package ru.main_service.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventFullDto {

    private Long id;

    private String annotation;

    private CategoryDto category;

    private Integer confirmedRequests;

    private LocalDateTime createdOn;

    private String description;

    private String eventDate;

    private UserDto initiator;

    private Location location;

    private Boolean paid;

    private Integer participantLimit;

    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private String state;

    private String title;

    private Long views;

    @Data
    public static class Location {

        private Double lat;

        private Double lon;
    }
}
