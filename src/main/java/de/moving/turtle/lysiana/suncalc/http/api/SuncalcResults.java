package de.moving.turtle.lysiana.suncalc.http.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuncalcResults {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sunrise;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sunset;
    @JsonProperty("solar_noon")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime solarNoon;
    @JsonProperty("astronomical_twilight_begin")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime astronomicalTwilightBegin;
    @JsonProperty("astronomical_twilight_end")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime astronomicalTwilightEnd;
    @JsonProperty("day_length")
    private int dayLength;
    @JsonProperty("civil_twilight_begin")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime civilTwilightBegin;
    @JsonProperty("civil_twilight_end")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime civilTwilightEnd;

}
