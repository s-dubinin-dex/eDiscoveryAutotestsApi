package eDiscovery.apiMethods.admin;

import eDiscovery.UrlBase;
import eDiscovery.models.admin.emplyee.AddEmployeeRequestModel;
import eDiscovery.models.admin.emplyee.UpdateEmployeeRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsEmployee extends UrlBase {

    private static final String EMPLOYEE            = "/Employee";
    private static final String EMPLOYEE_INVITATION = "/Employee/invitation";
    private static final String EMPLOYEE_EMPLOYEE   = "/Employee/Employee";
    private static final String ODATA_EMPLOYEE      = "/odata/Employee";

    @Step("Создание пользователя")
    public static Response addEmployee(AddEmployeeRequestModel addEmployeeRequestModel){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .body(addEmployeeRequestModel)
                .when()
                .post(EMPLOYEE)
                .then()
                .extract().response();

    }

    @Step("Изменение пользователя")
    public static Response updateEmployee(UpdateEmployeeRequestModel updateEmployeeRequestModel){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .body(updateEmployeeRequestModel)
                .when()
                .put(EMPLOYEE)
                .then()
                .extract().response();

    }

    @Step("Удаление пользователя")
    public static Response deleteEmployee(String employeeId){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .param("employeeId", employeeId)
                .when()
                .delete(EMPLOYEE)
                .then()
                .extract().response();

    }

    @Step("Генерация нового приглашения с новым токеном активации")
    public static Response updateInvitation(String employeeId){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .body("\"" +employeeId + "\"")
                .when()
                .put(EMPLOYEE_INVITATION)
                .then()
                .extract().response();

    }

    @Step("Получение списка пользователей")
    public static Response getEmployeeList(){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(EMPLOYEE_EMPLOYEE)
                .then()
                .extract().response();

    }

    @Step("Получение списка пользователей по протоколу odata")
    public static Response getEmployeeListOData(){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(ODATA_EMPLOYEE)
                .then()
                .extract().response();

    }

    @Step("Получение пользователя по id")
    public static Response getEmployeeById(String employeeId){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .when()
                .get(ODATA_EMPLOYEE + String.format("(%s)", employeeId))
                .then()
                .extract().response();

    }

    @Step("Получение пользователя по id")
    public static Response getEmployeeByIdPath(String employeeId){
        SpecificationsServer.setBaseUrl(ADMIN_URL);
        return given()
                .pathParam("employeeId", employeeId)
                .when()
                .get(ODATA_EMPLOYEE + "/{employeeId}")
                .then()
                .extract().response();

    }

}
