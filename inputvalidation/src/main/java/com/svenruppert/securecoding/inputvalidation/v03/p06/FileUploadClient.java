package com.svenruppert.securecoding.inputvalidation.v03.p06;

import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUploadClient implements HasLogger {

  public void uploadFile(String filePath, String targetURL) throws IOException {
    File file = new File(filePath);
    if (!file.exists()) {
      logger().info("Datei nicht gefunden");
      return;
    }

    URL url = new URL(targetURL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoOutput(true);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/octet-stream");

    try (OutputStream outputStream = connection.getOutputStream();
         InputStream fileInputStream = Files.newInputStream(Path.of(filePath))) {
      logger().info("transferfile to output");
      fileInputStream.transferTo(outputStream);
    }

    int responseCode = connection.getResponseCode();
    logger().info("Server Antwort: " + responseCode);
  }
}
