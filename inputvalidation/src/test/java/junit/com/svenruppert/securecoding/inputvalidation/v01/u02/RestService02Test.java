package junit.com.svenruppert.securecoding.inputvalidation.v01.u02;

import com.svenruppert.securecoding.inputvalidation.v01.u02.RestService02;
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
class RestService02Test {

    @Test
    void test001() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
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
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
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
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/upper/3.0/xX@");
            assertEquals(OK.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("3.0-XX@", body.string());
        });
    }

    @Test
    void test_value_successful() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/1/1");
            assertEquals(OK.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("1.0", body.string());
        });
    }

    @Test
    void test_value_a_has_space() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/%20/1");
            assertEquals(BAD_REQUEST.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("value a is not a number", body.string());
        });
    }

    @Test
    void test_value_b_has_space() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/1/%20");
            assertEquals(BAD_REQUEST.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("value b is not a number", body.string());
        });
    }

    @Test
    void test_value_a_is_empty() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide//1");
            assertEquals(NOT_FOUND.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("Not Found", body.string());
        });
    }


    @Test
    void test_value_a_is_not_a_number() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/KEINEZAHL/1");
            assertEquals(BAD_REQUEST.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("value a is not a number", body.string());
        });
    }

    @Test
    void test_value_b_is_empty() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/1/");
            assertEquals(NOT_FOUND.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("Not Found", body.string());
        });
    }

    @Test
    void test_value_b_is_not_a_number() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/1/KEINEZAHL");
            assertEquals(BAD_REQUEST.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("value b is not a number", body.string());
        });
    }

    @Test
    void test_divide_by_zero() {
        RestService02 service = new RestService02();
        Javalin javalin = service.getService();
        JavalinTest.test(javalin, (server, httpClient) -> {
            Response response = httpClient.get("/divide/2/0");
            assertEquals(BAD_REQUEST.getCode(), response.code());
            ResponseBody body = response.body();
            assertNotNull(body);
            assertEquals("Div durch null geht nicht", body.string());
        });
    }
}
