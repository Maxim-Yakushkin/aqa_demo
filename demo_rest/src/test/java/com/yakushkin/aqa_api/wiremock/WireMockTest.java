package com.yakushkin.aqa_api.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@EnableWireMock(
        @ConfigureWireMock(name = "user-service", property = "user-client.url")
)
public class WireMockTest {

    @InjectWireMock("user-service")
    private WireMockServer wireMockServer;

    @Value("${user-client.url}")
    private String wiremockUrl;

    @Test
    public void testA() {
        int a = 1;
        int b = 5;

        assertAll(
                () -> assertThat(b).isGreaterThan(a),
                () -> assertThat(a).isLessThan(b)
        );
    }

    @Test
    public void wireMockTest() {
        String bodyStub = """
                    [
                        { "id": 1, "userId": 1, "title": "my todo" }
                    ]                                    
                """;

        wireMockServer.stubFor((
                get("/todolist")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(bodyStub))));

        String body = given()
                .when()
                .get(wiremockUrl + "/todolist")
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .extract()
                .body()
                .asPrettyString();

        assertThat(body).contains("\"id\": 1");
        assertThat(body).contains("\"userId\": 1");
        assertThat(body).contains("\"title\": \"my todo\"");

    }
}
