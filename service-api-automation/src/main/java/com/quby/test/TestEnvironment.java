package com.quby.test;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public enum TestEnvironment {
    TEST("https://reqres.in", "/api") {
        @Override
        public RequestSpecification auth() {
            return given().auth().preemptive().oauth2(getToken());
        }

        private String getToken() {
            String token = given().contentType("application/json")
                    .body("{\"email\":\"peter@klaven\",\"password\":\"cityslicka\"}")
                    .when().post("/api/login")
                    .then().statusCode(200)
                    .and().extract().path("token");
            return token;
        }
    },
    LOCAL("http://localhost:8080", "/");
    private final String baseURI;
    private final String contextPath;

    TestEnvironment(final String envBaseURI, final String envContextPath) {

        this.baseURI = envBaseURI;
        this.contextPath = envContextPath;
    }

    public RequestSpecification auth() {
        return given();
    }

    public String baseURI() {
        return baseURI;
    }

    public String contextPath() {
        return contextPath;
    }
}
