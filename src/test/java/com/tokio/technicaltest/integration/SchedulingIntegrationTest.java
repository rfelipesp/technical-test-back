package com.tokio.technicaltest.integration;

import com.tokio.technicaltest.FixtureUtils;
import com.tokio.technicaltest.app.controller.SchedulingController;
import com.tokio.technicaltest.app.dto.SchedulingRequestAndResponse;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static com.tokio.technicaltest.integration.IntegrationHelper.QUERY_DELETE_SCHEDULES;
import static com.tokio.technicaltest.integration.IntegrationHelper.QUERY_INSERT_SCHEDULES;

@SpringBootTest
public class SchedulingIntegrationTest {

    @Inject
    private SchedulingController schedulingController;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Test
    void whenRequestGetSchedules_thenReturnAllSchedules() {

        truncateData();
        insertSchedules();

        var response = schedulingController.findSchedules();

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(2, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void whenRequestGetSchedules_AndHasEmpty_thenReturnNoReturnSchedules() {

        truncateData();

        var response = schedulingController.findSchedules();

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(0, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void giveAnValidRequest_whenProcessSaveSchedules_thenReturnSuccess() throws IOException {

        truncateData();

        var request = FixtureUtils.readObjectFromFile(
                "fixtures", "valid-scheduling-request.json", SchedulingRequestAndResponse.class);

        request.setTransferDate(LocalDate.now().plusDays(5));

        var response = schedulingController.createScheduling(request);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).getData().size());

    }

    private void truncateData() {
        jdbcTemplate.update(QUERY_DELETE_SCHEDULES);
    }

    private void insertSchedules() {
        jdbcTemplate.update(QUERY_INSERT_SCHEDULES);
    }

}
