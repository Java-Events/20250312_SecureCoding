package com.svenruppert.securecoding.inputvalidation.v03.p02;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RestService {

    private static final Logger logger = LoggerFactory.getLogger(RestService.class);

    private HttpServer server;

    public void startServer(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/upload", new FileUploadHandler(new FileUploadService()));
            server.setExecutor(null);
            server.start();
            logger.info("Server läuft auf http://localhost:{}", port);
        } catch (IOException e) {
            logger.error("Error starting server", e);
        }
    }

    public void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }
}