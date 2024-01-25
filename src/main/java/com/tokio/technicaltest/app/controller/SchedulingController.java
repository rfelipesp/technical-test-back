package com.tokio.technicaltest.app.controller;

import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import com.tokio.technicaltest.app.mapper.SchedulingRequestMapper;
import com.tokio.technicaltest.app.utils.ApiResponse;
import com.tokio.technicaltest.app.utils.Response;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tokio.technicaltest.domain.utils.LogUtils.*;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
@RestController
@RequestMapping("/scheduler")
public class SchedulingController {

    private final SchedulingInboundPort schedulingInboundPort;

    public SchedulingController(SchedulingInboundPort schedulingInboundPort) {
        this.schedulingInboundPort = schedulingInboundPort;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<SchedulingRequestAndResponse>> findSchedules() {

        final var start = now();
        log.info("status={}", STARTED);

        Response<SchedulingRequestAndResponse> response = new Response<>();

        try {

            var schedules = schedulingInboundPort.findSchedules().stream()
                    .map(SchedulingRequestMapper::fromSchedulingToScheduleResponse)
                    .toList();

            if (schedules.isEmpty()) {
                log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
                return new ResponseEntity<>(response.ofError("Not Founded Schedules"), HttpStatus.OK);
            }

            log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
            return new ResponseEntity<>(response.ofSuccess(schedules, "Success"), HttpStatus.OK);

        } catch (Exception exception) {

            log.error("status={}, cause={}, timeMillis={}", FAILED, exception.getMessage(), start.until(now(), MILLIS));
            return new ResponseEntity<>(response.ofError("Some Error when try retrieve information"), HttpStatus.BAD_REQUEST);

        }
    }

}
