package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.CommentEvent;
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
public class CommentEventListener extends AbstractEventListener<CommentEvent> {

    @Value("${spring.data.redis.channel.comment}")
    private String commentEventChannel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public CommentEventListener(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
    ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        CommentEvent event = mapMessage(message, CommentEvent.class);
        save(analyticsEventMapper.commentToAnalyticsEvent(event));
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(commentEventChannel);
    }
}
