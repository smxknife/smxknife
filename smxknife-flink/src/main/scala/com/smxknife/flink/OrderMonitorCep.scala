package com.smxknife.flink

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.cep.scala.{CEP, PatternStream}
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
 *
 * @author smxknife 
 *         2021/6/10 
 */
object OrderMonitorCep {
  def main(args: Array[String]): Unit = {

    // 使用cep
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

    // 定义pattern
    val pattern: Pattern[OrderEvent, OrderEvent] = Pattern.begin[OrderEvent]("create")
      .where(event => event.state.equals("create"))
      .followedBy("pay")
      .where(event => event.state.equals("pay"))
      .within(Time.seconds(10))

    // cep 检测
    val patternStream: PatternStream[OrderEvent] = CEP.pattern(orderEventStream.keyBy(_.id), pattern)

    val mainstream: DataStream[OrderMsg] = patternStream.select(timeoutTag)(
      (map: scala.collection.Map[String, Iterable[OrderEvent]], time: Long) => {
        val event: OrderEvent = map.get("create").get.iterator.next()
        val orderMsg = new OrderMsg(event.id, "订单超时", event.state, event.time)
        orderMsg
      }
    )(
      (map: scala.collection.Map[String, Iterable[OrderEvent]]) => {
        // 正常
        val create: OrderEvent = map.get("create").get.iterator.next()
        val pay: OrderEvent = map.get("pay").get.iterator.next()

        val orderMsg = new OrderMsg(create.id, "支付成功", pay.state, pay.time)
        orderMsg
      }
    )

    mainstream.getSideOutput(timeoutTag).print("c")
    mainstream.print("m")

    env.execute()
  }
}


case class OrderEvent(id:String, account:String, state:String, time:Long)
case class OrderMsg(id:String, msg:String, state:String, time:Long)
