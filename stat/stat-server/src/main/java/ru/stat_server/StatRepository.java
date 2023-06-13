package ru.stat_server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.stat_dto.ViewStats;
import ru.stat_server.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatRepository extends JpaRepository<EndpointHit, Long> {

    @Query(value = "select new ru.stat_dto.ViewStats(i.app, i.uri, count(i.ip)) " +
            "from EndpointHit i " +
            "where i.timestamp > ?1 and i.timestamp < ?2 and i.uri = ?3 " +
            "group by i.app, i.uri")
    List<ViewStats> getEndpointHitWithUris(LocalDateTime start, LocalDateTime end, String uri);

    @Query(value = "select new ru.stat_dto.ViewStats(i.app, i.uri, count(distinct  i.ip)) " +
            "from EndpointHit i " +
            "where i.timestamp > ?1 and i.timestamp < ?2 and i.uri =?3 " +
            "group by i.app, i.uri")
    List<ViewStats> getEndpointHitByWithUrisDistinct(LocalDateTime start, LocalDateTime end, String uri);

    @Query(value = "select new ru.stat_dto.ViewStats(i.app, i.uri, count(distinct  i.ip)) " +
            "from EndpointHit i " +
            "where i.timestamp > ?1 and i.timestamp < ?2 " +
            "group by i.app, i.uri")
    List<ViewStats> getEndpointHitByWithoutUrisDistinct(LocalDateTime start, LocalDateTime end);

    @Query(value = "select new ru.stat_dto.ViewStats(i.app, i.uri, count(i.ip)) " +
            "from EndpointHit i " +
            "where i.timestamp > ?1 and i.timestamp < ?2 " +
            "group by i.app, i.uri")
    List<ViewStats> getEndpointHitByWithoutUris(LocalDateTime start, LocalDateTime end);



}
