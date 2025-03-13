package junit.com.svenruppert.securecoding.inputvalidation.v01;

import com.svenruppert.securecoding.inputvalidation.v01.RestService01;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.eclipse.jetty.http.HttpStatus.Code.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
class RestService01Test {

  @Test
  void test001() {
    RestService01 service = new RestService01();
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
    RestService01 service = new RestService01();
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
    RestService01 service = new RestService01();
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
    RestService01 service = new RestService01();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide//1");
      assertEquals(NOT_FOUND.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Not Found", body.string());
    });
  }

  @Test
  void test004a() {
    RestService01 service = new RestService01();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/1/");
      assertEquals(NOT_FOUND.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Not Found", body.string());
    });
  }

  @Test
  void test005() {
    RestService01 service = new RestService01();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/2/0");
      assertEquals(BAD_REQUEST.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Div durch null geht nicht", body.string());
    });
  }
  @Test
  void test006() {
    RestService01 service = new RestService01();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/ /0");
      assertEquals(BAD_REQUEST.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Not a valid number", body.string());
    });
  }

  @Test
  void test007() {
    RestService01 service = new RestService01();
    Javalin     javalin = service.getService();
    JavalinTest.test(javalin, (server, httpClient) -> {
      Response response = httpClient.get("/divide/2/a");
      assertEquals(BAD_REQUEST.getCode(), response.code());
      ResponseBody body = response.body();
      assertNotNull(body);
      assertEquals("Not a valid number", body.string());
    });
  }
}
