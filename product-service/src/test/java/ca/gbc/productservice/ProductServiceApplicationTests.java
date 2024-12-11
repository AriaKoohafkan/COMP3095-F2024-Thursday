package ca.gbc.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    // This annotation is used in combination with TestContainers to automatically configure the connection
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void createProductTest() {
        String requestBody = """
                {
                    "name": "Smartphone",
                    "description": "5G-enabled smartphone with 128GB storage and 48MP camera.",
                    "price": 799
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Smartphone"))
                .body("description", Matchers.equalTo("5G-enabled smartphone with 128GB storage and 48MP camera."))
                .body("price", Matchers.equalTo(799));
    }

    @Test
    void getProductsTest() {
        String requestBody = """
                {
                    "name": "Smartphone",
                    "description": "5G-enabled smartphone with 128GB storage and 48MP camera.",
                    "price": 799
                }
                """;

        // Create a product
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Smartphone"))
                .body("description", Matchers.equalTo("5G-enabled smartphone with 128GB storage and 48MP camera."))
                .body("price", Matchers.equalTo(799));

        // Retrieve all products
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/api/product")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("[0].id", Matchers.notNullValue())
                .body("[0].name", Matchers.equalTo("Smartphone"))
                .body("[0].description", Matchers.equalTo("5G-enabled smartphone with 128GB storage and 48MP camera."))
                .body("[0].price", Matchers.equalTo(799));
    }
}
