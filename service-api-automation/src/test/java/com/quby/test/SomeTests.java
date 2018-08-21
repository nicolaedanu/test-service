package com.quby.test;

import com.quby.test.TestBase;
import org.testng.annotations.Test;

public class SomeTests extends TestBase {

    @Test
    public void getActiveUsers(){
        authJson().get(endpointUsers()).then().statusCode(200).log().all();
    }

}
