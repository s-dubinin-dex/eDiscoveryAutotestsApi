package eDiscovery.tests.apiTests.commonPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.helpers.admin.RoleHelper;
import eDiscovery.helpers.enums.PredefinedRoles;
import eDiscovery.models.admin.emplyee.AddEmployeeRequestModel;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;
import eDiscovery.models.admin.emplyee.UpdateEmployeeRequestModel;
import eDiscovery.models.admin.role.CommonRoleResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Создание пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание пользователя")
    @Description("Тест проверяет возможность создания пользователя")
    public void testAddEmployee(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel role = RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name);

        AddEmployeeRequestModel requestBody = AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(role.id)
                .email(faker.internet().emailAddress())
                .build();

        CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.roleId).isEqualTo(requestBody.roleId);
        assertThat(responseBody.email).isEqualTo(requestBody.email);
        assertThat(responseBody.role).isEqualTo(role.name);
        assertThat(responseBody.activationDate).isNull();
        assertThat(responseBody.createdUtc).matches(dateTimeUTCPattern());
        assertThat(responseBody.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Изменение пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение пользователя")
    @Description("Тест проверяет возможность изменения пользователя")
    public void testUpdateEmployee(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel roleForCreation = RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name);
        CommonRoleResponseModel roleForUpdate = RoleHelper.getRoleByName(PredefinedRoles.FULL_READ.name);

        AddEmployeeRequestModel requestBodyEmployeeCreation = AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(roleForCreation.id)
                .email(faker.internet().emailAddress())
                .build();

        CommonEmployeeResponseModel responseBodyEmployeeCreation = ApiMethodsEmployee.addEmployee(requestBodyEmployeeCreation).as(CommonEmployeeResponseModel.class);

        UpdateEmployeeRequestModel requestBodyForUpdate = UpdateEmployeeRequestModel.builder()
                .id(responseBodyEmployeeCreation.id)
                .name(getRandomName())
                .roleId(roleForUpdate.id)
                .build();

        CommonEmployeeResponseModel responseBodyEmployeeUpdate = ApiMethodsEmployee.updateEmployee(requestBodyForUpdate).as(CommonEmployeeResponseModel.class);

        assertThat(responseBodyEmployeeUpdate.id).isEqualTo(requestBodyForUpdate.id);
        assertThat(responseBodyEmployeeUpdate.name).isEqualTo(requestBodyForUpdate.name);
        assertThat(responseBodyEmployeeUpdate.roleId).isEqualTo(requestBodyForUpdate.roleId);
        assertThat(responseBodyEmployeeUpdate.email).isEqualTo(requestBodyEmployeeCreation.email);
        assertThat(responseBodyEmployeeUpdate.role).isEqualTo(roleForUpdate.name);
        assertThat(responseBodyEmployeeUpdate.activationDate).isNull();
        assertThat(responseBodyEmployeeUpdate.createdUtc).matches(dateTimeUTCPattern());
        assertThat(responseBodyEmployeeUpdate.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Удаление пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление пользователя")
    @Description("Тест проверяет возможность удаления пользователя")
    public void testDeleteEmployee(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonRoleResponseModel roleForCreation = RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name);

        AddEmployeeRequestModel requestBodyEmployeeCreation = AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(roleForCreation.id)
                .email(faker.internet().emailAddress())
                .build();

        CommonEmployeeResponseModel responseBodyEmployeeCreation = ApiMethodsEmployee.addEmployee(requestBodyEmployeeCreation).as(CommonEmployeeResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsEmployee.deleteEmployee(responseBodyEmployeeCreation.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        CommonEmployeeResponseModel responseBodyEmployeeAfterDeletion = ApiMethodsEmployee.getEmployeeByIdPath(responseBodyEmployeeCreation.id).as(CommonEmployeeResponseModel.class);

        assertThat(responseBodyEmployeeAfterDeletion.id).isEqualTo(responseBodyEmployeeCreation.id);
        assertThat(responseBodyEmployeeAfterDeletion.name).isEqualTo(responseBodyEmployeeCreation.name);
        assertThat(responseBodyEmployeeAfterDeletion.roleId).isEqualTo(responseBodyEmployeeCreation.roleId);
        assertThat(responseBodyEmployeeAfterDeletion.email).isEqualTo(responseBodyEmployeeCreation.id + ".deleted#" + requestBodyEmployeeCreation.email);
        assertThat(responseBodyEmployeeAfterDeletion.role).isEqualTo(RoleHelper.getRoleById(requestBodyEmployeeCreation.roleId).name);
        assertThat(responseBodyEmployeeAfterDeletion.activationDate).isNull();
        assertThat(responseBodyEmployeeAfterDeletion.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyEmployeeAfterDeletion.deletedUtc).matches(dateTimeISOPattern());
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Генерация приглашения")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Генерация приглашения")
    @Description("Тест проверяет возможность генерации нового приглашения с новым токеном активации")
    public void testUpdateEmployeeInvitation(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel roleForCreation = RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name);

        AddEmployeeRequestModel requestBodyEmployeeCreation = AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(roleForCreation.id)
                .email(faker.internet().emailAddress())
                .build();

        CommonEmployeeResponseModel responseBodyEmployeeCreation = ApiMethodsEmployee.addEmployee(requestBodyEmployeeCreation).as(CommonEmployeeResponseModel.class);

        CommonEmployeeResponseModel responseBodyEmployeeAfterUpdateInvitation = ApiMethodsEmployee.updateInvitation(responseBodyEmployeeCreation.id).as(CommonEmployeeResponseModel.class);

        assertThat(responseBodyEmployeeAfterUpdateInvitation.id).isEqualTo(responseBodyEmployeeCreation.id);
        assertThat(responseBodyEmployeeAfterUpdateInvitation.name).isEqualTo(responseBodyEmployeeCreation.name);
        assertThat(responseBodyEmployeeAfterUpdateInvitation.roleId).isEqualTo(responseBodyEmployeeCreation.roleId);
        assertThat(responseBodyEmployeeAfterUpdateInvitation.email).isEqualTo(responseBodyEmployeeCreation.email);
        assertThat(responseBodyEmployeeAfterUpdateInvitation.role).isEqualTo(roleForCreation.name);
        assertThat(responseBodyEmployeeAfterUpdateInvitation.activationDate).isNull();
        assertThat(responseBodyEmployeeAfterUpdateInvitation.createdUtc).matches(dateTimeUTCPattern());
        assertThat(responseBodyEmployeeAfterUpdateInvitation.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Получение списка пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка пользователей")
    @Description("Тест проверяет возможность получения списка пользователей")
    public void testGetRolesList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Response response = ApiMethodsEmployee.getEmployeeList();

        List<CommonEmployeeResponseModel> responseBody = response.jsonPath().getList("", CommonEmployeeResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        //TODO: Здесь и везде дальше проверять, что в ответе валидные данные с непустыми полями
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Получение списка пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка пользователей по протоколу odata")
    @Description("Тест проверяет возможность получения списка пользователей по протоколу odata")
    public void testGetRolesListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Response response = ApiMethodsEmployee.getEmployeeListOData();

        List<CommonEmployeeResponseModel> responseBody = response.jsonPath().getList("value", CommonEmployeeResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        //TODO: Здесь и везде дальше проверять, что в ответе валидные данные с непустыми полями
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Получение пользователя по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение пользователя по протоколу oData по id в скобках")
    @Description("Тест проверяет возможность получения пользователя по протоколу oData по id в скобках")
    public void testGetRoleById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel role = RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name);

        AddEmployeeRequestModel requestBody = AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(role.id)
                .email(faker.internet().emailAddress())
                .build();

        CommonEmployeeResponseModel responseBodyCreation = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

        CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.getEmployeeById(responseBodyCreation.id).as(CommonEmployeeResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.roleId).isEqualTo(responseBodyCreation.roleId);
        assertThat(responseBody.email).isEqualTo(responseBodyCreation.email);
        assertThat(responseBody.role).isEqualTo(responseBodyCreation.role);
        assertThat(responseBody.activationDate).isEqualTo(responseBodyCreation.activationDate);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).isEqualTo(responseBodyCreation.deletedUtc);
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Получение пользователя по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение пользователя по протоколу oData по id в path param")
    @Description("Тест проверяет возможность получения пользователя по протоколу oData по id в path param")
    public void testGetRoleByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel role = RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name);

        AddEmployeeRequestModel requestBody = AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(role.id)
                .email(faker.internet().emailAddress())
                .build();

        CommonEmployeeResponseModel responseBodyCreation = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

        CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.getEmployeeByIdPath(responseBodyCreation.id).as(CommonEmployeeResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.roleId).isEqualTo(responseBodyCreation.roleId);
        assertThat(responseBody.email).isEqualTo(responseBodyCreation.email);
        assertThat(responseBody.role).isEqualTo(responseBodyCreation.role);
        assertThat(responseBody.activationDate).isEqualTo(responseBodyCreation.activationDate);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).isEqualTo(responseBodyCreation.deletedUtc);
    }

}