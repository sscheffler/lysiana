package de.moving.turtle.lysiana.http.suncalc;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class SuncalcResponse {

    SuncalcResults results;
    private String status;

}
