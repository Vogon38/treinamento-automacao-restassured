package DataDrivenTest.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID = "51838"; //Hard coded - Input for get and update details of single employee

    public Logger logger;

    @BeforeClass
    public void setup() {
        logger = Logger.getLogger("EmployeesRestAPI"); //Added logger
        PropertyConfigurator.configure("Log4j.properties"); //Added logger
        logger.setLevel(Level.DEBUG);
    }

}
