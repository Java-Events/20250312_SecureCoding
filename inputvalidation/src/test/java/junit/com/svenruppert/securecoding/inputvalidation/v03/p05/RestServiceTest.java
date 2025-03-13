package junit.com.svenruppert.securecoding.inputvalidation.v03.p05;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.svenruppert.securecoding.inputvalidation.v03.p05.RestService;
import com.svenruppert.securecoding.inputvalidation.v03.p05.client.FileUpload;


class RestServiceTest {

	@Test
	void test001() throws Exception {
		RestService service = new RestService();
		service.startService(8080);

	    String filePath = "C:\\Programme_schrRecht\\LibreOffice_24.8.4_Win_x86-64_helppack_de.msi"; // Pfad zur hochzuladenden Datei
	    String targetURL = "http://localhost:8080/upload";
		String resultPath = new FileUpload().uploadFile(filePath, targetURL);

		final Path path = Paths.get(resultPath);
		Assertions.assertTrue(path.toFile().exists());

		path.toFile().delete();
		service.stopService();
	}

}
