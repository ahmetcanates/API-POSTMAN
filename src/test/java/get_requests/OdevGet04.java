package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class OdevGet04 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Josh&lastname=Allen  ==> Data değişebilir
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Josh" and lastname is "Allen"   ==> Data değişebilir
     */

    @Test
    public void get04() {
        //set the url
        spec.pathParam("first","booking").queryParams("firstname","Josh","lastname","Allen");

        //set the expected data

        //send the request and get the response
        Response response = given().when().spec(spec).get("/{first}");
        response.prettyPrint();

        //do assertion
        Assert.assertEquals(200,response.statusCode());
        Assert.assertTrue(response.asString().contains("bookingid"));
    }
}
