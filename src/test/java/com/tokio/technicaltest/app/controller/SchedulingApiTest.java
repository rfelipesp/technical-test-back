package com.tokio.technicaltest.app.controller;

import com.tokio.technicaltest.MainHelper;
import com.tokio.technicaltest.domain.port.inbound.SchedulingInboundPort;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SchedulingApiTest {

    @LocalServerPort
    private int port;

    @MockBean
    private SchedulingInboundPort schedulingInboundPortMock;

    @Test
    void whenRequestGetSchedules_thenReturnSchedules() {

        when(schedulingInboundPortMock.findSchedules()).thenReturn(List.of(MainHelper.buildScheduling()));

        given()
                .port(port)
                .contentType("application/json")
                .when()
                .get("/tokio/scheduler")
                .then()
                .body("message", equalTo("Success"))
                .body("data.size()", equalTo(1))
                .statusCode(200);

    }

    @Test
    void whenRequestGetSchedules_andEmptySchedules_thenNotFoundSchedules() {

        when(schedulingInboundPortMock.findSchedules()).thenReturn(List.of());

        given()
                .port(port)
                .contentType("application/json")
                .when()
                .get("/tokio/scheduler")
                .then()
                .body("message", equalTo("Not Founded Schedules"))
                .body("data.size()", equalTo(0))
                .statusCode(200);

    }

    @Test
    void whenRequestGetSchedules_thenThrowsException() {

        when(schedulingInboundPortMock.findSchedules()).thenThrow(IllegalStateException.class);

        given()
                .port(port)
                .contentType("application/json")
                .when()
                .get("/tokio/scheduler")
                .then()
                .body("message", equalTo("Some Error when try retrieve information"))
                .body("data.size()", equalTo(0))
                .statusCode(400);

    }

    @Test
    void giveAnValidRequest_whenProcessSchedules_thenReturnSuccess() {

        var request = MainHelper.buildSchedulingRequest();

        when(schedulingInboundPortMock.saveScheduling(any())).thenReturn(MainHelper.buildScheduling());

        given()
                .port(port)
                .contentType("application/json")
                .body(request)
                .when()
                .post("/tokio/scheduler")
                .then()
                .body("message", equalTo("Success"))
                .body("data.size()", equalTo(1))
                .statusCode(200);

    }

    @Test
    void giveAnInValidRequest_whenProcessSchedules_thenReturnSuccess() {

        var request = MainHelper.buildSchedulingRequest();
        request.setTransferDate(null);

        when(schedulingInboundPortMock.saveScheduling(any())).thenReturn(MainHelper.buildScheduling());

        given()
                .port(port)
                .contentType("application/json")
                .body(request)
                .when()
                .post("/tokio/scheduler")
                .then()
                .body("errors.transferDate", equalTo("A data de transferência deve ser informada"))
                .statusCode(400);

    }

    @Test
    void giveAUuid_whenProcessCancelSchedules_thenReturnSuccess() {

        String uuid = "/843417d2-809c-4fb2-aa08-584e521815e2";

        doNothing().when(schedulingInboundPortMock).deleteScheduling(any());

        given()
                .port(port)
                .contentType("application/json")
                .when()
                .delete("/tokio/scheduler" + uuid)
                .then()
                .body("message", equalTo("Scheduling canceled with success"))
                .body("data.size()", equalTo(0))
                .statusCode(200);

    }

}
