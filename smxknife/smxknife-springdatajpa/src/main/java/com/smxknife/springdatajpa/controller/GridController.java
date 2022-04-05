package com.smxknife.springdatajpa.controller;

import com.smxknife.springdatajpa.model.grid.Grid;
import com.smxknife.springdatajpa.repository.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2020/11/19
 */
@RestController
@RequestMapping("/grid")
public class GridController {

	@Autowired
	GridRepository gridRepository;

	@RequestMapping("insert")
	public String insert() {
		Grid grid1 = new Grid();
		grid1.setName("网格1");
		grid1.setNo("1");

		Grid grid2 = new Grid();
		grid2.setNo("2");
		grid2.setName("网格2");

		Grid grid11 = new Grid();
		grid11.setName("网格1-1");
		grid11.setNo("11");
		grid11.setParent(grid1);

		Grid grid12 = new Grid();
		grid12.setNo("12");
		grid12.setName("网格1-2");
		grid12.setParent(grid1);

		gridRepository.save(grid1);
		gridRepository.save(grid2);
		gridRepository.save(grid12);

		return "hhh";

	}
}
