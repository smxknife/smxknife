package com.smxknife.springboot.v2.jpa.domain.many_to_one_single;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author smxknife
 * 2019-02-21
 */
@Entity
public class User1 {

	@Id
	long id;

	@ManyToOne
	Class1 cls;
}
