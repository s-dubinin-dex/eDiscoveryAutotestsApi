package eDiscovery.apiMethods.identity;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsIdentity extends UrlBase {
    private static final String CONNECT_TOKEN       = "/connect/token";
    private static final String CONNECT_USERINFO    = "/connect/userinfo";

    @Step("Авторизация и получение токена")
    public static Response connectToken(Map<String, String> authorizationScope){

//        SpecificationsServer.setBaseUrl(IDENTITY_URL);
        RequestSpecification tokenRequestSpec = new RequestSpecBuilder().setBaseUri(IDENTITY_URL).build();
        return given()
                .log().all()
                .spec(tokenRequestSpec)
                .formParams(authorizationScope)
                .when()
                .post(CONNECT_TOKEN)
                .then()
                .extract().response();
    }

    @Step("Получение информации от текущем пользователе")
    public static Response connectUserInfo(){

//        SpecificationsServer.setBaseUrl(IDENTITY_URL);

        return given()
                .spec(SpecificationsServer.getUrlSpecification(IDENTITY_URL))
                .when()
                .get(CONNECT_USERINFO)
                .then()
                .extract().response();
    }
}
