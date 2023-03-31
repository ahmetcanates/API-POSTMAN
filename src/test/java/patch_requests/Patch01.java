package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									    }
     */

    @Test
    public void patch01() {
        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(null,"Wash the dishes",null);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).body(expectedData).patch("/{first}/{second}");
        response.prettyPrint();


        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));

        //eger tum body assert edilecekse:
        Assert.assertEquals(10,actualData.get("userId"));
        Assert.assertEquals(true,actualData.get("completed"));



    }
}
