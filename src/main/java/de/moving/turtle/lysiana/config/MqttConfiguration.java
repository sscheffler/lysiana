package de.moving.turtle.lysiana.config;

import de.moving.turtle.lysiana.mqtt.MqttClientFactory;
import de.moving.turtle.lysiana.mqtt.MqttSubscriber;
import de.moving.turtle.lysiana.mqtt.api.LoggingTopic;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MqttConfiguration {

    @Value("${mqtt.broker}")
    private String broker;
    @Value("${mqtt.clientId}")
    private String clientId;

    @Bean("loggingSubscriber")
    public MqttSubscriber loggingSubscriber(MqttClient mqttClient){
        return new MqttSubscriber(
                mqttClient,
                new LoggingTopic(),
                (t,v,u) -> System.out.println("Message arrived: "+ Arrays.toString(v.getPayload())),
                (t,v) -> System.out.println("Delivery complete for " + v.getName()),
                (t,v) -> System.out.println("Lost connection for " + v.getName()));
    }

    @Bean
    public MqttClientFactory mqttClientFactory(){
        return new MqttClientFactory(clientId, broker);
    }
}
