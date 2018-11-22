package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.suncalc.http.ActualSuncalc;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static de.moving.turtle.lysiana.mqtt.api.LoggingTopic.LOGGING_TOPIC;

@Component
public class SuncalcScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuncalcScheduler.class);

    private final ActualSuncalc actualSuncalc;

    public SuncalcScheduler(final ActualSuncalc actualSuncalc) {
        this.actualSuncalc = actualSuncalc;
    }

    private int qos = 2;

    private String clientId;

    @Autowired
    private MqttClient mqttClient;

    @Scheduled(cron = "${schedule.suncalc}")
    public void schedule(){

        try {
            try {
                final MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);

                mqttClient.connect(connOpts);
                final MqttMessage message = new MqttMessage("test message".getBytes());
                message.setQos(qos);

                mqttClient.publish(LOGGING_TOPIC.getName(), message);
            } finally {
                mqttClient.disconnect();
            }
        } catch(MqttException me) {
            LOGGER.warn("Exception occurred", me);
        }
        final SuncalcResults suncalcResults = actualSuncalc.getResults();
        LOGGER.info("Response: {}", suncalcResults);
    }


}
