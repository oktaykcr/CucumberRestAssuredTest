package step_definitions;

import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class CountriesSteps implements En {

    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    public CountriesSteps(){

        Given("^Http request with parameters \"([^\"]*)\" \"([^\"]*)\"$", (String code,String range) -> {
            request = RestAssured.given().
                    pathParam("codeVar", code).
                    pathParam("rangeVar", range);
        });

        When("^Waiting response for entering zip code and range$", () -> {
            response = request.when().get("http://api.zippopotam.us/{codeVar}/{rangeVar}");
        });

        Then("^The waiting response is actual \"([^\"]*)\"$", (String country) -> {
            json = response.then();
            json.assertThat().body("country", Matchers.equalTo(country));
        });
    }

    //Without Java lambda expression
    /*
    @Given("^http request with parameters (.*) (.*)$")
    public void http_request_with_parameters(String code,String range){
        request = RestAssured.given().
                pathParam("codeVar", code).
                pathParam("rangeVar", range);
    }

    @When("^waiting response for entering zip code and range$")
    public void waiting_response_for_entering_zip_code_and_range(){
        response = request.when().get("http://api.zippopotam.us/{codeVar}/{rangeVar}");
    }

    @Then("^the waiting response is actual (.*)$")
    public void the_waiting_response_is_actual(String country){
        json = response.then();
        json.assertThat().body("country", Matchers.equalTo(country));
    }
    */
}
