package com.tokio.technicaltest.domain;

import com.tokio.technicaltest.FixtureUtils;
import com.tokio.technicaltest.MainHelper;
import com.tokio.technicaltest.domain.model.Scheduling;
import com.tokio.technicaltest.domain.model.TransferRate;
import com.tokio.technicaltest.domain.port.outbound.SchedulingPersistencePort;
import com.tokio.technicaltest.domain.service.SchedulingService;
import com.tokio.technicaltest.domain.service.TransferRateService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SchedulingServiceTest {

    @Inject
    SchedulingService schedulingService;

    @MockBean
    SchedulingPersistencePort schedulingPersistencePort;

    @MockBean
    TransferRateService transferRateService;

    @Test
    void giveAnValidScheduling_whenProcess_thenReturnSuccess() throws IOException {

        var schedulingRequest = FixtureUtils.readObjectFromFile(
                "fixtures", "valid-scheduling-request.json", Scheduling.class);
        schedulingRequest.setUuid(null);
        schedulingRequest.setTransferDate(LocalDate.now());

        when(schedulingPersistencePort.saveScheduling(any())).thenReturn(MainHelper.buildScheduling());
        when(transferRateService.retrieveTransferRateByPeriod(any())).thenReturn(MainHelper.buildTransferRate());

        var schedulingPersisted = schedulingService.saveScheduling(schedulingRequest);

        assertEquals(UUID.fromString("f93bd959-2e22-40f3-8483-86f3c0dd6d4f"), schedulingPersisted.getUuid());
        assertEquals(LocalDate.of(2024, 1, 25), schedulingPersisted.getTransferDate());

        verify(transferRateService, times(1)).retrieveTransferRateByPeriod(any());
        verify(schedulingPersistencePort, times(1)).saveScheduling(any());

    }

    @Test
    void giveAInvalidScheduling_whenProcess_thenReturnNull() throws IOException {

        var schedulingRequest = FixtureUtils.readObjectFromFile(
                "fixtures", "valid-scheduling-request.json", Scheduling.class);
        schedulingRequest.setUuid(null);
        schedulingRequest.setSchedulingDate(LocalDate.now().plusDays(100));

        when(schedulingPersistencePort.saveScheduling(any())).thenReturn(MainHelper.buildScheduling());
        when(transferRateService.retrieveTransferRateByPeriod(any())).thenReturn(TransferRate.builder().build());

        var schedulingPersisted = schedulingService.saveScheduling(schedulingRequest);

        assertNull(schedulingPersisted.getUuid());

        verify(transferRateService, times(1)).retrieveTransferRateByPeriod(any());
        verify(schedulingPersistencePort, never()).saveScheduling(any());

    }


}
