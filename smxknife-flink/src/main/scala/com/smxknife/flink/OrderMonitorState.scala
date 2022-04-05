package com.smxknife.flink

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala.{OutputTag, StreamExecutionEnvironment}
import org.apache.flink.util.Collector

import java.time.{Instant, LocalDateTime, ZoneId}

/**
 *
 * @author smxknife 
 *         2021/6/10 
 */
object OrderMonitorState {

  val time: LocalDateTime = LocalDateTime.now()
  val milli: Long = time.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli

  def main(args: Array[String]): Unit = {
    // 京东里，一个订单创建后，
    // 15分钟内没有支付，会发送一个提示信息给用户
    // 15分钟内已经支付，会发送一个提示信息给商家

    // 使用状态编程
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    import org.apache.flink.streaming.api.scala._

    // 创建一个侧输出流的tag，用于给用户发送消息
    val timeoutTag = new OutputTag[OrderMsg]("order-timeout")

    val orderEventStream: DataStream[OrderEvent] = env.socketTextStream("localhost", 8888)
      .map(new MapFunction[String, OrderEvent] {
        override def map(str: String): OrderEvent = {
          val array: Array[String] = str.split(",")
          new OrderEvent(array(0), array(1), array(2), System.currentTimeMillis())
        }
      }).assignAscendingTimestamps(_.time)

    val mainStream: DataStream[OrderMsg] = orderEventStream
      .keyBy(_.id)
      .process(new OrderEventProcessFunction(timeoutTag))

    mainStream.print("商家")
    mainStream.getSideOutput(timeoutTag).print("user")

    env.execute();

  }

  class OrderEventProcessFunction(timeoutTag:OutputTag[OrderMsg]) extends KeyedProcessFunction[String, OrderEvent, OrderMsg] {

    // 状态直接保存
    var orderCreateEvent:ValueState[OrderEvent] = _
    var timeoutState:ValueState[Long] = _

    override def processElement(event: OrderEvent, context: KeyedProcessFunction[String, OrderEvent, OrderMsg]#Context, collector: Collector[OrderMsg]): Unit = {
      // 首先从状态中获取订单的创建数据
      val orderEvent: OrderEvent = orderCreateEvent.value()

      if (event.state.equals("create") && orderEvent == null) { // 刚刚创建
        orderCreateEvent.update(event) // 更新状态
        val triggerTime: Long = event.time + (15 * 1000) // 这里为了看出效果，使用15s，不是15分钟

//        println("trigger " + triggerTime)

//        val time: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(triggerTime), ZoneId.systemDefault())
//        println(time)

        timeoutState.update(triggerTime)
        // 开始注册触发器
        context.timerService().registerProcessingTimeTimer(triggerTime)

      }

      if (event.state.equals("pay") && orderEvent != null) { // 当前
        // 再判断一下，支付是否超时
        if (timeoutState.value() > event.time) {// 表示支付没有超时
          // 先把触发器删除
          context.timerService().registerProcessingTimeTimer(timeoutState.value())

          // 生成商家的提示信息
          val msg = new OrderMsg(event.id, "支付成功", event.state, event.time)
          collector.collect(msg)
          // 清理
          orderCreateEvent.clear()
          timeoutState.clear()
        }
      }
    }

    // 状态初始化
    override def open(parameters: Configuration): Unit = {
      val orderCreateEventDescriptor: ValueStateDescriptor[OrderEvent] = new ValueStateDescriptor[OrderEvent]("orderCreateEvent", classOf[OrderEvent])
      orderCreateEvent = getRuntimeContext.getState(orderCreateEventDescriptor)

      val timeoutStateDescriptor: ValueStateDescriptor[Long] = new ValueStateDescriptor[Long]("timeoutState", classOf[Long])
      timeoutState = getRuntimeContext.getState(timeoutStateDescriptor)
    }

    // 触发器触发的时候执行
    // 严格注意，这里使用的是事件触发时间，所以，一定需要更新watermarker，虽然上面没有使用watermarker，
    // 但是通过assignAscendingTimestamps指定了更新事件的时间，也就是说，如果一直不更新数据，那么事件时间也不会更新，订单数据会一直存在
    override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[String, OrderEvent, OrderMsg]#OnTimerContext, out: Collector[OrderMsg]): Unit = {
      val event: OrderEvent = orderCreateEvent.value()

      println("--------")
      if (event != null) { // 触发器开始触发了，订单创建之后过了15分钟后，给用户发送信息，发送到侧输出流
        ctx.output(timeoutTag, new OrderMsg(event.id, "过时，订单失败", event.state, event.time))
        orderCreateEvent.clear()
        timeoutState.clear()
      }
    }
  }
}