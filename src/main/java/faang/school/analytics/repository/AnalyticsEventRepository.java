package faang.school.analytics.repository;

import faang.school.analytics.model.AnalyticsEvent;
import faang.school.analytics.model.EventType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AnalyticsEventRepository extends CrudRepository<AnalyticsEvent, Long> {

    Stream<AnalyticsEvent> findByReceiverIdAndEventType(long receiverId, EventType eventType);

    @Query("SELECT a FROM AnalyticsEvent a WHERE "
            + "(:eventType IS NULL OR a.eventType = :eventType) AND "
            + "(:receiverId IS NULL OR a.receiverId = :receiverId OR a.actorId = :receiverId) AND "
            + "(:startTime IS NULL OR a.receivedAt >= :startTime) AND "
            + "(:endTime IS NULL OR a.receivedAt <= :endTime)")
    List<AnalyticsEvent> findAnalytics(@Param("eventType") String eventType,
                                       @Param("entityId") Long receiverId,
                                       @Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime);
}
