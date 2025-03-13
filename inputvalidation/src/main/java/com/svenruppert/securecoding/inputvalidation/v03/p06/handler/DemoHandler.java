package com.svenruppert.securecoding.inputvalidation.v03.p06.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.svenruppert.dependencies.core.logger.HasLogger;
import org.eclipse.jetty.http.MimeTypes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemoHandler implements HttpHandler {
    private HttpExchange exchange;
    private final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    private final String POST = "POST";
    private HasLogger logger = new HasLogger(){};


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        this.exchange = exchange;
        if (POST.equalsIgnoreCase(exchange.getRequestMethod())) {
            log("Handle POST request");
            String mimeType = exchange.getRequestHeaders().getFirst("Content-Type");
            if (!isValidMimeType(mimeType)) {
                log("invalid mime type");
                exchange.sendResponseHeaders(400, "Invalid type".getBytes().length);
                return;
            }
            Path uploadDir = Paths.get("uploads").toAbsolutePath();
            if (!uploadDir.startsWith(Paths.get("/sicheresVerzeichnis"))) {
                log("invalid upload path");
                throw new SecurityException("Invalid path");
            }
            try {
                Files.createDirectories(uploadDir);
            } catch (IOException e) {
                log("error on directory creation");
                exchange.sendResponseHeaders(500, "Directory could not be created".length());
                exchange.getResponseBody().write("Directory could not be created".getBytes());
                exchange.close();
                return;
            }

            File tempFile = Files.createTempFile(uploadDir, "upload_", ".bin").toFile();
            String fileName = tempFile.getName();
            if (fileName.contains("..")) {
                log("invalid filename");
                exchange.sendResponseHeaders(400, "invalid filename".getBytes().length);
                return;
            }
            if (isVirusDetected(tempFile)) {
                log("virus detected");
                exchange.sendResponseHeaders(400, "virus detected".getBytes().length);
                return;
            }
            if (exchange.getRequestBody().available() > MAX_FILE_SIZE) {
                log("file too big");
                exchange.sendResponseHeaders(413, "file too big".getBytes().length);
                return;
            }
            try (InputStream inputStream = exchange.getRequestBody();
                 OutputStream outputStream = new FileOutputStream(tempFile)) {
                log("transfer file");
                inputStream.transferTo(outputStream);
            }

            String response = "file uploaded";
            log(response);
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        } else {
            log("Handle Non-POST request");
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }

    private void log(String message) {
        logger.logger().info(message);
    }

    private boolean isVirusDetected(File tempFile) {
        // TODO check for virus
        return false;
    }

    private boolean isValidMimeType(String type) {
        return MimeTypes.getKnownMimeTypes().contains(type);
    }
}