package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.dealManipulation.AddDealManipulationRequestModel;
import eDiscovery.models.deal.dealManipulation.UpdateDealManipulationRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealManipulation extends UrlBase {

    @Step("Создание дела")
    public static Response addDeal(AddDealManipulationRequestModel addDealManipulationRequestModel){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .body(addDealManipulationRequestModel)
                .when()
                .post("/DealManipulation")
                .andReturn();
    }

    @Step("Изменение дела")
    public static Response updateDeal(UpdateDealManipulationRequestModel updateDealManipulationRequestModel){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .body(updateDealManipulationRequestModel)
                .when()
                .put("/DealManipulation")
                .andReturn();
    }

    @Step("Удаление дела")
    public static Response deleteDeal(String dealId){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", dealId)
                .when()
                .delete("/DealManipulation")
                .andReturn();
    }

    @Step("Просмотр информации по делу")
    public static Response getDealCard(String dealId){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", dealId)
                .when()
                .get("/DealManipulation/card")
                .andReturn();
    }

    @Step("Восстановление удалённого дела (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response restoreDeal(String dealId){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/restore")
                .andReturn();
    }

    @Step("Закрытие дела")
    public static Response closeDeal(String dealId){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/close")
                .andReturn();
    }

    @Step("Открытие закрытого дела (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response uncloseDeal(String dealId){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/unclose")
                .andReturn();
    }

    @Step("Остановка дела")
    public static Response stopDeal(String dealId){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/stop")
                .andReturn();
    }

}
