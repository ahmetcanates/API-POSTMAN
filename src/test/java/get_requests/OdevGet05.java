package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OdevGet05 {

    //1) https://petstore.swagger.io/ dokumanını kullanarak statüsü "available" olan "pet" sayısını bulup 100'den fazla olduğunu assert eden bir otomasyon testi yazınız.

 /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        User sens Get request
    Then
        Assert that number of pets whose status is "available" is more than 100
     */

    @Test
    public void get05() {
        //set the url
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";

        //set the expected data

        //send the request and get the response
        Response response = given().contentType(ContentType.JSON).get(url);
        response.prettyPrint();

        int petSayisi = response.jsonPath().getList("id").size();
        assertTrue(petSayisi>100);



    }
}
