package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.models.classifier.rule.AddRuleRequestModel;
import eDiscovery.models.classifier.rule.UpdateRuleRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsRule extends UrlBase {

    private static final String RULE        = "/Rule";
    private static final String RULE_RULE   = "/Rule/Rule";
    private static final String ODATA_RULE  = "/odata/Rule";

    @Step("Создание правила категоризации")
    public static Response addRule(AddRuleRequestModel addRuleRequestModel){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(addRuleRequestModel)
                .when()
                .post(RULE)
                .then()
                .extract().response();
    }

    @Step("Изменение правила категоризации")
    public static Response updateRule(UpdateRuleRequestModel updateRuleRequestModel){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(updateRuleRequestModel)
                .when()
                .put(RULE)
                .then()
                .extract().response();
    }

    @Step("Удаление правила категоризации")
    public static Response deleteRule(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .param("id", id)
                .when()
                .delete(RULE)
                .then()
                .extract().response();
    }

    @Step("Получение списка правил категоризации")
    public static Response getRuleList(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(RULE_RULE)
                .then()
                .extract().response();
    }

    @Step("Получение списка правил категоризации по протоколу odata")
    public static Response getRuleListOData(Map<String, String> params){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .params(params)
                .when()
                .get(ODATA_RULE)
                .then()
                .extract().response();
    }

    @Step("Получение списка правил категоризации по протоколу odata")
    public static Response getRuleListOData(){
        return getRuleListOData(new HashMap<>());
    }


    @Step("Получение правила категоризации по id")
    public static Response getRuleById(String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_RULE + String.format("(%s)", id))
                .then()
                .extract().response();
    }

    @Step("Получение правила категоризации по id в path param")
    public static Response getRuleByIdPath(String id, Map<String, String> params){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .params(params)
                .pathParam("id", id)
                .when()
                .get(ODATA_RULE + "/{id}")
                .then()
                .extract().response();
    }

    @Step("Получение правила категоризации по id в path param")
    public static Response getRuleByIdPath(String id){
        return getRuleByIdPath(id, new HashMap<>());
    }

}
