package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.MentorshipRequestedEvent;
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
public class MentorshipRequestedEventListener extends AbstractEventListener<MentorshipRequestedEvent> {

    @Value("${spring.data.redis.channel.mentorship-requested}")
    private String mentorshipRequestedChannel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public MentorshipRequestedEventListener(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
    ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        MentorshipRequestedEvent event = mapMessage(message, MentorshipRequestedEvent.class);
        save(analyticsEventMapper.mentorshipRequestToAnalyticsEvent(event));
        log.info("Mentorship requested event saved to database: {}", event.getClass().getName());
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(mentorshipRequestedChannel);
    }
}
