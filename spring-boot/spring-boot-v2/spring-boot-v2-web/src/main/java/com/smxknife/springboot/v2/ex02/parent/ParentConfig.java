package com.smxknife.springboot.v2.ex02.parent;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@EnableAutoConfiguration
//@ComponentScan
@Configuration
//@SpringBootApplication
@PropertySource("classpath:/parent.properties")
public class ParentConfig {
}
