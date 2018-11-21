package de.moving.turtle.lysiana.mqtt;

import de.moving.turtle.lysiana.mqtt.api.OnConnectionLost;
import de.moving.turtle.lysiana.mqtt.api.OnMessageArrived;
import de.moving.turtle.lysiana.mqtt.api.OnMessageComplete;
import de.moving.turtle.lysiana.mqtt.api.Topic;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Data
@RequiredArgsConstructor
public class MqttSubscriber implements MqttCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttSubscriber.class);

    private MqttConnectOptions conOpt;
    private final MqttClient client;
    private final Topic topic;
    private final OnMessageArrived onMessageArrived;
    private final OnMessageComplete onDeliveryComplete;
    private final OnConnectionLost onConnectionLost;

    @PostConstruct
    public void post() throws MqttException {
        this.conOpt = new MqttConnectOptions();
        this.conOpt.setCleanSession(true);
        client.setCallback(this);
        connect(conOpt);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        try {
            onConnectionLost.onLost(throwable, topic);
            connect(conOpt);
        } catch (MqttException e) {
            LOGGER.warn("Exception occured for topic '{}'", topic.getName(), e);
        }

    }

    @Override
    public void messageArrived(String s, MqttMessage message) {
        onMessageArrived.onArrived(s, message, topic);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        onDeliveryComplete.onComplete(iMqttDeliveryToken, topic);
    }

    private void connect(MqttConnectOptions conOpt) throws MqttException {
        if(!this.client.isConnected()) {
            this.client.connect(conOpt);
            this.client.subscribe(topic.getName(), 1);
        }
    }
}
