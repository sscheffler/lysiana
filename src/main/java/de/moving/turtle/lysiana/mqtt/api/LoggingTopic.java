package de.moving.turtle.lysiana.mqtt.api;

public final class LoggingTopic implements Topic {
    private static final String NAME = "topic.logging";

    public static final LoggingTopic LOGGING_TOPIC = new LoggingTopic();

    private LoggingTopic() {

    }

    @Override
    public String getName() {
        return NAME;
    }
}
