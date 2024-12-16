package eDiscovery.spec;

import eDiscovery.UrlBase;
import eDiscovery.helpers.Authorization;
import eDiscovery.helpers.AuthorizationScope;
import eDiscovery.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications extends UrlBase {
    public static RequestSpecification basicRequestSpecification(){
        return new RequestSpecBuilder()
                .addFilter(CustomAllureListener.withCustomTemplates())
                        .addFilter(new RequestLoggingFilter())
                                .addFilter(new ResponseLoggingFilter())
                                        .build();
    }

    public static RequestSpecification basicRequestSpecificationWithoutAuthorization(){
        return basicRequestSpecification()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification basicRequestSpecificationWithAdminAuthorization(){
        RequestSpecification req = basicRequestSpecificationWithoutAuthorization()
                .auth().oauth2(Authorization.getAccessToken(AuthorizationScope.getAdminClientScope()));

        return req;
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
