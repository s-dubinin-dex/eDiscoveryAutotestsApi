package eDiscovery.spec;

import eDiscovery.UrlBase;
import eDiscovery.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications extends UrlBase {
    public static RequestSpecification basicRequestSpecification(){
        return RestAssured.
                given().filter(CustomAllureListener.withCustomTemplates());
    }

    public static RequestSpecification basicRequestSpecificationWithAuthorization(){
        return basicRequestSpecification()
                .contentType(ContentType.JSON)
                .header("Authorization", "BEARER " + TOKEN);
    }

}
