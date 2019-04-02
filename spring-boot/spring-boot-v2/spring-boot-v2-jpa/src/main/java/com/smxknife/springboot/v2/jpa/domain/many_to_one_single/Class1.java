package com.smxknife.springboot.v2.jpa.domain.many_to_one_single;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author smxknife
 * 2019-02-21
 */
@Entity
public class Class1 {

	@Id
	long id;

//	@OneToMany
//	@JoinColumn(name = "cls")
//	List<User> users = new ArrayList<>();
}
