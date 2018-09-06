package org.waes.differ.resources;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * This Runner can be run standalone as a Java Application
 * (in case it is not possible to deploy the project to a server)
 * 
 * Grizzly creates the server requiring in the configuration:
 * - the class to run (DiffResource.class);
 * - the package where the exception mappers are located (otherwise a generic error page os thrown).
 */
public class DiffResourceRestStartup {

    private static final int PORT = 8081;
    private static final String HOST = "http://localhost/diffassign";

    @SuppressWarnings({ "unused" })
    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();

        ResourceConfig config = new ResourceConfig()
                .registerClasses(DiffResource.class)
                .packages("org.waes.differ.exceptions.mappers");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }
}