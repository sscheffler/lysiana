package de.moving.turtle.lysiana.api.suncalc;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class SuncalcResponse {

    SuncalcResults results;
    private String status;

}
