package faang.school.analytics.event_drive.redis.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorshipRequestedEvent {

    @NotNull
    private long receiverId;

    @NotNull
    private long actorId;

    @NotNull
    private LocalDateTime receivedAt;
}