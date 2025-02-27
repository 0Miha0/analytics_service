package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.LikeEvent;
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
public class LikeEventListener extends AbstractEventListener<LikeEvent> {

    @Value("${spring.data.redis.channel.like}")
    private String channel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public LikeEventListener(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
    ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(channel);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        LikeEvent event = mapMessage(message, LikeEvent.class);
        save(analyticsEventMapper.likeToAnalyticsEvent(event));
    }
}
