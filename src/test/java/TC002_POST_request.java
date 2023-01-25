import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_request {

    @Test
    void registrationSuccessful() {

        //Specify base URI
        RestAssured.baseURI = "https://demoqa.com/Account/v1";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Request payload sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("userName","Jane Doe");
        requestParams.put("password","09876543210Al!");

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toJSONString());

        //Response object
        Response response = httpRequest.request(Method.POST, "/User");

        //Print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " +responseBody);

        //Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " +statusCode);
        Assert.assertEquals(statusCode, 201);

//        //Success code validation
//        String successCode = response.jsonPath().get("SuccessCode");
//        Assert.assertEquals(successCode, "OPERATION_SUCCESS");

    }

}
