package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsFileExtension extends UrlBase {
    public static final String FILE_EXTENSION_FILE_EXTENSION  = "/FileExtension/FileExtension";
    public static final String ODATA_FILE_EXTENSION            = "/odata/FileExtension";

    @Step("Получение списка расширений")
    public static Response getFileExtensionList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(FILE_EXTENSION_FILE_EXTENSION)
                .then()
                .extract().response();
    }

    @Step("Получение списка расширений")
    public static Response getFileExtensionListListOData(){

        return getFileExtensionListListOData(new HashMap<>());

    }

    @Step("Получение списка расширений")
    public static Response getFileExtensionListListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_FILE_EXTENSION)
                .then()
                .extract().response();
    }
}
