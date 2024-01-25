package com.tokio.technicaltest.domain.port.outbound;

import com.tokio.technicaltest.domain.model.TransferRate;

import java.util.List;

public interface TransferRatePersistencePort {

    List<TransferRate> retrieveAllTransferRates();

}
