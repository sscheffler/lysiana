package de.moving.turtle.lysiana.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.util.UUID;

public class MqttClientFactory extends AbstractFactoryBean<MqttClient> {

    private final String clientId;
    private final String broker;

    public MqttClientFactory(final String clientId, final String broker) {
        this.clientId = clientId;
        this.broker = broker;
    }

    @Override
    public Class<?> getObjectType() {
        return MqttClient.class;
    }

    @Override
    protected MqttClient createInstance() throws Exception {
        return new MqttClient(broker, clientId + "-"+ UUID.randomUUID().toString(), new MemoryPersistence());
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
