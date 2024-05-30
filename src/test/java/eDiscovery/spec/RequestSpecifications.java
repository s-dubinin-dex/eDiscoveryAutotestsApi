package eDiscovery.spec;

import eDiscovery.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {
    public static RequestSpecification basicRequestSpecification(){
        return RestAssured.
                given().filter(CustomAllureListener.withCustomTemplates());
    }

}
