package ru.main_service.model.dto;

import lombok.Data;

@Data
public class EventShortDto {

    private Long id;

    private String annotation;

    private Integer confirmedRequests;

    private CategoryDto category;

    private String eventDate;

    private UserDto initiator;

    private Boolean paid;

    private String title;

    private Long views;
}
