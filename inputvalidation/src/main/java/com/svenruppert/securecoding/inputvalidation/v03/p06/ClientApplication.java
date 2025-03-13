package com.svenruppert.securecoding.inputvalidation.v03.p06;

import java.io.IOException;

public class ClientApplication {
    public static void main(String[] args) {
        String filePath = "testfile.bin"; // Pfad zur hochzuladenden Datei
        String targetURL = "http://localhost:7070/upload";

        var client = new FileUploadClient();
        try {
            client.uploadFile(filePath, targetURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
