package com.tokio.technicaltest.integration;

import com.tokio.technicaltest.app.controller.SchedulingController;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

import static com.tokio.technicaltest.integration.IntegrationHelper.QUERY_INSERT_SCHEDULES;

@SpringBootTest
public class SchedulingIntegrationTest {

    @Inject
    private SchedulingController schedulingController;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Test
    void whenRequestGetSchedules_thenReturnAllSchedules() {

        insertSchedules();

        var response = schedulingController.findSchedules();

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(2, Objects.requireNonNull(response.getBody()).getData().size());

    }

    @Test
    void whenRequestGetSchedules_AndHasEmpty_thenReturnNoReturnSchedules() {

        var response = schedulingController.findSchedules();

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(0, Objects.requireNonNull(response.getBody()).getData().size());

    }

    private void insertSchedules() {
        jdbcTemplate.update(QUERY_INSERT_SCHEDULES);
    }

}
