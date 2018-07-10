package com.smxknife.springcloud.dataflow.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@EnableBinding(Processor.class)
@SpringBootApplication
public class ProcessorBoot {
	public static void main(String[] args) {
		SpringApplication.run(ProcessorBoot.class, args);
	}

	@Transformer(inputChannel = Processor.INPUT,
			outputChannel = Processor.OUTPUT)
	public Object transform(Long timestamp) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:yy");
		String date = dateFormat.format(timestamp);
		return date;
	}
}
