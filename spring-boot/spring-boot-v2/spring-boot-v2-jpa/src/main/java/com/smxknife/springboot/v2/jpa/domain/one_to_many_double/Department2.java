package com.smxknife.springboot.v2.jpa.domain.one_to_many_double;

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
public class Department2 {

	@Id
	long id;

	@OneToMany(mappedBy = "department")
	List<Employee2> employee2List = new ArrayList<>();
}
