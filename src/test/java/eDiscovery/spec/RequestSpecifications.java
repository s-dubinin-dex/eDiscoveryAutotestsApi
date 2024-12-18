package eDiscovery.spec;

import eDiscovery.UrlBase;
import eDiscovery.helpers.Authorization;
import eDiscovery.helpers.AuthorizationScope;
import eDiscovery.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications extends UrlBase {
    public static RequestSpecification basicRequestSpecification(){
        return RestAssured.
                given().filter(CustomAllureListener.withCustomTemplates());
    }

    public static RequestSpecification basicRequestSpecificationWithoutAuthorization(){
        return basicRequestSpecification()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification basicRequestSpecificationWithAdminAuthorization(){
        return basicRequestSpecificationWithoutAuthorization()
                .header("Authorization", "BEARER " + Authorization.getAccessToken(AuthorizationScope.getAdminClientScope()));
    }

    public static RequestSpecification basicRequestSpecificationWithLocalAgentAuthorization(){
        return basicRequestSpecificationWithoutAuthorization()
                .header("Authorization", "BEARER " + Authorization.getAccessToken(AuthorizationScope.getLocalAgentClientScope()));
    }

    public static RequestSpecification basicRequestSpecificationWithCloudAgentAuthorization(){
        return basicRequestSpecificationWithoutAuthorization()
                .header("Authorization", "BEARER " + Authorization.getAccessToken(AuthorizationScope.getCloudAgentClientScope()));
    }

    public static RequestSpecification multipartRequestSpecificationWithAdminAuthorization(){
        return basicRequestSpecification()
                .contentType(ContentType.MULTIPART)
                .auth().oauth2(Authorization.getAccessToken(AuthorizationScope.getAdminClientScope()));
    }
}
