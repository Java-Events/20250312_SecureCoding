package com.svenruppert.securecoding.inputvalidation.v03.p03.services;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.inputvalidation.v03.p03.handler.FileUploadHandler03;

public class RestService03 implements HasLogger {

	public static void main(String[] args) throws IOException {
		new RestService03().start(8080);
	}

	private void start(int port) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/upload", new FileUploadHandler03());
			server.setExecutor(null);
			server.start();

			logger().info("Server läuft auf http://localhost: " + port);
		}
		catch (IOException e) {
			logger().error("der server kann nicht gestartet werden");
		}
	}

}
