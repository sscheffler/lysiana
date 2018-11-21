package de.moving.turtle.lysiana;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class TestMqttSubscriber implements MqttCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMqttSubscriber.class);

    @Value("${mqtt.broker}")
    private String broker;
    @Value("${mqtt.clientId}")
    private String clientId;
    private MqttClient client;

    @PostConstruct
    public void post() throws MqttException {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);

        this.client = new MqttClient(broker, UUID.randomUUID().toString(), new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);

        this.client.subscribe("topic.logging", 1);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        LOGGER.warn("Lost Connection", throwable);
    }

    @Override
    public void messageArrived(String s, MqttMessage message) throws Exception {
        System.out.println(String.format("[%s] %s", "topic.logging", new String(message.getPayload())));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
