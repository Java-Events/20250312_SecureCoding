package com.svenruppert.securecoding.inputvalidation.v03;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RestService {
  public static void main(String[] args) throws IOException {
    int port = 8080;
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/upload", new FileUploadHandler());
    server.setExecutor(null);
    server.start();
    System.out.println("Server läuft auf http://localhost:" + port);
  }

  static class FileUploadHandler implements HttpHandler {
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

        String response = "Datei gespeichert als: " + tempFile.getAbsolutePath();
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
      } else {
        exchange.sendResponseHeaders(405, -1); // Method Not Allowed
      }
    }
  }




}
