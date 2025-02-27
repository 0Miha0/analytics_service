package faang.school.analytics.mapper;

import faang.school.analytics.event_drive.redis.events.CommentEvent;
import faang.school.analytics.event_drive.redis.events.CompletedTaskEvent;
import faang.school.analytics.event_drive.redis.events.LikeEvent;
import faang.school.analytics.event_drive.redis.events.MentorshipRequestedEvent;
import faang.school.analytics.event_drive.redis.events.PostViewEvent;
import faang.school.analytics.event_drive.redis.events.ProfileViewEvent;
import faang.school.analytics.event_drive.redis.events.ProjectViewEvent;
import faang.school.analytics.model.AnalyticsEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnalyticsEventMapper {

    @Mapping(target = "eventType", constant = "MENTORSHIP_REQUEST")
    AnalyticsEvent mentorshipRequestToAnalyticsEvent(MentorshipRequestedEvent mentorshipRequestedEvent);

    @Mapping(target = "eventType", constant = "POST_VIEW")
    @Mapping(target = "receiverId", source = "postId")
    AnalyticsEvent postViewToAnalyticsEvent(PostViewEvent postViewEventEvent);

    @Mapping(target = "eventType", constant = "POST_COMMENT")
    @Mapping(target = "receiverId", source = "commentId")
    AnalyticsEvent commentToAnalyticsEvent(CommentEvent commentEvent);

    @Mapping(target = "eventType", constant = "POST_LIKE")
    @Mapping(target = "receiverId", source = "postId")
    AnalyticsEvent likeToAnalyticsEvent(LikeEvent postViewEventEvent);

    @Mapping(target = "eventType", constant = "POST_VIEW")
    AnalyticsEvent profileViewToAnalyticsEvent(ProfileViewEvent postViewEvent);

    @Mapping(target = "eventType" , constant = "TASK_COMPLETED")
    @Mapping(target = "receiverId", source = "goalId")
    AnalyticsEvent completedTaskToAnalyticsEvent(CompletedTaskEvent completedTaskEvent);

    @Mapping(target = "eventType", constant = "PROFILE_VIEW")
    @Mapping(target = "receiverId", source = "projectId")
    AnalyticsEvent projectViewToAnalyticsEvent(ProjectViewEvent projectViewEvent);
}
