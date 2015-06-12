package com.EmailServiceAbstraction;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class Main {
	 private static URI getBaseURI(String hostname, int port) {
	        return UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
	    }
	 
	 protected static HttpServer GetServer(URI uri) throws IOException {
	        System.out.println("Starting grizzly...");
	        ResourceConfig rc = new PackagesResourceConfig("com.EmailServiceAbstraction.resources");
	        return GrizzlyServerFactory.createHttpServer(uri, rc);
	    }
	 
    public static void main(String[] args) throws IOException {
        
        final String baseUri = "http://localhost:"+(System.getenv("PORT")!=null?System.getenv("PORT"):"9998")+"/";
        final Map<String, String> initParams = new HashMap<String, String>();
        ResourceConfig rc = new PackagesResourceConfig("com.EmailServiceAbstraction.resources");
        try {
            System.out.println("Starting grizzly...");
            URI uri = URI.create(baseUri);
            HttpServer httpServer = GetServer(uri);
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
