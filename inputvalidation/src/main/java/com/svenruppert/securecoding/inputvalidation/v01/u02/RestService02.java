package com.svenruppert.securecoding.inputvalidation.v01.u02;

import com.svenruppert.dependencies.core.logger.HasLogger;
import io.javalin.Javalin;

import java.util.Optional;

import static io.javalin.http.HttpStatus.BAD_REQUEST;

public class RestService02
        implements HasLogger {

    public static final int DEFAULT_PORT = 7070;
    private final Javalin service;
    private final int port;


    public RestService02() {
        this.port = DEFAULT_PORT;
        service = initService();
    }

    // unused
//    public RestService02(int port) {
//        this.port = port;
//        service = initService();
//    }

    public Javalin getService() {
        return service;
    }

    // unused
//    public Javalin startService() {
//        return service.start(port);
//    }

    public Javalin startService(int port) {
        return service.start(port);
    }

    private Javalin initService() {
        logger().info("Initializing REST service");
        return Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .get("/divide/{valueA}/{valueB}", ctx -> {
                    String valueA = ctx.pathParam("valueA");
                    String valueB = ctx.pathParam("valueB");
                    //werte können null sein, bzw fehlen
                    // Dieses Szenario bekomme ich über JavalinTest nicht hin. Die Werte werden nicht leer.
                    // Die Request werden vorher schon mit 400 BAD_REQUEST abgelehnt
//                    if (valueA.isEmpty()) {
//                        ctx.status(BAD_REQUEST);
//                        ctx.result("value a is null or empty");
//                        return;
//                    }
//                    if (valueB.isEmpty()) {
//                        ctx.status(BAD_REQUEST);
//                        ctx.result("value b is null or empty");
//                        return;
//                    }
                    //werte keine Zahlen
                    var floatValidator = new FloatValidator02();
                    if (!floatValidator.isFloat(valueA)) {
                        ctx.status(BAD_REQUEST);
                        ctx.result("value a is not a number");
                        return;
                    }
                    if (!floatValidator.isFloat(valueB)) {
                        ctx.status(BAD_REQUEST);
                        ctx.result("value b is not a number");
                        return;
                    }

                    //Division durch 0
                    float fA = Float.parseFloat(valueA);
                    float fB = Float.parseFloat(valueB);
                    DivideService02 divideService = new DivideService02();
                    Optional<Float> result = divideService.divideNull(fA, fB);
                    if (result.isPresent()) {
                        ctx.result(String.valueOf(result.get()));
                    } else {
                        ctx.status(BAD_REQUEST);
                        ctx.result("Div durch null geht nicht");
                    }
                })
                .get("/upper/{value}/{name}", ctx -> {
                    String value = ctx.pathParam("value");
                    String name = ctx.pathParam("name");
                    ctx.result(new UpperCaseService02()
                            .toUpperCase(value + "-" + name));
                });
    }


}
