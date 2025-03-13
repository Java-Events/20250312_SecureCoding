package com.svenruppert.securecoding.inputvalidation.v03.p03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import com.svenruppert.dependencies.core.logger.HasLogger;

public class FileUploadClient03 implements HasLogger {

	public static void main(String[] args) {
		// Pfad zur hochzuladenden Datei
		String filePath = "C:\\testfile.bin";
		String targetURL = "http://localhost:8080/upload";

		try {
			new FileUploadClient03().uploadFile(filePath, targetURL);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void uploadFile(String filePath, String targetURL) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			logger().error("Datei nicht gefunden: " + filePath);
			throw new FileNotFoundException("Datei nicht gefunden");
		}
		URI uri = URI.create(targetURL);
		HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/octet-stream");

		try (OutputStream outputStream = connection.getOutputStream(); InputStream fileInputStream = Files.newInputStream(Path.of(filePath))) {
			fileInputStream.transferTo(outputStream);
		}
		int responseCode = connection.getResponseCode();
		logger().info("Server Antwort: " + responseCode);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String responseLine;
			while ((responseLine = reader.readLine()) != null) {
				logger().info(responseLine);
			}
		}
	}

}
