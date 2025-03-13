package junit.com.svenruppert.securecoding.inputvalidation.v03.p02;

import com.svenruppert.securecoding.inputvalidation.v03.p02.FileUploadService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadServiceTest {

    @Test
    void test_save_successful() throws IOException {
        FileUploadService fileUploadService = new FileUploadService();

        /* given */
        String fileContent = "This is a test file";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        /* when */
        File savedFile = fileUploadService.saveFile(inputStream);

        /* then */
        assertNotNull(savedFile);
        assertTrue(savedFile.exists());
        assertEquals(fileContent, Files.readString(savedFile.toPath()));
    }

    @Test
    void test_null_inputstream() {
        FileUploadService fileUploadService = new FileUploadService();

        assertThrows(IllegalArgumentException.class, () -> {
            fileUploadService.saveFile(null);
        });
    }
}