package ru.main_service.controllers.authorized;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import ru.main_service.model.dto.ParticipationRequestDto;
import org.springframework.web.bind.annotation.*;
import ru.main_service.services.ParticipationService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class PrivateRequestsController {

    private final ParticipationService participationService;

    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> getRequestsByUser(@PathVariable Long userId) {
        return participationService.getAllRequestsByUser(userId);
    }

    @PostMapping("/{userId}/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequest(@PathVariable Long userId, @RequestParam Long eventId) {
        return participationService.addRequestByUserForEvent(userId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable Long userId, @PathVariable Long requestId) {
        return participationService.canselRequestByUserForEvent(userId, requestId);
    }
}
