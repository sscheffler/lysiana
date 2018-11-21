package de.moving.turtle.lysiana.mqtt.api;

public final class LoggingTopic implements Topic {
    private static final String NAME = "topic.logging";

    @Override
    public String getName() {
        return NAME;
    }
}
