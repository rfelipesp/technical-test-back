package com.tokio.technicaltest.domain.service;

import com.tokio.technicaltest.domain.model.TransferRate;
import com.tokio.technicaltest.domain.port.outbound.TransferRatePersistencePort;
import org.springframework.stereotype.Service;

@Service
public class TransferRateService {

    private final TransferRatePersistencePort transferRatePersistencePort;

    public TransferRateService(TransferRatePersistencePort transferRatePersistencePort) {
        this.transferRatePersistencePort = transferRatePersistencePort;
    }

    public TransferRate retrieveTransferRateByPeriod(Integer period) {

        var transferRates = transferRatePersistencePort.retrieveAllTransferRates();

        var transferRateList = transferRates.stream().filter(transferRate -> transferRate.getUntilDay() >= period
                && transferRate.getFromDay() <= period).toList();

        if (transferRateList.isEmpty()) {
            return TransferRate.builder().build();
        } else {
            return transferRateList.get(0);
        }
    }

}
