package com.svenruppert.securecoding.inputvalidation.v01;

import java.util.Optional;

import com.svenruppert.dependencies.core.logger.HasLogger;

import io.javalin.Javalin;

public class Rest05Service
implements HasLogger {

  public static final int DEFAULT_PORT = 7070;
  private final Javalin service;
  private final int port;


  public Rest05Service() {
    this.port = DEFAULT_PORT;
    service = initService();
  }

  public Rest05Service(int port) {
    this.port = port;
    service = initService();
  }

  public Javalin getService() {
    return service;
  }

  public Javalin startService() {
    return service.start(port);
  }

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
          if (valueA.isBlank()) {
            ctx.status(400).result("Nicht alle Werte befüllt!");
            return;
          }

          //werte keine Zahlen
          if (!valueA.matches("\\d+") || !valueB.matches("\\d+")) {
            ctx.status(400).result("Einer der beiden Werte ist keine Zahl!");
            return;
          }

          //Division durch 0
          Float fA = Float.valueOf(valueA);
          Float fB = Float.valueOf(valueB);
          DivideService service = new DivideService();

          Optional<Float> result = service.divideNull(fA, fB);
          if (result.isEmpty()) {
            ctx.result("Div durch 0 NICHT erlaubt!");
            return;
          }
          ctx.result(String.valueOf(result.get()));
        })
        .get("/upper/{value}/{name}", ctx -> {
          String value = ctx.pathParam("value");
          String name = ctx.pathParam("name");
          ctx.result(new UpperCaseService()
              .toUpperCase(value + "-" + name));
        });
  }


}
