package eDiscovery.spec;

import eDiscovery.UrlBase;
import eDiscovery.helpers.Authorization;
import eDiscovery.helpers.AuthorizationScope;
import eDiscovery.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications extends UrlBase {
    public static RequestSpecification basicRequestSpecification(){
        return RestAssured.
                given()
                .filter(CustomAllureListener.withCustomTemplates())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                ;
    }

    public static RequestSpecification basicRequestSpecificationWithoutAuthorization(){
        return basicRequestSpecification()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification basicRequestSpecificationWithAdminAuthorization(){
        return basicRequestSpecificationWithoutAuthorization()
                .auth().oauth2(Authorization.getAccessToken(AuthorizationScope.getAdminClientScope()));
    }

    public static RequestSpecification basicRequestSpecificationWithLocalAgentAuthorization(){
        return basicRequestSpecificationWithoutAuthorization()
                .auth().oauth2(Authorization.getAccessToken(AuthorizationScope.getLocalAgentClientScope()));
    }

    public static RequestSpecification basicRequestSpecificationWithCloudAgentAuthorization(){
        return basicRequestSpecificationWithoutAuthorization()
                .auth().oauth2(Authorization.getAccessToken(AuthorizationScope.getCloudAgentClientScope()));
    }
}
