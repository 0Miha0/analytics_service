package faang.school.analytics.service;

import faang.school.analytics.model.AnalyticsEvent;
import faang.school.analytics.repository.AnalyticsEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsEventService {

    private final AnalyticsEventRepository analyticsEventRepository;

    public List<AnalyticsEvent> getAnalytics(String eventType, Long receiverId, LocalDateTime startTime, LocalDateTime endTime) {
        return analyticsEventRepository.findAnalytics(eventType, receiverId, startTime, endTime);
    }

    public void saveEvent(AnalyticsEvent analyticsEvent) {
        analyticsEventRepository.save(analyticsEvent);
    }
}
