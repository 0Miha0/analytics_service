package faang.school.analytics.controller;

import faang.school.analytics.model.AnalyticsEvent;
import faang.school.analytics.service.AnalyticsEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Api analytics")
@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsEventController {

    private final AnalyticsEventService analyticsEventService;

    @Operation(summary = "Get analytics")
    @GetMapping
    public ResponseEntity<List<AnalyticsEvent>> getAnalytics(
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) Long receiverId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime
            ) {
        return ResponseEntity.ok(analyticsEventService.getAnalytics(eventType, receiverId, startTime, endTime));
    }
}
