package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.http.suncalc.SuncalcResource;
import de.moving.turtle.lysiana.http.suncalc.api.SuncalcResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SuncalcScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuncalcScheduler.class);

    private final SuncalcResource suncalcResource;


    public SuncalcScheduler(final SuncalcResource suncalcResource) {
        this.suncalcResource = suncalcResource;


    }

    @Scheduled(cron = "${schedule.suncalc}")
    public void schedule(){
        final SuncalcResults suncalcResults = suncalcResource.byCordinates("36.7201600", "-4.4203400D");
        LOGGER.info("Response: {}", suncalcResults);
    }


}
