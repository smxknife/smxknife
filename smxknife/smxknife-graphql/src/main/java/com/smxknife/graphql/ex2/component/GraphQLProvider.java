package com.smxknife.graphql.ex2.component;

import com.smxknife.graphql.ex2.service.UserService;
import graphql.Scalars;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

@Component
public class GraphQLProvider {

	@Autowired
	UserService userService;

	private GraphQLOutputType user;

	private GraphQLSchema schema;

	public GraphQLProvider() {
		user = newObject().name("user")
				.field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLInt))
				.field(GraphQLFieldDefinition.newFieldDefinition().name("account").type(Scalars.GraphQLString))
				.field(GraphQLFieldDefinition.newFieldDefinition().name("password").type(Scalars.GraphQLString))
				.build();

		schema = GraphQLSchema.newSchema()
				.query(newObject()
						.name("query")
						.field(createUserField()))
				.build();
	}

	/**
	 * query single author
	 * @return the single author's information
	 */
	private GraphQLFieldDefinition createUserField() {
		return GraphQLFieldDefinition.newFieldDefinition()
				.name("user")
				.argument(newArgument().name("id").type(Scalars.GraphQLInt).build())
				.type(user)
				.dataFetcher((DataFetchingEnvironment environment) -> {
					int id = environment.getArgument("id");
					return this.userService.findById(id);
				}).build();

	}

	public GraphQLSchema getSchema() {
		return schema;
	}

	public void setSchema(GraphQLSchema schema) {
		this.schema = schema;
	}

	public GraphQLOutputType getUser() {
		return user;
	}

	public void setUser(GraphQLOutputType user) {
		this.user = user;
	}
}
