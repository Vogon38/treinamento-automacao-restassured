package DataDrivenTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest_AddNewEmployees {

    @Test(dataProvider = "empdataprovider")
    void postNewEmployees(String ename, String esal, String eage) {

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest = RestAssured.given();

        //Here we created data which we can send along with the post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", ename);
        requestParams.put("salary", esal);
        requestParams.put("age", eage);

        //Add a header stating the request body is a json
        httpRequest.header("Content-Type", "application/json");

        //Add the json to the body request
        httpRequest.body(requestParams.toJSONString());

        //Post request
        Response response = httpRequest.request(Method.POST, "/create");

        //Capture response body to perform validation
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " +responseBody);

        Assert.assertEquals(responseBody.contains(ename), true);
        Assert.assertEquals(responseBody.contains(esal), true);
        Assert.assertEquals(responseBody.contains(eage), true);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @DataProvider(name="empdataprovider")
    String [][] getEmpData() {
        return (new String[][]{ {"abc123", "3000", "40"}, {"def123", "5000", "20"}, {"ghi123", "1000", "50"} });
    }

}
