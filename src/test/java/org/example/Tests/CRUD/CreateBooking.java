package org.example.Tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.Endpoints.APIConstants;
import org.example.POJOs.Response.BookingResponse;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class CreateBooking extends BaseTest {

    @Test
    @Owner("Hitanshu")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testCreateBooking() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING);

        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();


        validatableResponse = response.then().log().all();
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Validatable Default
        validatableResponse.statusCode(200);

        // Assert J
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Jimmy");

        // TestNG Assertions
        assertActions.verifyStatusCode(response);

    }

    @Test
    @Owner("Hitanshu")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#2 - Verify that the Booking not created when Payload is missing")
    public void testCreateBookingNegative() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body("{}").post();
        validatableResponse = response.then().log().all();
        // TestNG Assertions
        assertActions.verifyStatusCodeInvalidReq(response);
    }

}

