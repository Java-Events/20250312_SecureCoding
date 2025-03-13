package com.svenruppert.securecoding.inputvalidation.v03.p01.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.inputvalidation.v03.p01.ApplicationException;
import com.svenruppert.securecoding.inputvalidation.v03.p01.ApplicationResult;
import com.svenruppert.securecoding.inputvalidation.v03.p01.services.FileUploadService;

import java.io.*;

public class FileUploadHandler implements HttpHandler, HasLogger {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        logger().info("Handling upload request");
        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            ApplicationResult<String> result = new FileUploadService().processFileUpload(exchange.getRequestBody());
            result.ifSuccess(data -> createResponse(exchange, 200, data));
            result.ifError(error -> createResponse(exchange, 500, error));
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }

    private void createResponse(HttpExchange exchange, int status, String message) throws ApplicationException {
        try {
            exchange.sendResponseHeaders(status, message.length());
            exchange.getResponseBody().write(message.getBytes());
            exchange.close();
        } catch (IOException e) {
            throw new ApplicationException("Could not create response.", e);
        }
    }
}
