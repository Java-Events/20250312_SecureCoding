package com.svenruppert.securecoding.inputvalidation.v03.p02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    // TODO maybe add jspecify null annotations?
    public File saveFile(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }

        File tempFile = createTempFile();

        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            inputStream.transferTo(outputStream);
            logger.info("File uploaded successfully: {}", tempFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Error saving file", e);
            throw e;
        }
        return tempFile;
    }

    private static File createTempFile() throws IOException {
        Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
        Files.createDirectories(tempDir);
        return Files.createTempFile(tempDir, "upload_", ".bin").toFile();
    }
}