package junit.com.svenruppert.securecoding.inputvalidation.v03.p02;

import com.svenruppert.securecoding.inputvalidation.v03.p02.RestService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestServiceTest {

    private static RestService restService;
    private static final int PORT = 8080;
    private static final String BASE_URL = "http://localhost:" + PORT;

    @BeforeAll
    static void setUp() {
        restService = new RestService();
        restService.startServer(PORT);
    }

    @AfterAll
    static void tearDown() {
        restService.stopServer();
    }

    @Test
    void testFileUpload() throws IOException {
        File file = File.createTempFile("testfile", ".txt");
        Files.writeString(file.toPath(), "This is a test file");

        URI uri = URI.create(BASE_URL + "/upload");
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/octet-stream");

        try (OutputStream outputStream = connection.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.transferTo(outputStream);
        }

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        assertTrue(response.toString().contains("Datei gespeichert"));
    }

    @Test
    void testNonPostRequest() throws IOException {
        URI uri = URI.create(BASE_URL + "/upload");
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(405, responseCode);
    }
}