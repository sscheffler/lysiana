package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.suncalc.http.ActualSuncalc;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SuncalcScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuncalcScheduler.class);

    private final ActualSuncalc actualSuncalc;

    public SuncalcScheduler(final ActualSuncalc actualSuncalc) {
        this.actualSuncalc = actualSuncalc;
    }

    @Scheduled(cron = "${schedule.suncalc}")
    public void schedule(){
        final SuncalcResults suncalcResults = actualSuncalc.update();
        LOGGER.info("Response: {}", suncalcResults);
    }


}
