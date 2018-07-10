package com.smxknife.graphql.ex2.component;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class GraphQLFacade {

	private static GraphQLProvider PROVIDER;
	private static GraphQL GRAPH_QL;

	@Autowired
	public GraphQLFacade(GraphQLProvider graphQLProvider) {
		PROVIDER = graphQLProvider;
		GRAPH_QL = GraphQL.newGraphQL(PROVIDER.getSchema()).build();
	}

	/**
	 * query by the Graphql
	 * @param ghql the query
	 * @return the result
	 */
	public static Map<String, Object> query(String ghql) {
		if (ghql == null || ghql.isEmpty()) {
			return Collections.emptyMap();
		}

		return GRAPH_QL.execute(ghql).getData();
	}
}
