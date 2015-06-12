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
	 
	/**
	 * Get the Base Uri of the box
	 * @param hostname
	 * @param port
	 * @return
	 */
	private static URI getBaseURI(String hostname, int port) {
	        return UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
	    }
	 
	 /**
	  * Get the Server
	  * @param uri
	  * @return
	  * @throws IOException
	  */
	 protected static HttpServer GetServer(URI uri) throws IOException {
	        System.out.println("Starting grizzly...");
	        ResourceConfig rc = new PackagesResourceConfig("com.EmailServiceAbstraction.resources");
	        return GrizzlyServerFactory.createHttpServer(uri, rc);
	    }
	 
    /**
     * Start the Grizzly Server
     * @param args
     * @throws IOException
     */
	 public static void main(String[] args) throws IOException {
        try {
            System.out.println("Starting grizzly ...");
            URI uri = getBaseURI(System.getenv("HOSTNAME"), Integer.valueOf(System.getenv("PORT")));
            HttpServer httpServer = GetServer(uri);
            httpServer.start();
            System.out.println(String.format("Jersey started with WADL available at %sapplication.wadl.",uri));
            while(true) {
                System.in.read();
            }
        }
        catch(Throwable t) {
			t.printStackTrace();
		}
    }
}
