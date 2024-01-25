package com.tokio.technicaltest.app.controller;

import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import com.tokio.technicaltest.app.mapper.SchedulingRequestMapper;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tokio.technicaltest.domain.utils.LogUtils.FINISHED;
import static com.tokio.technicaltest.domain.utils.LogUtils.STARTED;
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
    public ResponseEntity<List<SchedulingRequestAndResponse>> findSchedules() {

        final var start = now();
        log.info("status={}", STARTED);

        var schedules = schedulingInboundPort.findSchedules().stream()
                .map(SchedulingRequestMapper::fromSchedulingToScheduleResponse)
                .toList();

        log.info("status={}, timeMillis={} ", FINISHED, start.until(now(), MILLIS));
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

}
