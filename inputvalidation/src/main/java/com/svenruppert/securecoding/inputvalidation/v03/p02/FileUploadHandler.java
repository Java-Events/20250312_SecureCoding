package com.svenruppert.securecoding.inputvalidation.v03.p02;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

class FileUploadHandler implements HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadHandler.class);

    private final FileUploadService fileUploadService;

    public FileUploadHandler(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            logger.info("Received POST request for file upload");
            try (InputStream inputStream = exchange.getRequestBody()) {
                fileUploadService.saveFile(inputStream);
                // TODO decide what we want to do with the saved file...
                String response = "Datei gespeichert";
                logger.info("Response: {}", response);
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
            } catch (IOException e) {
                logger.error("Error processing file upload", e);
                exchange.sendResponseHeaders(500, -1); // Internal Server Error
            }
        } else {
            logger.warn("Received non-POST request");
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}
