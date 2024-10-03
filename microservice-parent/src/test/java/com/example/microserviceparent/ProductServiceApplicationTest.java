package com.example.microserviceparent;

import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTest {

@ServiceConnection
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

@LocalServerPort
private Integer port;
    private CoreMatchers Matcher;

    @BeforeEach
void setup(){
    RestAssured.baseURI = "https://localhost";
    RestAssured.port = port;
}
static {
    mongoDBContainer.start();
}
@Test
void createProductTest() {

    String request = """
       {
        "name": "Samsung TV",
        "description" : "Samsung TV - Model 2024",
        "Price": 2000
       }
    """;

    byte[] requestBody = request.getBytes();
    RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/products")
            .then()
            .log().all()
            .statusCode(201)
            .body("id", Matchers.notNullValue())
            .body("name", Matchers.equalTo("Samsung TV"))
            .body("description", Matchers.equalTo("Samsung TV - Model 2024"))
            .body("price", Matchers.equalTo(2000));



}

@Test
    void getProductTest(){

    String request = """
       {
        "name": "Samsung TV",
        "description" : "Samsung TV - Model 2024",
        "Price": 2000
       }
    """;

    byte[] requestBody = request.getBytes();
    RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/products")
            .then()
            .log().all()
            .statusCode(201)
            .body("id", Matchers.notNullValue())
            .body("name", Matchers.equalTo("Samsung TV"))
            .body("description", Matchers.equalTo("Samsung TV - Model 2024"))
            .body("price", Matchers.equalTo(2000));

    RestAssured.given()
            .contentType("application/json")
            .when()
            .post("/products")
            .then()
            .log().all()
            .statusCode(200)
            .body("size()", Matchers.greaterThan(0))
            .body("[0].name", Matchers.equalTo("Samsung TV"))
            .body("[0].description", Matchers.equalTo("Samsung TV - Model 2024"))
            .body("[0].price", Matchers.equalTo(2000));


    }


}
