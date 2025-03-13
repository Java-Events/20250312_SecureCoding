package com.svenruppert.securecoding.inputvalidation.v03.p05;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.securecoding.inputvalidation.v03.p05.handler.FileUploadHandler;

public class RestService {

	private HttpServer server;

	public void startService(final int port) {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/upload", new FileUploadHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Server läuft auf http://localhost:" + port);
		} catch(IOException e) {
			//TODO Logging
			System.out.println(e.getMessage());
		}
	}

	public void stopService() {
		if (server != null) {
			server.stop(0);
		}
	}

}
