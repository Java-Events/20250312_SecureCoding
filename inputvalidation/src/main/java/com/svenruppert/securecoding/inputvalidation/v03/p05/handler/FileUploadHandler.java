package com.svenruppert.securecoding.inputvalidation.v03.p05.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class FileUploadHandler implements HttpHandler {
    private HttpExchange exchange;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      this.exchange = exchange;
      if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir);

        File tempFile = Files.createTempFile(uploadDir, "upload_", ".bin").toFile();
        try (InputStream inputStream = exchange.getRequestBody();
             OutputStream outputStream = new FileOutputStream(tempFile)) {
          inputStream.transferTo(outputStream);
        }

        String response = tempFile.getAbsolutePath();
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
      } else {
        exchange.sendResponseHeaders(405, -1); // Method Not Allowed
      }
    }
}
