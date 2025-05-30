package faang.school.analytics.event_drive.redis.events;

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
public class ProfileViewEvent {

    @NotNull
    private Long receiverId;

    @NotNull
    private Long actorId;

    @NotNull
    private LocalDateTime receivedAt;
}
