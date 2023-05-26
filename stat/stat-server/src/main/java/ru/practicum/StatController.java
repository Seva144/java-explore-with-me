package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class StatController {

    private final StatService statService;

    @PostMapping(value = "/hit")
    public void addEndpointHit(@RequestBody EndpointHitDto hit) {
        log.info("Сохранение запроса пользователя {}", hit);
        statService.addEndpointHit(hit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getViews(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Показать статитстику");
        return statService.getHits(start, end, uris, unique);
    }


}
