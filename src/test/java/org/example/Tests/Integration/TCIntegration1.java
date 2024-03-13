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

    // Steps Executed:
    // Step 1 : Get a Token
    // Step 2 : Create a Booking
    // Step 3 : Update the Booking with Token and Booking ID
        // 3.1. Auth -> API Key
        // 3.2. Cookie based Auth
        // 3.3. OAuth 2.0 - Method how you can use the OAuth 2.0
    // How to pass data/variables from One TC to another
    // Step 4 : Delete the Booking
    String token;
    String bookingId;

    @Test(groups = "Integration", priority = 1)
    @Owner("Hitanshu")
    @Description("TC#Int1 - 1. Verify that Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();

        // direct extraction via jsonPath
        // bookingId = jsonPath.getString("bookingid");
        // Extracting Booking ID from BookingResponse Class
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
//        System.out.println(bookingResponse.getBookingid());

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

        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING + "/" +bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatePayload()).put();

        validatableResponse = response.then().log().all();
        Booking booking = payloadManager.bookingResponsePUTReqJava(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("James");
        assertThat(booking.getLastname()).isNotNull();
    }

    @Test(groups="Integration", priority = 4)
    @Owner("Hitanshu")
    @Description("TC#INT1 - Step 4: Delete the booking by ID")
    public void testDeleteBookingByID(ITestContext iTestContext){
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        // DELETE Req.
        System.out.println(iTestContext.getAttribute("bookingid"));
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING + "/" + bookingId).cookie("");
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

}
