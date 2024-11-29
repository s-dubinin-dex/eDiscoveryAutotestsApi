package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsEncryptionPermission extends UrlBase {

    private static final String ENCRYPTION_PERMISSION_ENCRYPTION_PERMISSION = "/EncryptionPermission/EncryptionPermission";
    private static final String ODATA_ENCRYPTION_PERMISSION                 = "/odata/EncryptionPermission";

    @Step("Получение списка политик шифрования")
    public static Response getEncryptionPermissionList(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ENCRYPTION_PERMISSION_ENCRYPTION_PERMISSION)
                .then()
                .extract().response();

    }

    @Step("Получение списка политик шифрования по протоколу odata")
    public static Response getEncryptionPermissionListOData(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_ENCRYPTION_PERMISSION)
                .then()
                .extract().response();

    }

    @Step("Получение политики шифрования по id")
    public static Response getEncryptionPermissionListById(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_ENCRYPTION_PERMISSION + String.format("(%s)", id))
                .then()
                .extract().response();

    }

    @Step("Получение политики шифрования по id в path param")
    public static Response getEncryptionPermissionListByIdPath(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_ENCRYPTION_PERMISSION + "/{id}")
                .then()
                .extract().response();

    }
}
