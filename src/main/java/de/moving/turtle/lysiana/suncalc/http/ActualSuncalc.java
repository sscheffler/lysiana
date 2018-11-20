package de.moving.turtle.lysiana.suncalc.http;

import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;

import java.util.function.BiFunction;

import static java.util.Optional.*;

public class ActualSuncalc {
    private static final String LONGITUDE = "-4.4203400D";
    private static final String LATITUDE = "36.7201600";

    private SuncalcResults results;
    private final BiFunction<String, String, SuncalcResults> resultsBiFunction;

    public ActualSuncalc(final BiFunction<String, String, SuncalcResults> resultsBiFunction) {
        this.resultsBiFunction = resultsBiFunction;
    }

    public SuncalcResults getResults() {
        return ofNullable(results).orElse(this.update());
    }

    public void setResults(final SuncalcResults results) {
        this.results = results;
    }

    public SuncalcResults update(){
        this.results = resultsBiFunction.apply(LONGITUDE, LATITUDE);
        return this.results;
    }
}
