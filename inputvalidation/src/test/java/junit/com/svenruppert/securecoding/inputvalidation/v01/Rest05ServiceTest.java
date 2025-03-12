package junit.com.svenruppert.securecoding.inputvalidation.v01;

import static org.eclipse.jetty.http.HttpStatus.Code.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.svenruppert.securecoding.inputvalidation.v01.RestService;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import okhttp3.ResponseBody;

class Rest05ServiceTest {

  @Test
  void test001() {
    RestService service = new RestService();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/");
      assertEquals(response.code(), OK.getCode());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Hello World", body.string());
    });
  }

  @Test
  void test002() {
    RestService service = new RestService();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/upper/value/name");
      assertEquals(OK.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("VALUE-NAME", body.string());
    });
  }
  @Test
  void test003() {
    RestService service = new RestService();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/upper/3.0/xX@");
      assertEquals(OK.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("3.0-XX@", body.string());
    });
  }

  @Test
  void test004() {
    RestService service = new RestService();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/Omma/1");
      assertEquals(HttpStatus.BAD_REQUEST.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Einer der beiden Werte ist keine Zahl!", body.string());
    });
  }

  @Test
  void test005() {
    RestService service = new RestService();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/2/Oppa");
      assertEquals(HttpStatus.BAD_REQUEST.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Einer der beiden Werte ist keine Zahl!", body.string());
    });
  }

  @Test
  void test006() {
    RestService service = new RestService();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/2/0");
      assertEquals(HttpStatus.OK.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Div durch 0 NICHT erlaubt!", body.string());
    });
  }
}
