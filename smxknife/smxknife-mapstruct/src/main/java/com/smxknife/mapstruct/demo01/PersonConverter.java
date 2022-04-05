package com.smxknife.mapstruct.demo01;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author smxknife
 * 2021/1/19
 */
@Mapper
public interface PersonConverter {
	PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

	/**
	 * auto converter
	 * @param person
	 * @return
	 */
	@Mappings({
			@Mapping(source = "dateTime", target = "time"),
			@Mapping(source = "dateTime", target = "timeFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
			@Mapping(target = "timeExpression", expression = "java(java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:00:00\").format(person.getDateTime()))"),
			@Mapping(source = "user.age", target = "age"),
			@Mapping(target = "email", ignore = true)
	})
	PersonDTO domain2dto(Person person);
}
