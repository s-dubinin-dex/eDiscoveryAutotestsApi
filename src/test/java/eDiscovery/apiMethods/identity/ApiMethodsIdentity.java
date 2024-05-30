package eDiscovery.apiMethods.identity;

import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static eDiscovery.UrlBase.URL_IDENTITY;
import static io.restassured.RestAssured.given;

public class ApiMethodsIdentity {
    @Step("Авторизация и получение токена")
    public static Response connectToken(){

        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecification());

        SpecificationsServer.setBaseUrl(URL_IDENTITY);

        return given()
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .when()
                .post("/connect/token")
                .andReturn();
    }
}
