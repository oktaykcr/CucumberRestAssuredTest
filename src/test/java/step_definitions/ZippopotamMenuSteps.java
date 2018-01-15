package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.ZippopotamMainPage;

public class ZippopotamMenuSteps {

    private ZippopotamMainPage mainPage;

    public ZippopotamMenuSteps(ZippopotamMainPage mainPage){
        this.mainPage = mainPage;
    }

    @Given("^user is on zippopotam homepage$")
    public void user_is_on_zippopotam_homepage(){
        mainPage.isHomepageDisplayed();
    }

    @When("^user clicks countries button$")
    public void user_clicks_countries_button(){
        mainPage.clickCountries();
    }

    @Then("^user is displayed countries page$")
    public void user_is_displayed_countries_page(){
        mainPage.isCountriesDisplayed();
        mainPage.tearDown();
    }

}
