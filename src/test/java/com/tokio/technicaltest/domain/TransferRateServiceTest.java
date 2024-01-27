package com.tokio.technicaltest.domain;

import com.tokio.technicaltest.MainHelper;
import com.tokio.technicaltest.domain.port.outbound.TransferRatePersistencePort;
import com.tokio.technicaltest.domain.service.TransferRateService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferRateServiceTest {

    @Inject
    TransferRateService transferRateService;

    @MockBean
    TransferRatePersistencePort transferRatePersistencePort;

    @Test
    void giveAnValidPeriod_whenProcess_thenReturnSuccess() {

        when(transferRatePersistencePort.retrieveAllTransferRates()).thenReturn(MainHelper.buildTransferRateList());

        var transferRate = transferRateService.retrieveTransferRateByPeriod(0);

        assertEquals(1, transferRate.getId());
        assertEquals(new BigDecimal("2.5"), transferRate.getTransferRate());

    }

    @Test
    void giveAnInvalidPeriod_whenProcess_thenReturnSuccess() {

        when(transferRatePersistencePort.retrieveAllTransferRates()).thenReturn(MainHelper.buildTransferRateList());

        var transferRate = transferRateService.retrieveTransferRateByPeriod(1);

        assertNull(transferRate.getId());

    }

}
