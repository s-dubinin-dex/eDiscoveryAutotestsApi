package eDiscovery.tests.apiTests.commonPositiveTests.adminService;

import com.google.gson.Gson;
import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsRole;
import eDiscovery.data.adminService.DataGeneratorRole;
import eDiscovery.models.admin.role.AddRoleRequestModel;
import eDiscovery.models.admin.role.CommonRoleResponseModel;
import eDiscovery.models.admin.role.PolicyResponseModel;
import eDiscovery.models.admin.role.UpdateRoleRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static eDiscovery.data.adminService.DataGeneratorRole.*;

@DisplayName("Common positive tests: Admin - Role")
public class RoleCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Создание роли")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание роли")
    @Description("Тест проверяет возможность создания роли")
    public void testAddRole(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddRoleRequestModel requestBody = AddRoleRequestModel.builder()
                .name(getRandomName())
                .policies(Collections.singletonList("ed.deal.read"))
                .build();

        CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.description).isNull();
        assertThat(responseBody.policies).isEqualTo(requestBody.policies);
        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(responseBody.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Изменение роли")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение роли")
    @Description("Тест проверяет возможность изменения роли")
    public void testUpdateRole(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel responseBodyRoleCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

        UpdateRoleRequestModel requestBodyRoleUpdate = UpdateRoleRequestModel.builder()
                .id(responseBodyRoleCreation.id)
                .name(getRandomName())
                .policies(Collections.singletonList("ed.deal.write"))
                .build();

        CommonRoleResponseModel responseBodyRoleUpdate = ApiMethodsRole.updateRole(requestBodyRoleUpdate).as(CommonRoleResponseModel.class);

        assertThat(responseBodyRoleUpdate.id).isEqualTo(responseBodyRoleCreation.id);
        assertThat(responseBodyRoleUpdate.name).isEqualTo(requestBodyRoleUpdate.name);
        assertThat(responseBodyRoleUpdate.description).isNull();
        assertThat(responseBodyRoleUpdate.policies).isEqualTo(requestBodyRoleUpdate.policies);
        assertThat(responseBodyRoleUpdate.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(responseBodyRoleUpdate.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Удаление роли")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление роли")
    @Description("Тест проверяет возможность удаления роли")
    public void testDeleteRole(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddRoleRequestModel requestBodyRoleCreation = AddRoleRequestModel.builder()
                .name(getRandomName())
                .policies(Collections.singletonList("ed.deal.read"))
                .build();

        CommonRoleResponseModel responseBodyRoleCreation = ApiMethodsRole.addRole(requestBodyRoleCreation).as(CommonRoleResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsRole.deleteRole(responseBodyRoleCreation.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel responseBody = ApiMethodsRole.getRoleODataByIdPath(responseBodyRoleCreation.id).as(CommonRoleResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyRoleCreation.id);
        assertThat(responseBody.name).matches(responseBody.id + "\\.deleted.*");
        assertThat(responseBody.description).isNull();
        assertThat(responseBody.policies).isEqualTo(requestBodyRoleCreation.policies);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).matches(dateTimeISOPattern());
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Получение списка полиси")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка полиси")
    @Description("Тест проверяет возможность получить список полиси")
    public void testGetPolicies(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<PolicyResponseModel> responseBody = ApiMethodsRole.getPolicies().jsonPath().getList("", PolicyResponseModel.class);

        Gson gson = new Gson();
        assertThat(gson.toJson(responseBody)).isEqualTo(gson.toJson(getEtalonPolicies()));
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Получение списка ролей")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка ролей")
    @Description("Тест проверяет возможность получения ролей")
    public void testGetRolesList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorRole.createRoleWithOnlyRequiredParameters();

        Response response = ApiMethodsRole.getRolesList();

        List<CommonRoleResponseModel> responseBody = response.jsonPath().getList("", CommonRoleResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        //TODO: Здесь и везде дальше проверять, что в ответе валидные данные с непустыми полями
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Получение списка ролей")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка ролей по протоколу oData")
    @Description("Тест проверяет возможность получения ролей по протоколу oData")
    public void testGetRolesListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorRole.createRoleWithOnlyRequiredParameters();

        Response response = ApiMethodsRole.getRolesListOData();

        List<CommonRoleResponseModel> responseBody = response.jsonPath().getList("value", CommonRoleResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        //TODO: Здесь и везде дальше проверять, что в ответе валидные данные с непустыми полями
    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Получение роли по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение роли по протоколу oData по id")
    @Description("Тест проверяет возможность получения роли по протоколу oData по id в скобках")
    public void testGetRoleODataById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel responseBodyCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

        CommonRoleResponseModel responseBody = ApiMethodsRole.getRoleODataById(responseBodyCreation.id).as(CommonRoleResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.description).isNull();
        assertThat(responseBody.policies).isEqualTo(responseBodyCreation.policies);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).isNull();

    }

    @Test
    @Epic("Сервис Admin")
    @Feature("Роль")
    @Story("Получение роли по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение роли по протоколу oData по id")
    @Description("Тест проверяет возможность получения роли по протоколу oData по id в path Param")
    public void testGetRoleODataByIdInPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRoleResponseModel responseBodyCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

        CommonRoleResponseModel responseBody = ApiMethodsRole.getRoleODataByIdPath(responseBodyCreation.id).as(CommonRoleResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.description).isNull();
        assertThat(responseBody.policies).isEqualTo(responseBodyCreation.policies);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).isNull();

    }

}
