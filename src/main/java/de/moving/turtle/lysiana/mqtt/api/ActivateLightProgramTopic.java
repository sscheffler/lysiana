package de.moving.turtle.lysiana.mqtt.api;

public final class ActivateLightProgramTopic implements Topic {
    private static final String NAME = "topic/activate.light.program";

    public static final ActivateLightProgramTopic ACTIVATE_LIGHT_PROGRAM_TOPIC = new ActivateLightProgramTopic();

    private ActivateLightProgramTopic() {

    }

    @Override
    public String getName() {
        return NAME;
    }
}
