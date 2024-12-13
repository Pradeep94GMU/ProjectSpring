package com.pradeep.tetsing.with.junit.and.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/login.feature",
        glue = {"com.pradeep.tetsing.with.junit.and.cucumber.stepdefinitions", "com.pradeep.tetsing.with.junit.and.cucumber"}
)
public class CucumberTestRunner {
}
