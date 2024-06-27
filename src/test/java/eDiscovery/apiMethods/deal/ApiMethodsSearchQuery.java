package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsSearchQuery extends UrlBase {

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(AddSearchQueryRequestModel addSearchQueryRequestModel){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(addSearchQueryRequestModel)
                .when()
                .post("/SearchQuery")
                .andReturn();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(UpdateSearchQueryRequestModel updateSearchQueryRequestModel){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateSearchQueryRequestModel)
                .when()
                .put("/SearchQuery")
                .andReturn();
    }

    @Step("Удаление поискового запроса")
    public static Response deleteSearchQuery(String id){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", id)
                .when()
                .delete("/SearchQuery")
                .andReturn();
    }

    @Step("Получение списка поисковых запросов")
    public static Response getSearchQueryList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get("/SearchQuery/SearchQuery")
                .andReturn();

    }

    @Step("Получение списка поисковых запросов по протоколу oData")
    public static Response getSearchQueryListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get("/odata/SearchQuery")
                .andReturn();

    }
}
