package com.tokio.technicaltest.integration;

import com.tokio.technicaltest.FixtureUtils;
import com.tokio.technicaltest.app.controller.SchedulingController;
import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import com.tokio.technicaltest.domain.utils.TransferStatus;
import com.tokio.technicaltest.infra.db.repository.SchedulingRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static com.tokio.technicaltest.integration.IntegrationHelper.QUERY_DELETE_SCHEDULES;
import static com.tokio.technicaltest.integration.IntegrationHelper.QUERY_INSERT_SCHEDULES;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SchedulingIntegrationTest {

    @Inject
    private SchedulingController schedulingController;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    private SchedulingRepository schedulingRepository;

    @Test
    void whenRequestGetSchedules_thenReturnAllSchedules() {

        truncateData();
        insertSchedules();

        var response = schedulingController.findSchedules();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void whenRequestGetSchedules_AndHasEmpty_thenReturnNoReturnSchedules() {

        truncateData();

        var response = schedulingController.findSchedules();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(0, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void giveAnValidRequest_whenProcessSaveSchedules_thenReturnSuccess() throws IOException {

        truncateData();

        var request = FixtureUtils.readObjectFromFile(
                "fixtures", "valid-scheduling-request.json", SchedulingRequestAndResponse.class);

        request.setTransferDate(LocalDate.now().plusDays(5));

        var response = schedulingController.createScheduling(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void giveAnValidRequest_whenProcessNotValidSchedulePeriod_thenNoCreateScheduling() throws IOException {

        truncateData();

        var request = FixtureUtils.readObjectFromFile(
                "fixtures", "valid-scheduling-request.json", SchedulingRequestAndResponse.class);

        request.setTransferDate(LocalDate.now().plusDays(100));

        var response = schedulingController.createScheduling(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Invalid scheduling period", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(0, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void giveAnValidUuid_whenProcessCancelSchedules_thenReturnSuccess() throws IOException {

        truncateData();
        insertSchedules();

        var response = schedulingController.deleteScheduling(UUID.fromString("0f373186-17fe-4984-8780-f5c423955462"));

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Scheduling canceled with success", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(0, Objects.requireNonNull(response.getBody()).getData().size());

        var scheduling = schedulingRepository.findById(
                UUID.fromString("0f373186-17fe-4984-8780-f5c423955462")).orElseThrow();

        assertEquals(TransferStatus.CANCELED, scheduling.getTransferStatus());


    }

    @Test
    void giveAInvalidUuid_whenProcessCancelSchedules_thenReturnSuccess() throws IOException {

        truncateData();
        insertSchedules();

        var response = schedulingController.deleteScheduling(UUID.fromString("0f373186-17fe-4984-8780-f5c423955465"));

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Some problem when try to cancel scheduling", Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(0, Objects.requireNonNull(response.getBody()).getData().size());

    }

    private void truncateData() {
        jdbcTemplate.update(QUERY_DELETE_SCHEDULES);
    }

    private void insertSchedules() {
        jdbcTemplate.update(QUERY_INSERT_SCHEDULES);
    }

}
