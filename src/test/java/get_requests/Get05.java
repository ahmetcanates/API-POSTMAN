package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get05 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a GET request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Sally" and last name is "Brown"
            (Data içerisinde firstname değeri "Sally", lastname değeri "Brown" olan biri olmalı)
     */

    @Test
    public void get05() {
        //Set the URL
        spec.
                pathParam("first","booking").
                queryParams("firstname","Sally",
                        "lastname","Brown");

        //Set the expected data

        //Send the request and get the response
        Response response = given().when().spec(spec).get("/{first}");
        response.prettyPrint();

        //do assertion
        response.then().statusCode(200);//Status code is 200

        //Among the data there should be someone whose firstname is "Sally" and last name is "Brown"
        Assert.assertTrue(response.asString().contains("bookingid"));



    }
}
