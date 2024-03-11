package org.example.Modules;

import com.google.gson.Gson;
import org.example.POJOs.*;
import org.example.POJOs.Request.Booking;
import org.example.POJOs.Request.BookingDates;
import org.example.POJOs.Response.BookingResponse;

public class PayloadManager {

    Gson gson;

    // Java -> JSON
    public static String createPayloadGSON(){
        Booking booking = new Booking();

        booking.setFirstname("Jimmy");
        booking.setLastname("Anderson");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-05-05");

        booking.setBookingDates(bookingdates);

        booking.setAdditionalneeds("Breakfast");

        //System.out.println(booking);

        Gson gson = new Gson();
        String jsonStringBooking =  gson.toJson(booking);

       // System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }

//    public static void main(String[] args) {
//        System.out.println(createPayloadGSON());
//    }


    public void createPayloadJackson(){

    }

    public String updatePayload(){
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Anderson");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Object -> JSON String (GSON)
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }

    public String setAuthPayload() {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonStringBooking = gson.toJson(auth);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }

    public String getTokenFromJSON(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse,TokenResponse.class);
        return  tokenResponse1.getToken();
    }


    public BookingResponse bookingResponseJava(String responseString){
        gson = new Gson();
        return gson.fromJson(responseString,BookingResponse.class);

    }

    public Booking bookingResponsePUTReqJava(String responseString){
        gson = new Gson();
        Booking booking = gson.fromJson(responseString,Booking.class);
        return booking;

    }
}
