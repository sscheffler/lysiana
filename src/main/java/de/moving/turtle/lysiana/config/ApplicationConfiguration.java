package de.moving.turtle.lysiana.config;

import de.moving.turtle.lysiana.suncalc.http.ActualSuncalc;
import de.moving.turtle.lysiana.suncalc.http.SuncalcResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {


    @Autowired
    private SuncalcResource suncalcResource;

    @Bean("actualSunCalc")
    public ActualSuncalc actualSuncalc() {
        return new ActualSuncalc((a,b) -> suncalcResource.byCordinates(a, b));
    }
}
