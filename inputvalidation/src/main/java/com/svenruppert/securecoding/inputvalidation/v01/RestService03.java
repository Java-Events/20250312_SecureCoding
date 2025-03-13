package com.svenruppert.securecoding.inputvalidation.v01;

import java.util.Optional;

import com.svenruppert.dependencies.core.logger.HasLogger;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class RestService03
implements HasLogger {

  public static final int DEFAULT_PORT = 7070;
  private final Javalin service;
  private final int port;


  public RestService03() {
    this.port = DEFAULT_PORT;
    service = initService();
  }

  public RestService03(int port) {
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
          if (valueA == null || valueA.isEmpty()) {
              ctx.status(HttpStatus.BAD_REQUEST).result("valueA leer");
          }
          if (valueB == null || valueB.isEmpty()) {
              ctx.status(HttpStatus.BAD_REQUEST).result("valueB leer");
          }
          //werte keine Zahlen
          if (!valueA.matches("\\d+") || !valueB.matches("\\d+")) {
              ctx.status(HttpStatus.BAD_REQUEST).result("valueA oder valueB ist keine zahl");
          }
          //Division durch 0
          Float fA = Float.valueOf(valueA);
          Float fB = Float.valueOf(valueB);
          Optional<Float> result = new DivideService().divideNull(fA, fB);

          if (result.isEmpty()) {
        	  ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("div durch 0 geht nicht");
          }
          else {
        	  ctx.status(HttpStatus.OK).result(String.valueOf(result.get()));
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
