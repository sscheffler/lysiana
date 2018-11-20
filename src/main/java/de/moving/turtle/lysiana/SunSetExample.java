package de.moving.turtle.lysiana;

import de.moving.turtle.lysiana.api.suncalc.SuncalcResponse;
import de.moving.turtle.lysiana.api.suncalc.SuncalcResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Component
public class SunSetExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(SunSetExample.class);


    public SunSetExample() {
        LOGGER.info("Starting sunset example");
        // https://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400
        Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("https://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400");
        final Invocation.Builder requestBuilder = target.request(MediaType.APPLICATION_JSON_TYPE);
        final SuncalcResponse response = requestBuilder.get(SuncalcResponse.class);

        LOGGER.info("Response: {}", response.getResults());
    }
}
