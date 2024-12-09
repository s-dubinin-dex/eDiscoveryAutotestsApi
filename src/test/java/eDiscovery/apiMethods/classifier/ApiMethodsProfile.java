package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.models.classifier.profile.AddProfileRequestModel;
import eDiscovery.models.classifier.profile.UpdateProfileRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsProfile extends UrlBase {

    private static final String PROFILE         = "/Profile";
    private static final String PROFILE_PROFILE = "/Profile/Profile";
    private static final String ODATA_PROFILE   = "/odata/Profile";

    @Step("Создание профиля категоризации")
    public static Response addProfile(AddProfileRequestModel requestBody){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(requestBody)
                .when()
                .post(PROFILE)
                .then()
                .extract().response();
    }

    @Step("Изменение профиля категоризации")
    public static Response updateProfile(UpdateProfileRequestModel requestBody){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(requestBody)
                .when()
                .put(PROFILE)
                .then()
                .extract().response();
    }

    @Step("Получение списка профилей категоризации")
    public static Response getProfileList(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(PROFILE_PROFILE)
                .then()
                .extract().response();
    }

    @Step("Получение списка профилей категоризации по протоколу odata")
    public static Response getProfileListOData(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_PROFILE)
                .then()
                .extract().response();
    }

    @Step("Получение списка профилей категоризации по протоколу odata")
    public static Response getProfileListOData(Map<String, String> params){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .params(params)
                .when()
                .get(ODATA_PROFILE)
                .then()
                .extract().response();
    }

    @Step("Получение профиля категоризации по id")
    public static Response getProfileById(String id){
        return getProfileListOData(new HashMap<>());
    }

    @Step("Получение профиля категоризации по id в path param")
    public static Response getProfileByIdPath(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_PROFILE + "/{id}")
                .then()
                .extract().response();
    }

}
