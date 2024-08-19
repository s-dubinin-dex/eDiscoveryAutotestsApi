package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.dealManipulation.*;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealManipulation extends UrlBase {
    private static final String DEAL_MANIPULATION                       = "/DealManipulation";
    private static final String DEAL_MANIPULATION_CARD                  = "/DealManipulation/card";
    private static final String DEAL_MANIPULATION_RESTORE               = "/DealManipulation/restore";
    private static final String DEAL_MANIPULATION_CLOSE                 = "/DealManipulation/close";
    private static final String DEAL_MANIPULATION_UNCLOSE               = "/DealManipulation/unclose";
    private static final String DEAL_MANIPULATION_STOP                  = "/DealManipulation/stop";
    private static final String DEAL_MANIPULATION_START                 = "/DealManipulation/start";
    private static final String DEAL_MANIPULATION_TO_QUARANTINE         = "/DealManipulation/toQuarantine";
    private static final String DEAL_MANIPULATION_OUT_QUARANTINE        = "/DealManipulation/outQuarantine";
    private static final String DEAL_MANIPULATION_TO_EXPORT             = "/DealManipulation/toExport";
    private static final String DEAL_MANIPULATION_ACTIVE_TASKS          = "/DealManipulation/activeTasks";
    private static final String DEAL_MANIPULATION_CHANGE_TASK_STATUS    = "/DealManipulation/changeTaskStatus";
    private static final String DEAL_MANIPULATION_DEAL_MANIPULATION     = "/DealManipulation/DealManipulation";
    private static final String ODATA_DEAL_MANIPULATION                 = "/odata/DealManipulation";


    @Step("Создание дела")
    public static Response addDeal(AddDealManipulationRequestModel addDealManipulationRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(addDealManipulationRequestModel)
                .when()
                .post(DEAL_MANIPULATION)
                .then()
                .extract().response();
    }

    @Step("Изменение дела")
    public static Response updateDeal(UpdateDealManipulationRequestModel updateDealManipulationRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateDealManipulationRequestModel)
                .when()
                .put(DEAL_MANIPULATION)
                .then()
                .extract().response();
    }

    @Step("Удаление дела")
    public static Response deleteDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .delete(DEAL_MANIPULATION)
                .then()
                .extract().response();
    }

    @Step("Просмотр информации по делу")
    public static Response getDealCard(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .get(DEAL_MANIPULATION_CARD)
                .then()
                .extract().response();
    }

    @Step("Восстановление удалённого дела (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response restoreDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put(DEAL_MANIPULATION_RESTORE)
                .then()
                .extract().response();
    }

    @Step("Закрытие дела")
    public static Response closeDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put(DEAL_MANIPULATION_CLOSE)
                .then()
                .extract().response();
    }

    @Step("Открытие закрытого дела (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response uncloseDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put(DEAL_MANIPULATION_UNCLOSE)
                .then()
                .extract().response();
    }

    @Step("Остановка дела")
    public static Response stopDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put(DEAL_MANIPULATION_STOP)
                .then()
                .extract().response();
    }

    @Step("Запуск дела")
    public static Response startDeal(String dealId){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", dealId)
                .when()
                .put(DEAL_MANIPULATION_START)
                .then()
                .extract().response();
    }

    @Step("Помещение файла в карантин")
    public static Response toQuarantine(ToOutQuarantineRequestModel toOutQuarantineRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toOutQuarantineRequestModel)
                .when()
                .put(DEAL_MANIPULATION_TO_QUARANTINE)
                .then()
                .extract().response();
    }

    @Step("Изъятие файла из карантина")
    public static Response outQuarantine(ToOutQuarantineRequestModel toOutQuarantineRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toOutQuarantineRequestModel)
                .when()
                .put(DEAL_MANIPULATION_OUT_QUARANTINE)
                .then()
                .extract().response();
    }

    @Step("Экспорт файла")
    public static Response toExport(ToExportRequestModel toExportRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toExportRequestModel)
                .when()
                .put(DEAL_MANIPULATION_TO_EXPORT)
                .then()
                .extract().response();
    }

    @Step("Получение списка активных задач по агенту")
    public static Response activeTasks(ActiveTasksRequestsModel activeTasksRequestsModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(activeTasksRequestsModel)
                .when()
                .get(DEAL_MANIPULATION_ACTIVE_TASKS)
                .then()
                .extract().response();
    }

    @Step("Изменение статуса задачи (СЛУЖЕБНЫЙ МЕТОД)")
    public static Response changeTaskStatus(ToExportRequestModel toExportRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(toExportRequestModel)
                .when()
                .put(DEAL_MANIPULATION_CHANGE_TASK_STATUS)
                .then()
                .extract().response();
    }

    @Step("Получение списка дел")
    public static Response getDealManipulationList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_MANIPULATION_DEAL_MANIPULATION)
                .then()
                .extract().response();

    }

    @Step("Получение списка дел по протоколу oData")
    public static Response getDealManipulationListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_DEAL_MANIPULATION)
                .then()
                .extract().response();

    }

}
