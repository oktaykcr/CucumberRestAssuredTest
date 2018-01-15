package step_definitions;

import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.util.Map;

public class TurkeyPlaceSteps implements En{

    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    private Headers allHeaders;
    private Map<String, String> allCookies;
    private String statusLine;
    private int statusCode;


    public TurkeyPlaceSteps(){
        Given("^Request from turkey page$", () -> {
            request = RestAssured.given();
        });

        When("^Get turkey page$", () -> {
            response = request.when().get("http://api.zippopotam.us/TR/01000");
            allHeaders = response.getHeaders();
            allCookies = response.getCookies();
            statusLine = response.getStatusLine();
            statusCode = response.getStatusCode();
            System.out.println("_HEADERS : " + allHeaders);
            System.out.println("_COOKIES : "+ allCookies);
            System.out.println("_STATUS LINE : "+ statusLine);
            System.out.println("_STATUS CODE : "+ statusCode);
        });

        Then("^The country should be turkey$", () -> {
            json = response.then();
            json.assertThat().body("country", Matchers.equalTo("Turkey"));
        });

        Then("^Serinevler mah and Beyceli mah should have correct longitude$", () -> {
            json.assertThat().body("places.findAll {it.longitude = 37.4987}.'place name'", Matchers.hasItems("Serinevler Mah.","Beyceli Mah."));
        });

        And("^Log$", () -> {
            json.log().body();
        });
    }

    //Without Java lambda expression
    /*
    @Given("^request from turkey page$")
    public void request_from_turkey_page(){
        request = RestAssured.given();
    }
    @When("^get turkey page$")
    public void get_turkey_page(){
        response = request.when().get("http://api.zippopotam.us/TR/01000");
    }
    @Then("^the country should be turkey$")
    public void the_country_should_be_turkey(){
        json = response.then();
        json.assertThat().body("country", Matchers.equalTo("Turkey"));
    }
    @Then("^serinevler mah and beyceli mah should have correct longitude$")
    public void serinevler_mah_beycel_koyleri_should_have_correct_longitude(){
        json.assertThat().body("places.findAll {it.longitude = 37.4987}.'place name'", Matchers.hasItems("Serinevler Mah.","Beyceli Mah."));
    }
    @And("^log$")
    public void log(){
        json.log().body();
    }
    */
}
