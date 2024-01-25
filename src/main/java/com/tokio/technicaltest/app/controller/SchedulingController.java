package com.tokio.technicaltest.app.controller;

import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import com.tokio.technicaltest.app.mapper.SchedulingRequestMapper;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
public class SchedulingController {

    private final SchedulingInboundPort schedulingInboundPort;

    public SchedulingController(SchedulingInboundPort schedulingInboundPort) {
        this.schedulingInboundPort = schedulingInboundPort;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SchedulingRequestAndResponse>> findSchedules() {
        var schedules = schedulingInboundPort.findSchedules().stream()
                .map(SchedulingRequestMapper::fromSchedulingToScheduleResponse)
                .toList();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

}
