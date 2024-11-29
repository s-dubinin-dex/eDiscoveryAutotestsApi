package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsMarker extends UrlBase {

    private static final String MARKER_MARKER   = "/Marker/Marker";
    private static final String ODATA_MARKER    = "/odata/Marker";

    @Step("Получение списка маркеров")
    public static Response getMarkerList(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(MARKER_MARKER)
                .then()
                .extract().response();

    }

    @Step("Получение списка маркеров по протоколу odata")
    public static Response getMarkerListOData(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_MARKER)
                .then()
                .extract().response();

    }

    @Step("Получение маркера по id")
    public static Response getMarkerListById(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_MARKER + String.format("(%s)", id))
                .then()
                .extract().response();

    }

    @Step("Получение маркера по id в path param")
    public static Response getMarkerListByIdPath(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_MARKER + "/{id}")
                .then()
                .extract().response();

    }
}
