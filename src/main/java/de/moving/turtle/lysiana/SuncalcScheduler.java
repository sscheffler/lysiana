package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.suncalc.http.ActualSuncalc;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SuncalcScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuncalcScheduler.class);

    private final ActualSuncalc actualSuncalc;

    public SuncalcScheduler(final ActualSuncalc actualSuncalc) {
        this.actualSuncalc = actualSuncalc;
    }

    int qos             = 2;

    @Value("${mqtt.broker}")
    private String broker;
    @Value("${mqtt.clientId}")
    private String clientId;
    MemoryPersistence persistence = new MemoryPersistence();

    @Scheduled(cron = "${schedule.suncalc}")
    public void schedule(){

        try {
            MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
            try {
                final MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);

                mqttClient.connect(connOpts);
                final MqttMessage message = new MqttMessage("test message".getBytes());
                message.setQos(qos);

                mqttClient.publish("topic.logging", message);
            } finally {
                mqttClient.disconnect();
            }
        } catch(MqttException me) {
            LOGGER.warn("Exception occured", me);
        }
        final SuncalcResults suncalcResults = actualSuncalc.getResults();
        LOGGER.info("Response: {}", suncalcResults);
    }


}
