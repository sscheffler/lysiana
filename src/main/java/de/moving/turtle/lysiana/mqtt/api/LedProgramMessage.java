package de.moving.turtle.lysiana.mqtt.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ledProgramBuilder")
public class LedProgramMessage {
    public enum ACTION {
        START, STOP
    }
    private String program;
    private ACTION action;
}
