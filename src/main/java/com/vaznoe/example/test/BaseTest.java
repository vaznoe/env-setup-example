package com.vaznoe.example.test;

import com.vaznoe.example.config.EnvironmentConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;

@ContextConfiguration(classes = {EnvironmentConfig.class})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Value("${base.url}")
    private String baseUrl;

    @BeforeSuite(alwaysRun = true)
    public void setup() throws Exception {
        this.springTestContextPrepareTestInstance();
    }

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().clearPreferences();
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
     }

    public void openBrowser() {
        getDriver().get(baseUrl);
    }
}
