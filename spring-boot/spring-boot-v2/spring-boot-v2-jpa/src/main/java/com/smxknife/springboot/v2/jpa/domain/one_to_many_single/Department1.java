package com.smxknife.springboot.v2.jpa.domain.one_to_many_single;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2019-02-21
 */
@Entity
public class Department1 {

	@Id
	long id;

	@OneToMany
	List<Employee1> employees = new ArrayList<>();
}
