package com.svenruppert.securecoding.inputvalidation.v03.p01.services;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.inputvalidation.v03.p01.ApplicationResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadService implements HasLogger {

    public ApplicationResult<String> processFileUpload(InputStream inputStream) {
        try {
            Path uploadDir = Paths.get("uploads");
            Files.createDirectories(uploadDir);

            File tempFile = Files.createTempFile(uploadDir, "upload_", ".bin").toFile();
            try (inputStream; OutputStream outputStream = new FileOutputStream(tempFile)) {
                inputStream.transferTo(outputStream);
            }
            return new ApplicationResult<>(true, "Successfully uploaded file.", null);
        } catch (IOException e) {
            return new ApplicationResult<>(false, null, "Could not process file upload.");
        }
    }

}
