package com.smxknife.mapstruct.demo01;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/1/19
 */
public class Demo01 {
	public static void main(String[] args) {
		Person person = new Person();
		person.setEmail("xxx@xxx.com");
		person.setId(1L);
		person.setDateTime(LocalDateTime.now());
		person.setName("test");
		person.setUser(new User());

		PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
		System.out.println(personDTO);
	}
}
