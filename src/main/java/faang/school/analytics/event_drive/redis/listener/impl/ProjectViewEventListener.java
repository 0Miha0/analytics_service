package faang.school.analytics.event_drive.redis.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.analytics.event_drive.redis.events.ProjectViewEvent;
import faang.school.analytics.event_drive.redis.listener.AbstractEventListener;
import faang.school.analytics.mapper.AnalyticsEventMapper;
import faang.school.analytics.service.AnalyticsEventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class ProjectViewEventListener extends AbstractEventListener<ProjectViewEvent> {

    @Value("${spring.data.redis.channel.project_views}")
    private String projectViewsChannel;

    private final AnalyticsEventMapper analyticsEventMapper;

    public ProjectViewEventListener(
            ObjectMapper objectMapper,
            AnalyticsEventService analyticsEventService,
            AnalyticsEventMapper analyticsEventMapper
    ) {
        super(objectMapper, analyticsEventService);
        this.analyticsEventMapper = analyticsEventMapper;
    }

    @Override
    public ChannelTopic getChannelTopic() {
        return new ChannelTopic(projectViewsChannel);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        ProjectViewEvent event = mapMessage(message, ProjectViewEvent.class);
        save(analyticsEventMapper.projectViewToAnalyticsEvent(event));
    }
}
