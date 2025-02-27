package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.PostViewEvent;
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
public class PostViewEventPublisher extends AbstractEventListener<PostViewEvent> {

    @Value("${spring.data.redis.channel.post-view}")
    private String postViewChannel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public PostViewEventPublisher(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
            ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        PostViewEvent event = mapMessage(message, PostViewEvent.class);
        save(analyticsEventMapper.postViewToAnalyticsEvent(event));
        log.info("Post view event saved to tadabase: {}", event.getClass().getName());
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(postViewChannel);
    }
}
