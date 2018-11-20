package de.moving.turtle.lysiana.api.suncalc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuncalcResults {

    private String sunrise;
    private String sunset;
    @JsonProperty("solar_noon")
    private String solarNoon;
    @JsonProperty("astronomical_twilight_begin")
    private String astronomicalTwilightBegin;
    @JsonProperty("astronomical_twilight_end")
    private String astronomicalTwilightEnd;
    @JsonProperty("day_length")
    private String dayLength;
    @JsonProperty("civil_twilight_begin")
    private String civilTwilightBegin;
    @JsonProperty("civil_twilight_end")
    private String civilTwilightEnd;

}
