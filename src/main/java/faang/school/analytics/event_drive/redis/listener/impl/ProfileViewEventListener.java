package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.PostViewEvent;
import faang.school.analytics.event_drive.redis.events.ProfileViewEvent;
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
public class ProfileViewEventListener extends AbstractEventListener<ProfileViewEvent> {

    @Value("${spring.data.redis.channel.profile-view}")
    private String profileViewChannel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public ProfileViewEventListener(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
    ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(profileViewChannel);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        ProfileViewEvent event = mapMessage(message, ProfileViewEvent.class);
        save(analyticsEventMapper.profileViewToAnalyticsEvent(event));
    }
}
