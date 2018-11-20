package de.moving.turtle.lysiana.suncalc.http;

import de.moving.turtle.lysiana.suncalc.http.SuncalcResource;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResponse;
import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.core.MediaType.*;

@Component
class SuncalcResourceImpl implements SuncalcResource {

    private static final String BASE = "https://api.sunrise-sunset.org";
    private final WebTarget target;

    SuncalcResourceImpl() {
        // https://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400
        final Client client = ClientBuilder.newClient();

        this.target = client
                .target(BASE);
    }

    @Override
    public SuncalcResults byCordinates(final String lat, final String lng) {
        return target
                .path("json")
                .queryParam("lat", lat)
                .queryParam("lng", lng)
                .request(APPLICATION_JSON_TYPE)
                .get(SuncalcResponse.class)
                .getResults();
    }
}
