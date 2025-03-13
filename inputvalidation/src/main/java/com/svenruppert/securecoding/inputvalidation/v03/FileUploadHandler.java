package com.svenruppert.securecoding.inputvalidation.v03;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileUploadHandler implements HttpHandler , HasLogger {
  protected static final String POST = "POST";
  protected static final String PATH_UPLOADS = "uploads";

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if (POST.equalsIgnoreCase(exchange.getRequestMethod())) {
//      logger().info();
      Path uploadDir = Paths.get(PATH_UPLOADS);
      Files.createDirectories(uploadDir);

      File tempFile = Files.createTempFile(uploadDir, "upload_", ".bin").toFile();
      try (InputStream inputStream = exchange.getRequestBody();
           OutputStream outputStream = new FileOutputStream(tempFile)) {
        inputStream.transferTo(outputStream);
      }

      String response = "Datei gespeichert als: " + tempFile.getAbsolutePath();
      exchange.sendResponseHeaders(200, response.length());
      exchange.getResponseBody().write(response.getBytes());
      exchange.close();
    } else {
      exchange.sendResponseHeaders(405, -1); // Method Not Allowed
    }
  }
}
