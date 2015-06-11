package com.EmailServiceAbstraction;

import java.io.IOException;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.sun.grizzly.http.SelectorThread;

public class Main {
    private static java.net.URI getBaseURI(String hostname, int port) {
        return UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
    }

    protected static org.glassfish.grizzly.http.server.HttpServer startServer(java.net.URI uri) throws IOException {
        System.out.println("Starting grizzly...");
        
        final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put("com.sun.jersey.config.property.packages","package com.EmailServiceAbstraction.resources;");
        initParams.put("com.sun.jersey.config.property.packages","package com.EmailServiceAbstraction.models;");
        
        return GrizzlyServerFactory.createHttpServer(uri, initParams);
    }

    public static void main(String[] args) throws IOException {
        java.net.URI uri = getBaseURI(System.getenv("HOSTNAME"), Integer.valueOf(System.getenv("PORT")));
        org.glassfish.grizzly.http.server.HttpServer httpServer = startServer(uri);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
                uri, uri));
        while(true) {
            System.in.read();
        }
    }
}
