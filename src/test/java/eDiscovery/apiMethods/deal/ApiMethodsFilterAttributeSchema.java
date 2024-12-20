package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsFilterAttributeSchema extends UrlBase {

    private static final String FILTER_ATTRIBUTE_SCHEMA_FILTER_ATTRIBUTE_SCHEMA = "/FilterAttributeSchema/FilterAttributeSchema";
    private static final String ODATA_FILTER_ATTRIBUTE_SCHEMA                   = "/odata/FilterAttributeSchema";

    @Step("Получение списка схем фильтров метаданных")
    public static Response getFilterAttributeSchemaList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(FILTER_ATTRIBUTE_SCHEMA_FILTER_ATTRIBUTE_SCHEMA)
                .then()
                .extract().response();
    }

    @Step("Получение списка схем фильтров метаданных")
    public static Response getFilterAttributeSchemaListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_FILTER_ATTRIBUTE_SCHEMA)
                .then()
                .extract().response();
    }

    @Step("Получение схемы фильтра метаданных по id")
    public static Response getFilterAttributeSchemaById(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_FILTER_ATTRIBUTE_SCHEMA + String.format("(%s)", id))
                .then()
                .extract().response();
    }

    @Step("Получение схемы фильтра метаданных по id в path")
    public static Response getFilterAttributeSchemaByIdPath(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_FILTER_ATTRIBUTE_SCHEMA + "/{id}")
                .then()
                .extract().response();
    }

}
