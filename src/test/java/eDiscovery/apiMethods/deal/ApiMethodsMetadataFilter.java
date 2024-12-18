package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.metadataFilter.AddMetadataFilterRequestBody;
import eDiscovery.models.deal.metadataFilter.UpdateMetadataFilterRequestBody;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsMetadataFilter extends UrlBase {

    private static final String METADATA_FILTER                 = "/MetadataFilter";
    private static final String METADATA_FILTER_METADATA_FILTER = "/MetadataFilter/MetadataFilter";
    private static final String ODATA_METADATA_FILTER           = "/odata/MetadataFilter";

    @Step("Создание фильтра по метаданным")
    public static Response addMetadataFilter(AddMetadataFilterRequestBody requestBody){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(requestBody)
                .when()
                .post(METADATA_FILTER)
                .then()
                .extract().response();
    }

    @Step("Изменение фильтра по метаданным")
    public static Response updateMetadataFilter(UpdateMetadataFilterRequestBody requestBody){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(requestBody)
                .when()
                .put(METADATA_FILTER)
                .then()
                .extract().response();
    }

    @Step("Удаление фильтра по метаданным")
    public static Response deleteMetadataFilter(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", id)
                .when()
                .delete(METADATA_FILTER)
                .then()
                .extract().response();
    }

    @Step("Получение списка фильтров по метаданным")
    public static Response getMetadataFilterList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(METADATA_FILTER_METADATA_FILTER)
                .then()
                .extract().response();
    }

    @Step("Получение списка фильтров по метаданным по протоколу odata")
    public static Response getMetadataFilterListOData(){

        return getMetadataFilterListOData(new HashMap<>());
    }

    @Step("Получение списка фильтров по метаданным по протоколу odata")
    public static Response getMetadataFilterListOData(Map<String, String> params){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(params)
                .when()
                .get(ODATA_METADATA_FILTER)
                .then()
                .extract().response();
    }

    @Step("Получение фильтра по метаданным по id")
    public static Response getMetadataFilterById(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_METADATA_FILTER + String.format("(%s)", id))
                .then()
                .extract().response();
    }

    @Step("Получение фильтра по метаданным по id в path param")
    public static Response getMetadataFilterByIdPath(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_METADATA_FILTER + "/{id}")
                .then()
                .extract().response();
    }

}
