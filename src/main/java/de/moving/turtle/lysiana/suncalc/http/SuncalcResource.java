package de.moving.turtle.lysiana.suncalc.http;

import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;

public interface SuncalcResource {

    SuncalcResults byCordinates(String lat, String lng);
}
