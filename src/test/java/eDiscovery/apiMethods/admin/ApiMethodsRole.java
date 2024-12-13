package eDiscovery.apiMethods.admin;

import eDiscovery.UrlBase;
import eDiscovery.models.admin.role.AddRoleRequestModel;
import eDiscovery.models.admin.role.UpdateRoleRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiMethodsRole extends UrlBase {
    private static final String ROLE        = "/Role";
    private static final String ROLE_ROLE   = "/Role/Role";
    private static final String ODATA_ROLE  = "/odata/Role";

    @Step("Создание роли")
    public static Response addRole(AddRoleRequestModel addRoleRequestModel){

        return given()
                .spec(SpecificationsServer.getUrlSpecification(ADMIN_URL))
                .body(addRoleRequestModel)
                .when()
                .post(ROLE)
                .then()
                .extract().response();

    }

    @Step("Изменение роли")
    public static Response updateRole(UpdateRoleRequestModel updateRoleRequestModel){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .spec(SpecificationsServer.getUrlSpecification(ADMIN_URL))
                .body(updateRoleRequestModel)
                .when()
                .put(ROLE)
                .then()
                .extract().response();

    }

    @Step("Удаление роли")
    public static Response deleteRole(String id){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .spec(SpecificationsServer.getUrlSpecification(ADMIN_URL))
                .param("id", id)
                .when()
                .delete(ROLE)
                .then()
                .extract().response();

    }

    @Step("Получение списка полиси")
    public static Response getPolicies(){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(ROLE)
                .then()
                .extract().response();

    }

    @Step("Получение списка ролей")
    public static Response getRolesList(){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(ROLE_ROLE)
                .then()
                .extract().response();

    }

    @Step("Получение списка ролей по протоколу oData")
    public static Response getRolesListOData(){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(ODATA_ROLE)
                .then()
                .extract().response();

    }

    @Step("Получение роли по id")
    public static Response getRoleODataById(String id){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(ODATA_ROLE + "(" + id + ")")
                .then()
                .extract().response();

    }

    @Step("Получение роли по id")
    public static Response getRoleODataByIdPath(String id){
        //SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_ROLE + "/{id}")
                .then()
                .extract().response();
    }

}
