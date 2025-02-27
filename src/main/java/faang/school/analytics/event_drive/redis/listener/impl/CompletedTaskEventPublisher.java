package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.CompletedTaskEvent;
import faang.school.analytics.event_drive.redis.listener.AbstractEventListener;
import faang.school.analytics.mapper.AnalyticsEventMapper;
import faang.school.analytics.service.AnalyticsEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompletedTaskEventPublisher extends AbstractEventListener<CompletedTaskEvent> {

    @Value("${spring.data.redis.channel.completed-task}")
    private String completedEventChannel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public CompletedTaskEventPublisher(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
    ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(completedEventChannel);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        CompletedTaskEvent event = mapMessage(message, CompletedTaskEvent.class);
        analyticsEventMapper.completedTaskToAnalyticsEvent(event);
    }
}
