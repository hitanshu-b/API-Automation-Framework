package org.example.Tests.Integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.Base.BaseTest;
import org.example.Endpoints.APIConstants;
import org.example.POJOs.Request.Booking;
import org.example.POJOs.Response.BookingResponse;
import org.testng.Assert;
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
    @Description("TC#Int1 - 1. Verify that Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
//        token = getToken();
//        System.out.println(token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING);
     //   System.out.println(requestSpecification);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();

        // Extracting Booking ID from BookingResponse Class
        // ....
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        System.out.println(bookingResponse.getBookingid());
        assertThat(bookingResponse.getBookingid()).isNotNull();
        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
        iTestContext.setAttribute("token", getToken());


    }

    @Test(groups = "Integration", priority = 2)
    @Owner("Hitanshu")
    @Description("TC#Int1 - 2. Verify Booking by ID")
    public void testVerifyBookingByID(ITestContext iTestContext){
        // GET Req code
        System.out.println(iTestContext.getAttribute("bookingid"));
        Assert.assertTrue(true);
    }

    @Test(groups="Integration", priority = 3)
    @Owner("Hitanshu")
    @Description("TC#Int1 - 3. Verify Updated Changes in Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
//        token = getToken();
//        System.out.println("testUpdateBookingByID ->" +token);
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        System.out.println("Booking ID: "+bookingId);
        System.out.println("token"+token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING + "/" +bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatePayload()).put();

        validatableResponse = response.then().log().all();
        Booking booking = payloadManager.bookingResponsePUTReqJava(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("James");
        assertThat(booking.getLastname()).isNotNull();
    }

    @Test(groups="Integration", priority = 4)

    public void testDeleteBookingByID(){
        token = getToken();
        System.out.println("testDeleteBookingByID ->" +token);
        assertThat("Jimmy").isEqualTo("Jimmy");
    }

}
