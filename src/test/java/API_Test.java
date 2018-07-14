import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;

public class API_Test {

    @Test
    public void GetAllCountryCodes(){

        Response feedback = RestAssured.get("http://services.groupkt.com/country/get/all");
        int code = feedback.getStatusCode();
        String validate = feedback.asString();
        Assert.assertEquals(code,200);
        Assert.assertEquals(validate.contains("US"), true);
        Assert.assertEquals(validate.contains("DE"), true);
        Assert.assertEquals(validate.contains("GB"), true);

        System.out.println("Status code" + " " + code);


    }

    @Test
    public void GetSingleCountryCode(){

        //Status code of US

        Response codeUS = RestAssured.get("http://services.groupkt.com/country/get/iso2code/US");
        int usCodeStatus = codeUS.getStatusCode();
        String usCodeResponse = codeUS.getBody().asString();
        Assert.assertEquals(usCodeStatus,200);
        Assert.assertEquals(usCodeResponse.contains("Country found matching code [US]"),true);
        System.out.println("The status is" +" " + usCodeStatus);
        System.out.println(usCodeResponse);

        //Status code of DE

        Response codeDE = RestAssured.get("http://services.groupkt.com/country/get/iso2code/DE");
        int deCodeStatus = codeDE.getStatusCode();
        String deCodeResponse = codeDE.getBody().asString();
        Assert.assertEquals(deCodeStatus,200);
        Assert.assertEquals(deCodeResponse.contains("Country found matching code [DE]"),true);
        System.out.println("The status is" +" " + deCodeStatus);
        System.out.println(deCodeResponse);

        // Status code of GB

        Response codeGB = RestAssured.get("http://services.groupkt.com/country/get/iso2code/GB");
        int gbCodeStatus = codeGB.getStatusCode();
        String gbCodeResponse = codeGB.getBody().asString();

        Assert.assertEquals(gbCodeStatus,200);
        Assert.assertEquals(gbCodeResponse.contains("Country found matching code [GB]"),true);

        System.out.println("The status is" +" " + gbCodeStatus);
        System.out.println(gbCodeResponse);

    }

    @Test

    public void RetrieveWrongCountryCode(){
        Response wrongCode = RestAssured.get("http://services.groupkt.com/country/get/iso2code/UD");
        int status = wrongCode.getStatusCode();

        String respMessage = wrongCode.getBody().asString();
        System.out.println(respMessage);

        Assert.assertEquals(status,200);
        Assert.assertEquals(respMessage.contains("No matching country found for requested code"),true);

    }

    @Test

    public void createNewCountry(){
        given().
                body("{'name': 'TestCountry'," +
                        "'alpha2_code': 'TC', " +
                        "'alpha3_code' : 'TCY'}").
                when().
                contentType(ContentType.JSON).
                post("http://services.groupkt.com/country/post");
    }

}
