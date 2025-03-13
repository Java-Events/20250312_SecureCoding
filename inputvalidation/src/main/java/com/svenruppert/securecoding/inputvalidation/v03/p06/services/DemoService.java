package com.svenruppert.securecoding.inputvalidation.v03.p06.services;

import com.sun.net.httpserver.HttpExchange;
import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.inputvalidation.v03.p06.handler.DemoHandler;
import io.javalin.Javalin;

public class DemoService implements HasLogger {

    public static final int DEFAULT_PORT = 7070;
    private final Javalin service;
    private final int port;
    private final DemoHandler demoHandler;


    public DemoService() {
        this.port = DEFAULT_PORT;
        this.demoHandler = new DemoHandler();
        service = initService();
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
                .get("/upload", ctx -> demoHandler.handle(ctx.bodyAsClass(HttpExchange.class)));

    }


}
