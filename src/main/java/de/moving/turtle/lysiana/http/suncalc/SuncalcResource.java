package de.moving.turtle.lysiana.http.suncalc;

import de.moving.turtle.lysiana.http.suncalc.api.SuncalcResults;

public interface SuncalcResource {

    SuncalcResults byCordinates(String lat, String lng);
}
