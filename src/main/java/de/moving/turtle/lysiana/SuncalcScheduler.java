package de.moving.turtle.lysiana;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.moving.turtle.lysiana.mqtt.MqttPublisher;
import de.moving.turtle.lysiana.suncalc.http.ActualSuncalc;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static de.moving.turtle.lysiana.mqtt.api.ActivateLightProgramTopic.ACTIVATE_LIGHT_PROGRAM_TOPIC;
import static de.moving.turtle.lysiana.mqtt.api.LedProgramMessage.ACTION.START;
import static de.moving.turtle.lysiana.mqtt.api.LedProgramMessage.ACTION.STOP;
import static de.moving.turtle.lysiana.mqtt.api.LedProgramMessage.LedProgramMessageBuilder;
import static de.moving.turtle.lysiana.mqtt.api.LedProgramMessage.ledProgramBuilder;
import static de.moving.turtle.lysiana.mqtt.api.LedPrograms.EVENING;
import static java.time.LocalDateTime.now;

@Component
public class SuncalcScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuncalcScheduler.class);

    private final ActualSuncalc actualSuncalc;

    private int qos = 2;

    private final MqttPublisher mqttPublisher;

    @Autowired
    public SuncalcScheduler(final ActualSuncalc actualSuncalc, MqttPublisher mqttPublisher) {
        this.actualSuncalc = actualSuncalc;
        this.mqttPublisher = mqttPublisher;
    }

    @Scheduled(cron = "${schedule.suncalc}")
    public void schedule(){
        final SuncalcResults suncalcResults = actualSuncalc.getResults();
        LOGGER.debug("Suncalc response: {}", suncalcResults);

        final LedProgramMessageBuilder ledProgramMessageBuilder = ledProgramBuilder()
                .program(EVENING);

        if(now().isAfter(suncalcResults.getCivilTwilightBegin())) {
            ledProgramMessageBuilder.action(START);
        } else {
            ledProgramMessageBuilder.action(STOP);
        }

        try {
            final MqttMessage message = new MqttMessage(
                    new ObjectMapper()
                            .writeValueAsBytes(ledProgramMessageBuilder.build())
            );
            message.setQos(qos);
            mqttPublisher.publish(ACTIVATE_LIGHT_PROGRAM_TOPIC, message);
        } catch (JsonProcessingException pe) {
            LOGGER.warn("Could not create JSON", pe);
        }
    }
}
