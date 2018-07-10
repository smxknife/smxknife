package com.smxknife.graphql.ex2.controller;

import com.alibaba.fastjson.JSON;
import com.smxknife.graphql.ex2.component.GraphQLFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/graphql")
public class GraphQLController {

	@RequestMapping("query/{ghql}")
	public void query(@PathVariable("ghql") String ghql, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String res = JSON.toJSONString(GraphQLFacade.query(ghql));
		System.out.printf("request query: [ %s ],\nresult: [ %s ]", ghql, res);
		try (PrintWriter writer = response.getWriter()){
			writer.append(res);
		}
	}
}
