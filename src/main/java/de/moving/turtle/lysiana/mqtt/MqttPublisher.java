package de.moving.turtle.lysiana.mqtt;

import de.moving.turtle.lysiana.mqtt.api.Topic;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttPublisher {

    void publish(Topic topic, MqttMessage ... messages);

}
