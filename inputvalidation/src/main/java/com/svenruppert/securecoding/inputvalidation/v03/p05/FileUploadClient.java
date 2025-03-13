package com.svenruppert.securecoding.inputvalidation.v03.p05;

import java.io.IOException;

import com.svenruppert.securecoding.inputvalidation.v03.p05.client.FileUpload;

public class FileUploadClient {
  public static void main(String[] args) {
    String filePath = "C:\\Programme_schrRecht\\LibreOffice_24.8.4_Win_x86-64_helppack_de.msi"; // Pfad zur hochzuladenden Datei
    String targetURL = "http://localhost:8080/upload";

    try {
      final String result = new FileUpload().uploadFile(filePath, targetURL);
      System.out.println(result);
    } catch (IOException e) {
    	// TODO Logger
      System.out.println(e.getMessage());
    }
  }
}
