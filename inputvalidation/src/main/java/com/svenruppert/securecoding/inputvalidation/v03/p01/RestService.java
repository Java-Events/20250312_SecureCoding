package com.svenruppert.securecoding.inputvalidation.v03.p01;

import com.sun.net.httpserver.HttpServer;
import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.inputvalidation.v03.p01.handler.FileUploadHandler;

import java.io.*;
import java.net.InetSocketAddress;

public class RestService implements HasLogger {
    public static final int DEFAULT_PORT = 7070;

    public void start(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/upload", new FileUploadHandler());
            server.setExecutor(null);
            server.start();
            logger().info("Server auf http://localhost:{}", port);
        } catch (IOException e) {
            logger().error("Could not start HTTP server");
        }
    }

}
