package de.moving.turtle.lysiana.mqtt.api;

import org.eclipse.paho.client.mqttv3.MqttMessage;

@FunctionalInterface
public interface OnMessageArrived {

    void onArrived(String topicName, MqttMessage message, Topic topic);
}
