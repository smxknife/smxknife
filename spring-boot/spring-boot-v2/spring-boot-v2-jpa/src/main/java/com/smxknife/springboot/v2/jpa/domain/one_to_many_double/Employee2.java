package com.smxknife.springboot.v2.jpa.domain.one_to_many_double;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author smxknife
 * 2019-02-21
 */
@Entity
public class Employee2 {

	@Id
	long id;

	@ManyToOne
	Department2 department;
}
