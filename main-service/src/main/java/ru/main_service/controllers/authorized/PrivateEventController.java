package ru.main_service.controllers.authorized;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import ru.main_service.model.dto.*;
import ru.main_service.services.EventService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
public class PrivateEventController {

    private final EventService eventService;

    @GetMapping()
    public List<EventShortDto> getEventsByUser(@PathVariable Long userId, @RequestParam(defaultValue = "0") int from,
                                               @RequestParam(defaultValue = "10") int size) {
        log.info("Get events by user Id {}", userId);
        return eventService.getEventsByUserId(userId, from, size);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto createEvent(@PathVariable Long userId, @Valid @RequestBody EventNewDto eventDto) {
        log.info("Add event {}", eventDto);
        return eventService.createEvent(eventDto, userId);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getInfoAboutEventByUser(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Get full info event by user Id {}", userId);
        return eventService.getEventByUserIdAndEventId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventByUser(@PathVariable Long userId, @PathVariable Long eventId,
                                          @Valid @RequestBody EventUpdateUserRequestDto requestDto) {
        log.info("Update event Id {}", eventId);
        return eventService.updateEventByUser(userId, eventId, requestDto);
    }


    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getInfoAboutRequestByEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Get participation by user Id {} and event Id {}", userId, eventId);
        return eventService.getParticipantsInEventByUser(userId, eventId);
    }


    @PatchMapping("/{eventId}/requests")
    public ChangeRequestDto changeStatusByUser(@PathVariable Long userId, @PathVariable Long eventId,
                                               @RequestBody ChangeRequestDto requestDto) {
        log.info("Update event by user Id {}", userId);
        return eventService.updateParticipantsByUser(userId, eventId,
                requestDto);
    }


//
//    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
//    public ParticipationDto confirmRequestEventByUser(@PathVariable Long userId, @PathVariable Long eventId,
//                                                      @PathVariable Long reqId) {
//        log.info("Confirmed request {} event Id {}", reqId, eventId);
//        return eventService.confirmRequestInEventByUser(userId, eventId, reqId);
//    }
//
//    @PatchMapping("/{eventId}/requests/{reqId}/reject")
//    public ParticipationDto rejectRequestEventByUser(@PathVariable Long userId, @PathVariable Long eventId,
//                                                     @PathVariable Long reqId) {
//        log.info("Reject request id {} event Id {}", reqId, eventId);
//        return eventService.rejectRequestInEventByUser(userId, eventId, reqId);
//    }
}
