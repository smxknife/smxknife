package com.smxknife.flink.demo.demo01;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author smxknife
 * 2020/4/24
 */
public class FraudDetectionJob {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStream<Transaction> transactions = env.addSource(new TransactionSource())
				.name("Transaction");

		DataStream<Alert> alerts = transactions.keyBy(Transaction::getAccountId)
				.process(new FraudDetector())
				.name("fraud-detector");

		alerts.addSink(new AlertSink())
				.name("send-alerts");

		env.execute("Fraud Detection");
	}
}
