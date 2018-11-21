package de.moving.turtle.lysiana.mqtt.api;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

@FunctionalInterface
public interface OnMessageComplete {

    void onComplete(IMqttDeliveryToken token, Topic topic);
}
