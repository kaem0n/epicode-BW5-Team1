package bw5team1.epicenergyservices.exceptions;

import java.time.LocalDateTime;

public record ErrorsResponsePayload(String message, LocalDateTime timestamp) {}