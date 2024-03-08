package org.example.Tests.Integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Base.BaseTest;
import org.example.Endpoints.APIConstants;
import org.example.Payloads.POJOs.BookingResponse;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import static org.assertj.core.api.Assertions.*;

public class TCIntegration1 extends BaseTest {

    // Get a Token
    // Create a Booking
    // Update the Booking with Token and Booking ID
        // 1. Auth -> API Key
        // Cookie based Auth
        // OAuth 2.0 - Method how you can use the OAuth 2.0
    // How to pass data/variables from One TC to another
    // Delete the Booking
    String token;

    @Test(groups = "Integration", priority = 1)
    @Owner("Hitanshu")
    @Description("TC#Int1 - Verify that Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
//        token = getToken();
//        System.out.println(token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();

        // Extracting Booking ID from BookingResponse Class
        // ....
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBookingid()).isNotNull();
        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
        iTestContext.setAttribute("token", getToken());


    }

    @Test(groups = "Integration", priority = 2)
    @Owner("Hitanshu")
    @Description("TC#Int2 - Verify Booking by ID")
    public void testVerifyBookingByID(){
        assertThat("Jimmy").isEqualTo("Jimmy");
    }

    @Test(groups="P0")
    public void testUpdateBookingByID(){
        token = getToken();
        System.out.println("testUpdateBookingByID ->" +token);
        assertThat("Jimmy").isEqualTo("Jimmy");
    }

    @Test(groups="P0")
    public void testDeleteBookingByID(){
        token = getToken();
        System.out.println("testDeleteBookingByID ->" +token);
        assertThat("Jimmy").isEqualTo("Jimmy");
    }

}
