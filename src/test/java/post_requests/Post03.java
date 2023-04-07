package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;

public class Post03 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post03() {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //do assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(expectedData.getUserId(),actualData.getUserId());
        Assert.assertEquals(expectedData.getTitle(),actualData.getTitle());
        Assert.assertEquals(expectedData.getCompleted(),actualData.getCompleted());
    }
}
