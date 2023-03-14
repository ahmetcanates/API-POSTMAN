package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


public class Get02 {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get02() {
        //1.Adim: Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

        //2.Adim: Set the expected data-->(POST, PUT, PATCH)

        //3.Adim: Send the request get the response
        try {
            Response response= given().when().get(url);
        } catch (Exception e) {
            assert e.getMessage().contains("404");
            assert e.getMessage().contains("Not Found");
            Assert.assertFalse(e.getMessage().contains("TechProEd"));
        }

//        //4.Adim: Do Assertion
//        response.then().
//                statusCode(404).//   HTTP Status code should be 404
//                statusLine("HTTP/1.1 404 Not Found");//Status Line should be HTTP/1.1 404 Not Found
//
//        //  Response body contains "Not Found"
//        //assertTrue() methodu, method parantezi icindeki degerin false olmasi durumunda test fail olur
//        Assert.assertTrue(response.asString().contains("Not Found"));
//        //Response body does not contain "TechProEd"
//        Assert.assertFalse(response.asString().contains("TechProEd"));
//
//        //   Server is "Cowboy"
//        Assert.assertEquals("Cowboy",response.header("Server"));
    }
}
