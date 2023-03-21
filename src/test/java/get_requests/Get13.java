package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;

public class Get13 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
         "meta": null,
         "data": {
            "id": 247158,
            "name": "Chandak Dutta",
            "email": "dutta_chandak@bartoletti.name",
            "gender": "female",
            "status": "inactive"
                 }
          }
    */

    @Test
    public void get13() {
        //set the url
        spec.pathParams("first","users","second",247152);

        //set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo("Chandak Dutta","dutta_chandak@bartoletti.name","female","inactive");
        GoRestPojo expectedData = new GoRestPojo(null,goRestDataPojo);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        Assert.assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        Assert.assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        Assert.assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());
        Assert.assertEquals(expectedData.getMeta(),actualData.getMeta());

    }
}
