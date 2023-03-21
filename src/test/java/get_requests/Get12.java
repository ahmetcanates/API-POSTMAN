package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class Get12 extends HerOkuAppBaseUrl {
    /*
     Given
         https://restful-booker.herokuapp.com/booking/535
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
              {
                     "firstname": "John",
                     "lastname": "Smith",
                     "totalprice": 111,
                     "depositpaid": true,
                     "bookingdates": {
                         "checkin": "2018-01-01",
                         "checkout": "2019-01-01"
                     },
                     "additionalneeds": "Breakfast"
                 }
  */

    @Test
    public void get12() {
        //set the url
        spec.pathParams("first","booking","second",535);

        //set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("John","Smith",111,true,bookingDatesPojo,"Breakfast");

        //send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        Assert.assertEquals(expectedData.getLastname(),actualData.getLastname());
        Assert.assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        Assert.assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        Assert.assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        Assert.assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        Assert.assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
