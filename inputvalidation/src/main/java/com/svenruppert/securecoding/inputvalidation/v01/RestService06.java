package com.svenruppert.securecoding.inputvalidation.v01;

import com.svenruppert.dependencies.core.logger.HasLogger;
import io.javalin.Javalin;

public class RestService06
implements HasLogger {

  public static final int DEFAULT_PORT = 7070;
    public static final String NUMERIC_REGEX = "-?\\d+(\\.\\d+)?";
    private final Javalin service;
  private final int port;


  public RestService06() {
    this.port = DEFAULT_PORT;
    service = initService();
  }

  public RestService06(int port) {
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
          if(valueA == null || valueA.isEmpty() || valueB == null || valueB.isEmpty()
                  || !valueA.matches(NUMERIC_REGEX) || !valueB.matches(NUMERIC_REGEX)) {
              ctx.result("");
          } else {
              Float fB = Float.valueOf(valueB);
              if (Math.signum(fB) == 0) {
                  ctx.result("");
              } else {
                  Float fA = Float.valueOf(valueA);
                  ctx.result(String.valueOf(new DivideService().divide(fA, fB)));
              }
          }
        })
        .get("/upper/{value}/{name}", ctx -> {
          String value = ctx.pathParam("value");
          String name = ctx.pathParam("name");
          ctx.result(new UpperCaseService()
              .toUpperCase(value + "-" + name));
        });
  }


}
