package eDiscovery.apiMethods.identity;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsIdentity extends UrlBase {
    private static final String CONNECT_TOKEN       = "/connect/token";

    @Step("Авторизация и получение токена")
    public static Response connectToken(){

        SpecificationsServer.setBaseUrl(IDENTITY_URL);

        return given()
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .when()
                .post(CONNECT_TOKEN)
                .then()
                .extract().response();
    }
}
