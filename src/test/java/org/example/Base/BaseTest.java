package org.example.Base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Actions.AssertActions;
import org.example.Endpoints.APIConstants;
import org.example.Modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeMethod
    public void setConfig(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
//        requestSpecification = RestAssured.given();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();

    }

    public String getToken() {
        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");
        String payload = payloadManager.setAuthPayload();
        response = requestSpecification.contentType(ContentType.JSON)
                .body(payload)
                .when().post();
        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;
    }

}
