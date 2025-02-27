package faang.school.analytics.event_drive.redis.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.model.AnalyticsEvent;
import faang.school.analytics.service.AnalyticsEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractEventListener<T> implements MessageListener, RedisContainerMessageListener {

    private final ObjectMapper objectMapper;
    private final AnalyticsEventService analyticsEventService;

    public T mapMessage(Message message, Class<T> eventType) {
        try {
            log.info("Received message: {}", Arrays.toString(message.getBody()));
            return objectMapper.readValue(message.getBody(), eventType);
        } catch (IOException e) {
            log.warn("Failed to deserialize event", e);
            throw new IllegalArgumentException("Failed to deserialize message body", e);
        }
    }

    public void save(AnalyticsEvent analyticsEvent) {
        log.info("Saving event: {}", analyticsEvent.getClass().getName());
        analyticsEventService.saveEvent(analyticsEvent);
        log.info("Event saved: {}", analyticsEvent.getClass().getName());
    }
}
