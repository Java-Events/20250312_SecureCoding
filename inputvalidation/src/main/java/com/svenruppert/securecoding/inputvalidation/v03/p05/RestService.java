package com.svenruppert.securecoding.inputvalidation.v03.p05;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.securecoding.inputvalidation.v03.p05.handler.FileUploadHandler;

public class RestService {


	public void startService(final int port) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/upload", new FileUploadHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Server läuft auf http://localhost:" + port);
		} catch(IOException e) {
			//TODO Logging
			System.out.println(e.getMessage());
		}
	}

}
