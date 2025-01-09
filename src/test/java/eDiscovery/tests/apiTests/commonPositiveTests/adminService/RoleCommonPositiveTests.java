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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static eDiscovery.data.adminService.DataGeneratorRole.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Admin - Role: Основные позитивные тесты")
public class RoleCommonPositiveTests extends TestBase {

    @Nested
    @DisplayName("Admin - Role: Базовая проверка CRUD")
    class CheckBaseCRUDAdminRole{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Создание роли")
        @Description("Тест проверяет возможность создания роли")
        public void testAddRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorRole.createRoleWithOnlyRequiredParameters();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Изменение роли")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Изменение роли")
        @Description("Тест проверяет возможность изменения роли")
        public void testUpdateRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBodyRoleCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            UpdateRoleRequestModel requestBodyRoleUpdate = new UpdateRoleRequestModel(responseBodyRoleCreation);

            ApiMethodsRole.updateRole(requestBodyRoleUpdate);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Удаление роли")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Удаление роли")
        @Description("Тест проверяет возможность удаления роли")
        public void testDeleteRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBodyRoleCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsRole.deleteRole(responseBodyRoleCreation.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение списка полиси")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка политик для роли")
        @Description("Тест проверяет возможность получить список политик для роли")
        public void testGetPolicies(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsRole.getPolicies();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение списка ролей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка ролей")
        @Description("Тест проверяет возможность получения ролей")
        public void testGetRolesList(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsRole.getRolesList();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение списка ролей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка ролей по протоколу oData")
        @Description("Тест проверяет возможность получения ролей по протоколу oData")
        public void testGetRolesListOData(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsRole.getRolesListOData();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение роли по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение роли по протоколу oData по id")
        @Description("Тест проверяет возможность получения роли по протоколу oData по id в скобках")
        public void testGetRoleODataById(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBodyCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            ApiMethodsRole.getRoleODataById(responseBodyCreation.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение роли по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение роли по протоколу oData по id")
        @Description("Тест проверяет возможность получения роли по протоколу oData по id в path Param")
        public void testGetRoleODataByIdInPath(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBodyCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            ApiMethodsRole.getRoleODataByIdPath(responseBodyCreation.id);
        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при создании роли с обязательными параметрами")
    class CheckRoleCreationWithOnlyRequiredParametersCheckResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании роли с обязательными полями")
        @Description("Тест проверяет поля в теле ответа прии создании роли с обязательными полями")
        public void testAddRoleWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = DataGeneratorRole.getRoleModelWithOnlyRequiredParameters();
            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.description).isNull(),
                    () -> assertThat(responseBody.policies).isEqualTo(requestBody.policies),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при изменении роли с обязательными параметрами")
    class CheckRoleUpdateWithOnlyRequiredParametersCheckResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Изменение роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении роли с обязательными полями")
        @Description("Тест проверяет поля в теле ответа прии изменении роли с обязательными полями")
        public void testUpdateRoleWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBodyRoleCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);
            UpdateRoleRequestModel requestBody = new UpdateRoleRequestModel(responseBodyRoleCreation);

            requestBody.name = getRandomName();
            requestBody.policies = Collections.singletonList("ed.deal.write");

            CommonRoleResponseModel responseBody = ApiMethodsRole.updateRole(requestBody).as(CommonRoleResponseModel.class);

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(requestBody.id),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.description).isNull(),
                    () -> assertThat(responseBody.policies).isEqualTo(requestBody.policies),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при получении роли с обязательными параметрами из списка ролей")
    class CheckGetRoleWithOnlyRequiredParametersFromListOdataResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение списка ролей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка ролей")
        @Description("Тест проверяет поля в теле ответа при получении списка ролей")
        public void testGetRoleWithOnlyRequiredParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel roleCreationResponseBody = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            CommonRoleResponseModel roleFromOdataList = ApiMethodsRole.getRolesListOData().jsonPath().getList("value", CommonRoleResponseModel.class).stream()
                    .filter(element -> element.id.equals(roleCreationResponseBody.id))
                    .findFirst().orElse(null);

            assertThat(roleFromOdataList).isNotNull();

            assertAll(
                    () -> assertThat(roleFromOdataList.id).isEqualTo(roleCreationResponseBody.id),
                    () -> assertThat(roleFromOdataList.name).isEqualTo(roleCreationResponseBody.name),
                    () -> assertThat(roleFromOdataList.description).isNull(),
                    () -> assertThat(roleFromOdataList.policies).isEqualTo(roleCreationResponseBody.policies),
                    () -> assertThat(roleFromOdataList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(roleFromOdataList.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при получении роли с обязательными полями по id")
    class CheckGetRoleWithOnlyRequiredParametersByIdPathResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение роли по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении роли по id")
        @Description("Тест проверяет поля в теле ответа при получении роли по id")
        public void testGetRoleWithOnlyRequiredParametersFByIdPathCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel roleCreationResponseBody = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            CommonRoleResponseModel responseBody = ApiMethodsRole.getRoleODataByIdPath(roleCreationResponseBody.id).as(CommonRoleResponseModel.class);

            assertThat(responseBody).isNotNull();

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(roleCreationResponseBody.id),
                    () -> assertThat(responseBody.name).isEqualTo(roleCreationResponseBody.name),
                    () -> assertThat(responseBody.description).isNull(),
                    () -> assertThat(responseBody.policies).isEqualTo(roleCreationResponseBody.policies),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }
    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при получении списка политик для ролей")
    class CheckGetPoliciesResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Получение списка полиси")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка полиси")
        @Description("Тест проверяет поля в теле ответа при получении списка полиси")
        public void testGetPoliciesResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            List<PolicyResponseModel> responseBody = ApiMethodsRole.getPolicies().jsonPath().getList("", PolicyResponseModel.class);

            Gson gson = new Gson();
            assertThat(gson.toJson(responseBody)).isEqualTo(gson.toJson(getEtalonPolicies()));
        }
    }

}
