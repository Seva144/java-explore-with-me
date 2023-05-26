package ru.practicum;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class EndpointHitDto {

    @NotNull(message = "App should not be null")
    String app;

    @NotNull(message = "Uri should not be null")
    String uri;

    @NotNull(message = "IP should not be null")
    String ip;

    String timestamp;

}
