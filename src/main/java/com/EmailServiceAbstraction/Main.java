package com.EmailServiceAbstraction;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.http.server.HttpServer;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class Main {
    public static void main(String[] args) throws IOException {
        
        final String baseUri = "http://localhost:"+(System.getenv("PORT")!=null?System.getenv("PORT"):"9998")+"/";
        final Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.property.packages","com.EmailServiceAbstraction.resources");
        ResourceConfig rc = new PackagesResourceConfig("com.EmailServiceAbstraction.resources");
        try {
            System.out.println("Starting grizzly...");
            URI uri = URI.create(baseUri);
            HttpServer httpServer = GrizzlyServerFactory.createHttpServer(uri, rc);
            httpServer.start();
            System.out.println(String.format("Jersey started with WADL available at %sapplication.wadl.",baseUri, baseUri));
            System.in.read();
            httpServer.stop();
        }
        catch(Throwable t) {
			t.printStackTrace();
		}
    }
}
