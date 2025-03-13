package com.svenruppert.securecoding.inputvalidation.v03.p05.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUpload {


	  public String uploadFile(String filePath, String targetURL) throws IOException {
	    File file = new File(filePath);
	    if (!file.exists()) {
	      System.err.println("Datei nicht gefunden: " + filePath);
	      return "";
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
	      return reader.readLine();
	    }
	  }
}
