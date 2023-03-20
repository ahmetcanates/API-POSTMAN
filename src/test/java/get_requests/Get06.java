package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get06 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/23
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
     {
    "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "super bowls"
}
     */

    @Test
    public void get06() {
        //set the url
        spec.pathParams("first","booking","second",23);

        //set the expected data

        //send the request and get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1.Yol
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Jake"),
                        "lastname",equalTo("Smith"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //2.Yol: JSON PATH ile
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("Jake",jsonPath.getString("firstname"));
        Assert.assertEquals("Smith",jsonPath.getString("lastname"));
        Assert.assertEquals(111,jsonPath.getInt("totalprice"));
        Assert.assertTrue(jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
        Assert.assertEquals("Breakfast",jsonPath.getString("additionalneeds"));

        //3.Yol: Test NG Soft Assert

        //1.Soft Assert objesi olustur
        SoftAssert softAssert = new SoftAssert();

        //2.Assertion:
        softAssert.assertEquals(jsonPath.getString("firstname"),"Jake","firstname uyusmadi");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith","lastname uyusmadi");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice uyusmadi");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"),"depositpaid uyusmadi");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","bookingdates.checkin uyusmadi");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","bookingdates.checkout uyusmadi");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds uyusmadi");


        //3.softAssert().assertAll() diyerek dogrulamayi kontrol et. Aksi takdirde test hep "PASS" olur.
        softAssert.assertAll();



    }

}
