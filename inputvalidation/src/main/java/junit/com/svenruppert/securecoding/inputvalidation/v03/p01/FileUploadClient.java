package junit.com.svenruppert.securecoding.inputvalidation.v03.p01;

import com.svenruppert.dependencies.core.logger.HasLogger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUploadClient implements HasLogger {
  public static void main(String[] args) {
    String filePath = "testfile.bin"; // Pfad zur hochzuladenden Datei
    String targetURL = "http://localhost:7070/upload";

    try {
      uploadFile(filePath, targetURL);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void uploadFile(String filePath, String targetURL) throws IOException {
    File file = new File(filePath);
    if (!file.exists()) {
      System.err.println("Datei nicht gefunden: " + filePath);
      return;
    }

    URL url = new URL(targetURL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoOutput(true);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/octet-stream");

    try (OutputStream outputStream = connection.getOutputStream();
         InputStream fileInputStream = Files.newInputStream(Path.of(filePath))) {
      fileInputStream.transferTo(outputStream);
    }

    int responseCode = connection.getResponseCode();
    System.out.println("Server Antwort: " + responseCode);

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      String responseLine;
      while ((responseLine = reader.readLine()) != null) {
        System.out.println(responseLine);
      }
    }
  }
}
