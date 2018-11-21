package de.moving.turtle.lysiana.mqtt.api;

@FunctionalInterface
public interface OnConnectionLost {

    void onLost(Throwable throwable, Topic topic);
}
