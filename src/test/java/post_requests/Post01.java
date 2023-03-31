package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
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

    /*
    De-Serialization: Json datanin Java objesine cevrilmesi.
    Serialization: Java objesinin, Json dataya cevrilmesi.
    2 turlu De-Serialization yapacagiz:
        i)Gson: Google tarafindan uretilmistir.
        ii)Object Mapper: En populeri
     */


    @Test
    public void post01() {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data-->Payload
        /*
        {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
         */
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",55.0);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given().when().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);//DE SERIALIZATION --> JSON -> JAVA
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
}
