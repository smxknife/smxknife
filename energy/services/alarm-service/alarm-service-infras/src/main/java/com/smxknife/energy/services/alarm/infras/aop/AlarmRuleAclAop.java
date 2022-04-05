package com.smxknife.energy.services.alarm.infras.aop;

import com.smxknife.energy.services.alarm.infras.mq.AlarmProducerProperties;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRuleWrapper;
import com.smxknife.energy.services.alarm.infras.mq.producer.AlarmRuleWrapperKafkaSerializer;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Properties;

/**
 * @author smxknife
 * 2021/5/17
 */
@Slf4j
@Component
public class AlarmRuleAclAop {

	@Pointcut("execution(* com.smxknife.energy.services.alarm.core.dao.AlarmRuleDao.save(..))")
	public void save() {};

	@Pointcut("execution(* com.smxknife.energy.services.alarm.core.dao.AlarmRuleDao.update(..))")
	public void update() {};

	@Pointcut("execution(* com.smxknife.energy.services.alarm.core.dao.AlarmRuleDao.deleteByUid(..))")
	public void deleteByUid() {};

	@Autowired
	private AlarmProducerProperties properties;

	private KafkaProducer<String, AlarmRuleWrapper> kafkaProducer;

	@AfterReturning("save()")
	public void afterSave(JoinPoint joinPoint) {
		final Object[] args = joinPoint.getArgs();
		if (Objects.isNull(args) || args.length == 0 || Objects.isNull(args[0])) {
			log.warn("args exception");
			return;
		}

		final AlarmRule alarmRule = (AlarmRule) args[0];

		kafkaProducer.send(new ProducerRecord<String, AlarmRuleWrapper>(properties.getTopic(), new AlarmRuleWrapper(alarmRule, "save")));
	}

	@AfterReturning("update()")
	public void afterUpdate(JoinPoint joinPoint) {
		final Object[] args = joinPoint.getArgs();
		if (Objects.isNull(args) || args.length == 0 || Objects.isNull(args[0])) {
			log.warn("args exception");
			return;
		}

		final AlarmRule alarmRule = (AlarmRule) args[0];

		kafkaProducer.send(new ProducerRecord<String, AlarmRuleWrapper>(properties.getTopic(), new AlarmRuleWrapper(alarmRule, "update")));
	}

	@AfterReturning("deleteByUid()")
	public void afterDeleteByUid(JoinPoint joinPoint) {
		final Object[] args = joinPoint.getArgs();
		if (Objects.isNull(args) || args.length == 0 || Objects.isNull(args[0])) {
			log.warn("args exception");
			return;
		}

		final String uid = (String) args[0];
		final AlarmRule alarmRule = new AlarmRule();
		alarmRule.setUid(uid);
		kafkaProducer.send(new ProducerRecord<String, AlarmRuleWrapper>(properties.getTopic(), new AlarmRuleWrapper(alarmRule, "delete")));
	}

	@PostConstruct
	public void init() {
		final Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				properties.getBootstrapServers());
		props.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AlarmRuleWrapperKafkaSerializer.class.getName());

		kafkaProducer = new KafkaProducer<>(props);
	}
}
