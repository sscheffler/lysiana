package de.moving.turtle.lysiana.mqtt;

import de.moving.turtle.lysiana.mqtt.api.Topic;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;

@Component
class MqttPublisherImpl implements MqttPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttPublisherImpl.class);

    private final MqttClient mqttClient;
    private final MqttConnectOptions connOpts;

    @Autowired
    MqttPublisherImpl(final MqttClient mqttClient) {
        this.mqttClient = mqttClient;

        this.connOpts = new MqttConnectOptions();
        this.connOpts.setCleanSession(true);
    }

    @Override
    public void publish(final Topic topic, final MqttMessage... messages) {
        try {
            this. mqttClient.connect(connOpts);
            stream(messages)
                    .forEach(m -> {
                        try {
                            mqttClient.publish(topic.getName(), m);
                        } catch (MqttException e) {
                            LOGGER.warn("Could not send message to: '{}'", topic.getName(), e);
                        }
                    });

            this.mqttClient.disconnect();
        } catch (MqttException me) {
            LOGGER.warn("Error on message sending", me);
        }



    }
}
