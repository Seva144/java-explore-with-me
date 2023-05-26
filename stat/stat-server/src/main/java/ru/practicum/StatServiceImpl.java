package ru.practicum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    @Override
    public void addEndpointHit(EndpointHitDto hit) {
        statRepository.save(StatMapper.endpointHitDtoToModel(hit));
    }

    @Override
    public List<ViewStats> getHits(LocalDateTime start,
                                   LocalDateTime end, List<String> uris, Boolean unique) {
        List<ViewStats> hits = new ArrayList<>();
        if (unique) {
            if (uris == null) hits.addAll(statRepository.getEndpointHitByWithoutUrisDistinct(start, end));
            else for (String uri : uris) {
                hits.addAll(statRepository.getEndpointHitByWithUrisDistinct(start, end, uri));
            }
        } else {
            if (uris == null) hits.addAll(statRepository.getEndpointHitByWithoutUris(start, end));
            else for (String uri : uris) {
                hits.addAll(statRepository.getEndpointHitWithUris(start, end, uri));
            }
        }
        return hits.stream().sorted(Comparator
                        .comparingLong(ViewStats::getHits).reversed())
                .collect(Collectors.toList());
    }
}
