package com.smxknife.java.ex27;

import lombok.Getter;

import java.util.Optional;

/**
 * @author smxknife
 * 2019-06-01
 */
public class Demo7 {
	public static void main(String[] args) {
		Boss boss = new Boss("sy");
		Department department = new Department();
		department.boss = boss;
		Company company = new Company();
		company.department = department;

		Optional<Company> companyOptional = Optional.of(company);
		System.out.println(companyOptional
				.map(Company::getDepartment)
				.map(Department::getBoss)
				.map(Boss::getName)
				.get()
		);


	}
}

@Getter
class Company {
	Department department;
}

@Getter
class Department {
	Boss boss;
}

@Getter
class Boss {
	String name;

	public Boss(String name) {
		this.name = name;
	}
}


