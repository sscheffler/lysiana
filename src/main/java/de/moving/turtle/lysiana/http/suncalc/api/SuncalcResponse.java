package de.moving.turtle.lysiana.http.suncalc.api;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class SuncalcResponse {

    SuncalcResults results;
    private String status;

}
