package org.example.Modules;

import com.google.gson.Gson;
import org.example.Payloads.POJOs.Booking;
import org.example.Payloads.POJOs.BookingDates;

public class PayloadManager {

    // Java -> JSON
    public String createPayloadGSON(){
        Booking booking = new Booking();

        booking.setFirstname("Jimmy");
        booking.setLastname("Anderson");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        Gson gson = new Gson();
        String jsonStringBooking =  gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }

    public void createPayloadJackson(){

    }
}
