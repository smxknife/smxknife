package com.smxknife.flink.cep

import com.sun.xml.internal.rngom.binary.visitor.PatternFunction
import org.apache.flink.cep.PatternSelectFunction
import org.apache.flink.cep.scala.{CEP, PatternStream}
import org.apache.flink.cep.scala.pattern.Pattern
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
 *
 * @author smxknife 
 *         2021/6/10 
 */
object UserLoginCep {

  // 实时的根据用户登陆日志，来判断是否恶意登陆

  /**
   * 恶意登陆规则：
   *  - 10s内连续登陆失败3次为恶意登陆
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    // 1. 事件流
    val userLogStream: DataStream[LoginEvent] = env.fromCollection(Array(
      new LoginEvent(1, "zhangsan", "fail", 10000),
      new LoginEvent(2, "zhangsan", "fail", 11000),
      new LoginEvent(3, "zhangsan", "fail", 12000),
      new LoginEvent(4, "lisi", "fail", 13000),
      new LoginEvent(5, "lisi", "success", 14000),
      new LoginEvent(6, "zhangsan", "fail", 15000),
    )).assignAscendingTimestamps(_.time) // 引入事件时间

    // 2. 定义模式
    val pattern: Pattern[LoginEvent, LoginEvent] = Pattern.begin[LoginEvent]("fail-start")
      .where(_.loginType.equals("fail")) // 条件：loginType是fail
      .times(3) // 3
      .greedy // 尽可能多的匹配
      .within(Time.seconds(10)) // 10s内，

    // 3. 检测数据流
    val patternStream: PatternStream[LoginEvent] = CEP.pattern(userLogStream.keyBy(_.username), pattern)

    // 4. 选择数据并且返回
//    val cepResultStream: DataStream[String] = patternStream.select(psf => {
//      val builder = new StringBuilder
//      val failItr: Iterable[LoginEvent] = psf.get("fail-start").get
//      failItr.foreach(event => {
//        builder.append(event.id).append(event.username).append(event.loginType).append(event.time)
//      })
//      builder.toString()
//    })
//
//    cepResultStream.print()

    env.execute();
  }

}

case class LoginEvent(id:Long, username:String, loginType:String,time:Long)
