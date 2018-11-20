package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.http.suncalc.SuncalcResource;
import de.moving.turtle.lysiana.http.suncalc.api.SuncalcResponse;
import de.moving.turtle.lysiana.http.suncalc.api.SuncalcResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Component
public class SunSetExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(SunSetExample.class);

    private final SuncalcResource suncalcResource;


    public SunSetExample(SuncalcResource suncalcResource) {
        this.suncalcResource = suncalcResource;

        final SuncalcResults suncalcResults = suncalcResource.byCordinates("36.7201600", "-4.4203400D");


        LOGGER.info("Response: {}", suncalcResults);
    }
}
