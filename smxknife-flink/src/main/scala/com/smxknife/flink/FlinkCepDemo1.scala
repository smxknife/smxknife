package com.smxknife.flink

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 *
 * @author smxknife 
 *         2021/6/9 
 */
object FlinkCepDemo1 {
  def main(args: Array[String]): Unit = {

    /**
     * cep为了解决复杂事件
     * 事件的关系：时序关系、聚合关系、层次关系、依赖关系
     * 步骤：
     *  事件流创建
     *  Pattern定义
     *  pattern在事件流上检测
     *  选取结果
     */

    val env = StreamExecutionEnvironment.getExecutionEnvironment

  }
}
