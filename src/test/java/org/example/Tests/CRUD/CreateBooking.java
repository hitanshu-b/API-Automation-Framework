package org.example.Tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.Endpoints.APIConstants;
import org.testng.annotations.Test;

public class CreateBooking extends BaseTest {


    @Owner("Hitanshu")
    @Description("Verify that the Create Booking with Valid Payload, StatusCode is 200")
    @Test
    public void testPOSTRequest(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }
}
