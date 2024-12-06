package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsFileType extends UrlBase {
    public static final String FILE_TYPE_FILE_TYPE  = "/FileType/FileType";
    public static final String ODATA_FILE_TYPE      = "/odata/FileType";

    @Step("Получение списка типов файлов")
    public static Response getFileTypeList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(FILE_TYPE_FILE_TYPE)
                .then()
                .extract().response();
    }

    @Step("Получение списка типов файлов")
    public static Response getFileTypeListOData(){

        return getFileTypeListOData(new HashMap<>());

    }

    @Step("Получение списка типов файлов")
    public static Response getFileTypeListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_FILE_TYPE)
                .then()
                .extract().response();
    }
}
