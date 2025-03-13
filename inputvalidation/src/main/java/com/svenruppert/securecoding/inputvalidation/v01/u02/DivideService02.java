package com.svenruppert.securecoding.inputvalidation.v01.u02;

import com.svenruppert.dependencies.core.logger.HasLogger;

import java.util.Optional;

public class DivideService02 implements HasLogger {

    public float divide(float a, float b) {
        logger().info("divide {}/{}", a, b);
        if (b == 0) throw new IllegalArgumentException("Div durch null geht nicht");
        float result = a / b;
        logger().info("result: {}", result);
        return result;
    }

    public Optional<Float> divideNull(float a, float b) {
        logger().info("divide {}/{}", a, b);
        if (b == 0) return Optional.empty();
        Optional<Float> result = Optional.of(a / b);
        logger().info("result: {}", result);
        return result;
    }

}
