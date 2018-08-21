package com.quby.test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class TestBase {
    private TestEnvironment testEnvironment = TestEnvironment.TEST;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = testEnvironment.baseURI();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public RequestSpecification authJson() {
        return initRequest("application/json");
    }

    public RequestSpecification authV2Json() {
        return initRequest("application/v2+json");
    }

    private RequestSpecification initRequest(final String mediaType) {
        return testEnvironment.auth().contentType(mediaType).accept(mediaType);
    }

    protected String endpointUsers() {
        return endpoint("/users");
    }

    private String endpoint(final String path) {
        return testEnvironment.contextPath() + path;
    }
}
