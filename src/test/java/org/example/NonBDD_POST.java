package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class NonBDD_POST {

    @Test
    public void testNonBDDPOSTRequest(){
        RequestSpecification r= RestAssured.given();

        String payload = "{\n" +
                "        \"username\": \"admin\",\n" +
                "        \"password\": \"password123\"\n" +
                "}";

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload);

        Response response = r.when().post();

        ValidatableResponse validatableResponse = response.then();
        String responseString = response.asPrettyString();
        System.out.println(responseString);
        validatableResponse.statusCode(HttpStatus.SC_OK);

    }

}
