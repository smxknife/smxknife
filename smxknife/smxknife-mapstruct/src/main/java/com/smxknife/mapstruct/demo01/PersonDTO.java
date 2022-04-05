package com.smxknife.mapstruct.demo01;

import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2021/1/19
 */
@ToString
public class PersonDTO {
	private Long id;
	private String name;
	/**
	 * 对应User age
	 */
	private Integer age;
	private String email;

	/**
	 * 与Person DO里面的dateTime不一致
	 */
	private LocalDateTime time;

	/**
	 * 与Persion DO里面的dateTime的format格式
	 */
	private String timeFormat;

	/**
	 * expression
	 */
	private String timeExpression;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getTimeExpression() {
		return timeExpression;
	}

	public void setTimeExpression(String timeExpression) {
		this.timeExpression = timeExpression;
	}
}
