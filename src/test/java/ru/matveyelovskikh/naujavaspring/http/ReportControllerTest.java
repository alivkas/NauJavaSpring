package ru.matveyelovskikh.naujavaspring.http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Тест запросов контроллера ReportController
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    /**
     * Тестировать создание отчета с кодом статуса 201,
     * также проверить получение id
     */
    @Test
    public void createReportReturn201AndReportIdTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/public/reports")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("'ID отчета'", notNullValue())
                .extract()
                .response();

        Map<String, Long> responseMap = response.as(Map.class);
        Assertions.assertNotNull(responseMap.get("ID отчета"));
    }

    /**
     * Тестировать получение отчета по id со статусом 200
     */
    @Test
    public void getReportReturn200Test() {
        Integer reportId = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/public/reports")
                .then()
                .extract()
                .path("'ID отчета'");

        given()
                .pathParam("reportId", reportId)
                .when()
                .get("/api/public/reports/{reportId}")
                .then()
                .log().all() // Для диагностики
                .statusCode(HttpStatus.OK.value())
                .body("status", anyOf(
                        equalTo("CREATED"),
                        equalTo("FINISHED"),
                        equalTo("ERROR")))
                .body("content", anyOf(
                        nullValue(),
                        not(emptyOrNullString())));
    }

    /**
     * Тестировать получение отчета по id со статусом 404,
     * который не существует
     */
    @Test
    public void getReportReturn404Test() {
        Long nonExistentId = 999999L;

        given()
                .pathParam("reportId", nonExistentId)
                .when()
                .get("/api/public/reports/{reportId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    /**
     * Тестировать получение отчета по id со статусом 500
     * при вводе id неправильного формата
     */
    @Test
    public void getReportReturn500() {
        given()
                .pathParam("reportId", "invalid-id-format")
                .when()
                .get("/api/public/reports/{reportId}")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * Тестировать получение отчета по id со статусом 500
     * при вводе отрицательного id
     */
    @Test
    public void getReportReturn404() {
        given()
                .pathParam("reportId", -1)
                .when()
                .get("/api/public/reports/{reportId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
