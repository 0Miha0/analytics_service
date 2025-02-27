package faang.school.analytics.event_drive.redis.events;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompletedTaskEvent {

    @NotNull
    private Long goalId;

    @NotNull
    private Long actorId;

    @NotNull
    private LocalDateTime receivedAt;
}
