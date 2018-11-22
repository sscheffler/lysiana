package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.mqtt.MqttPublisher;
import de.moving.turtle.lysiana.suncalc.http.ActualSuncalc;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static de.moving.turtle.lysiana.mqtt.api.LoggingTopic.LOGGING_TOPIC;
import static java.lang.String.format;

@Component
public class SuncalcScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuncalcScheduler.class);

    private final ActualSuncalc actualSuncalc;

    private int qos = 2;

    @Autowired
    private MqttPublisher mqttPublisher;

    public SuncalcScheduler(final ActualSuncalc actualSuncalc) {
        this.actualSuncalc = actualSuncalc;
    }

    @Scheduled(cron = "${schedule.suncalc}")
    public void schedule(){

        final SuncalcResults suncalcResults = actualSuncalc.getResults();
        LOGGER.info("Response: {}", suncalcResults);
        final MqttMessage message = new MqttMessage(format("Received twilight begin: '%s'", suncalcResults.getCivilTwilightBegin()).getBytes());
        message.setQos(qos);
        mqttPublisher.publish(LOGGING_TOPIC, message);
    }


}
