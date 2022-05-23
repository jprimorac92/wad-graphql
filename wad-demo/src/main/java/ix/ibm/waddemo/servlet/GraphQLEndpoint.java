/*package ix.ibm.waddemo.servlet;

import graphql.kickstart.servlet.GraphQLConfiguration;
import graphql.kickstart.servlet.GraphQLHttpServlet;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaParser;
import ix.ibm.waddemo.resolver.Query;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends GraphQLHttpServlet
{
    @Override
    protected GraphQLConfiguration getConfiguration() {
        return GraphQLConfiguration.with(createSchema()).build();
    }

    private GraphQLSchema createSchema() {
        SchemaParser schemaParser = new SchemaParser();
        return schemaParser.parse("graphql/schema.graphqls").
            .resolvers(new Query())
            .build()
            .makeExecutableSchema();
    }
}
*/