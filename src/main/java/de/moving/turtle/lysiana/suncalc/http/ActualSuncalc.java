package de.moving.turtle.lysiana.suncalc.http;

import de.moving.turtle.lysiana.suncalc.http.api.SuncalcResults;
import org.springframework.cache.annotation.Cacheable;

import java.util.function.BiFunction;

/**
 * {@link ActualSuncalc#getResults()} cached by 'suncalcResultsCache'
 */
public class ActualSuncalc {
    private static final String LONGITUDE = "13.737262 ";
    private static final String LATITUDE = "51.050407";

    private final BiFunction<String, String, SuncalcResults> resultsBiFunction;

    public ActualSuncalc(final BiFunction<String, String, SuncalcResults> resultsBiFunction) {
        this.resultsBiFunction = resultsBiFunction;
    }

    @Cacheable("suncalcResultsCache")
    public SuncalcResults getResults() {
        return resultsBiFunction.apply(LONGITUDE, LATITUDE);
    }
}
