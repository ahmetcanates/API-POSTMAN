package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeleteBodyPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {
     /*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
       */

    /*
   Given
      URL: https://dummy.restapiexample.com/api/v1/delete/2
   When
      HTTP Request Method: DELETE Request
   Then
       Status code is 200
   And
       "status" is "success"
   And
       "data" is "2"
   And
       "message" is "Successfully! Record has been deleted"
     */

    @Test
    public void delete02() {
        //set the url
        spec.pathParams("first","delete","second",2);

        //set the expected data
        DummyRestApiDeleteBodyPojo expectedData = new DummyRestApiDeleteBodyPojo("success","2","Successfully! Record has been deleted");
        System.out.println("expectedData = " + expectedData);

        //send the request and get the response
        Response response = given(spec).delete("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        DummyRestApiDeleteBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiDeleteBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData(),actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());


    }


}
