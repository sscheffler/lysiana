package de.moving.turtle.lysiana.suncalc.http.api;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class SuncalcResponse {

    SuncalcResults results;
    private String status;

}
