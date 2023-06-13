package ru.main_service.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CompilationDto {

    private Long id;

    private List<EventShortDto> events;

    private Boolean pinned;

    private String title;

}
