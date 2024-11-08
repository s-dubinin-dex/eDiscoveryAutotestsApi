package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsFileTypes extends UrlBase {
    public static final String FILE_EXTENSIONS_FILE_EXTENSIONS  = "/FileExtensions/FileExtensions";
    public static final String ODATA_FILE_EXTENSIONS            = "/odata/FileExtensions";

    @Step("Получение списка типов файлов")
    public static Response getFileTypesList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(FILE_EXTENSIONS_FILE_EXTENSIONS)
                .then()
                .extract().response();
    }

    @Step("Получение списка типов файлов")
    public static Response getFileTypesListOData(){

        return getFileTypesListOData(new HashMap<>());

    }

    @Step("Получение списка типов файлов")
    public static Response getFileTypesListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_FILE_EXTENSIONS)
                .then()
                .extract().response();
    }
}
