package com.vaznoe.example.test;

import org.testng.annotations.Test;

public class SomeTest extends BaseTest {

    @Test(groups = "full")
    public void test1() {
        openBrowser();
    }
}
