package com.pradeep.tetsing.with.junit.and.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginStepDefinitions {

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        System.out.println("User navigates to login page");
    }

    @When("they enter valid credentials")
    public void enterValidCredentials() {
        System.out.println("User enters valid credentials");
    }

    @Then("they are redirected to their dashboard")
    public void redirectedToDashboard() {
        System.out.println("User is redirected to the dashboard");
    }

    @When("they enter invalid credentials")
    public void enterInvalidCredentials() {
        System.out.println("User enters invalid credentials");
    }

    @Then("an error message is displayed")
    public void errorMessageDisplayed() {
        System.out.println("Error message is displayed");
    }
}