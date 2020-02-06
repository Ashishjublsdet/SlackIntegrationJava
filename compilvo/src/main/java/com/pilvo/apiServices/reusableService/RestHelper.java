package com.pilvo.apiServices.reusableService;


import com.pilvo.apiServices.customAssertion.CustomAssert;
import com.pilvo.apiServices.customReporting.ExtentReportManager;
import com.pilvo.apiServices.statusCode.HttpStatusCode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestHelper {

    public static Response postRequestWithQueryParam(String URI, Map<String, String> mapParams) {
        Response response =
                given().relaxedHTTPSValidation().log().all()
                        .with()
                        .queryParams(mapParams)
                        .contentType(ContentType.JSON)
                        .when().post(URI)
                        .then().extract().response();
        return response;
    }

    public static Response postRequestWithBody(String URI, Map<String, String> header, String body) {
        Response response =
                given().relaxedHTTPSValidation().log().all()
                        .with()
                        .headers(header)
                        .body(body)
                        .contentType(ContentType.JSON)
                        .when().post(URI)
                        .then().extract().response();
        return response;
    }

    public static Response getHttpRequest(String URI, Map<String, String> params) {
        return given().log().all()
                .relaxedHTTPSValidation()
                .with()
                .params(params)
                .contentType(ContentType.JSON)
                .get(URI).then().extract().response();
    }

    public static void ValidateStatusCode(Response response, HttpStatusCode httpStatusCode) {
        int statusCode = response.then().extract().statusCode();
        CustomAssert.assertEquals(statusCode, httpStatusCode.getCode(), "Verify Http Status Code ::");
    }
}
