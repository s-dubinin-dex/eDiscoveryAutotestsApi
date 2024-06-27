package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.dealManipulation.*;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealManipulation extends UrlBase {

    @Step("Создание дела")
    public static Response addDeal(AddDealManipulationRequestModel addDealManipulationRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(addDealManipulationRequestModel)
                .when()
                .post("/DealManipulation")
                .andReturn();
    }

    @Step("Изменение дела")
    public static Response updateDeal(UpdateDealManipulationRequestModel updateDealManipulationRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateDealManipulationRequestModel)
                .when()
                .put("/DealManipulation")
                .andReturn();
    }

    @Step("Удаление дела")
    public static Response deleteDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .delete("/DealManipulation")
                .andReturn();
    }

    @Step("Просмотр информации по делу")
    public static Response getDealCard(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .get("/DealManipulation/card")
                .andReturn();
    }

    @Step("Восстановление удалённого дела (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response restoreDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/restore")
                .andReturn();
    }

    @Step("Закрытие дела")
    public static Response closeDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/close")
                .andReturn();
    }

    @Step("Открытие закрытого дела (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response uncloseDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/unclose")
                .andReturn();
    }

    @Step("Остановка дела")
    public static Response stopDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/stop")
                .andReturn();
    }

    @Step("Запуск дела")
    public static Response startDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put("/DealManipulation/start")
                .andReturn();
    }

    @Step("Помещение файла в карантин")
    public static Response toQuarantine(ToOutQuarantineRequestModel toOutQuarantineRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toOutQuarantineRequestModel)
                .when()
                .put("/DealManipulation/toQuarantine")
                .andReturn();
    }

    @Step("Изъятие файла из карантина")
    public static Response outQuarantine(ToOutQuarantineRequestModel toOutQuarantineRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toOutQuarantineRequestModel)
                .when()
                .put("/DealManipulation/outQuarantine")
                .andReturn();
    }

    @Step("Экспорт файла")
    public static Response toExport(ToExportRequestModel toExportRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toExportRequestModel)
                .when()
                .put("/DealManipulation/toExport")
                .andReturn();
    }

    @Step("Получение списка активных задач по агенту")
    public static Response activeTasks(ActiveTasksRequestsModel activeTasksRequestsModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(activeTasksRequestsModel)
                .when()
                .get("/DealManipulation/activeTasks")
                .andReturn();
    }

    @Step("Изменение статуса задачи (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response changeTaskStatus(ToExportRequestModel toExportRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toExportRequestModel)
                .when()
                .put("/DealManipulation/toExport")
                .andReturn();
    }

    @Step("Получение списка дел")
    public static Response getDealManipulationList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get("/DealManipulation/DealManipulation")
                .andReturn();

    }

    @Step("Получение списка дел по протоколу oData")
    public static Response getDealManipulationListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get("/odata/DealManipulation")
                .andReturn();

    }

}
