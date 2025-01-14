package eDiscovery.tests.apiTests.commonPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.data.adminService.DataGeneratorEmployee;
import eDiscovery.helpers.DataChecker;
import eDiscovery.helpers.admin.PredefinedRoles;
import eDiscovery.helpers.admin.RoleHelper;
import eDiscovery.models.admin.emplyee.AddEmployeeRequestModel;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;
import eDiscovery.models.admin.emplyee.UpdateEmployeeRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Admin - Employee: Основные позитивные тесты")
public class EmployeeCommonPositiveTests extends TestBase {

    @Nested
    @Tag("smoke")
    @DisplayName("Admin - Employee: Базовая проверка CRUD")
    class CheckBaseCRUDAdminEmployee{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Создание пользователя")
        @Description("Тест проверяет возможность создания пользователя")
        public void testAddEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Изменение пользователя")
        @Description("Тест проверяет возможность изменения пользователя")
        public void testUpdateEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();

            UpdateEmployeeRequestModel requestBodyForUpdate = new UpdateEmployeeRequestModel(responseBodyEmployeeCreation);

            ApiMethodsEmployee.updateEmployee(requestBodyForUpdate);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Удаление пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Удаление пользователя")
        @Description("Тест проверяет возможность удаления пользователя")
        public void testDeleteEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsEmployee.deleteEmployee(responseBodyEmployeeCreation.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения")
        @Description("Тест проверяет возможность генерации нового приглашения с новым токеном активации")
        public void testUpdateEmployeeInvitation(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();

            ApiMethodsEmployee.updateInvitation(responseBodyEmployeeCreation.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка пользователей")
        @Description("Тест проверяет возможность получения списка пользователей")
        public void testGetEmployeeList(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsEmployee.getEmployeeList();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка пользователей по протоколу odata")
        @Description("Тест проверяет возможность получения списка пользователей по протоколу odata")
        public void testGetEmployeeListOData(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsEmployee.getEmployeeListOData();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение пользователя по протоколу oData по id в скобках")
        @Description("Тест проверяет возможность получения пользователя по протоколу oData по id в скобках")
        public void testGetEmployeeById(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();

            ApiMethodsEmployee.getEmployeeById(responseBodyEmployeeCreation.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Получение пользователя по id")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение пользователя по протоколу oData по id в path param")
        @Description("Тест проверяет возможность получения пользователя по протоколу oData по id в path param")
        public void testGetEmployeeByIdPath(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();

            ApiMethodsEmployee.getEmployeeByIdPath(responseBodyEmployeeCreation.id);
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка тела ответа при создании пользователя с обязательными параметрами")
    class CheckEmployeeCreationWithOnlyRequiredParametersResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании пользователя с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании пользователя с обязательными полями")
        public void testAddEmployeeWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel employeeCreationRequestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();
            CommonEmployeeResponseModel employeeCreationResponseBody = ApiMethodsEmployee.addEmployee(employeeCreationRequestBody).as(CommonEmployeeResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(employeeCreationResponseBody.id)).isTrue(),
                    () -> assertThat(employeeCreationResponseBody.name).isEqualTo(employeeCreationRequestBody.name),
                    () -> assertThat(employeeCreationResponseBody.roleId).isEqualTo(employeeCreationRequestBody.roleId),
                    () -> assertThat(employeeCreationResponseBody.email).isEqualTo(employeeCreationRequestBody.email),
                    () -> assertThat(employeeCreationResponseBody.role).isEqualTo(PredefinedRoles.FULL_WRITE.name),
                    () -> assertThat(employeeCreationResponseBody.activationDate).isNull(),
                    () -> assertThat(employeeCreationResponseBody.createdUtc).matches(DataChecker.dateTimeCommonPattern()),
                    () -> assertThat(employeeCreationResponseBody.deletedUtc).isNull()
            );
        }
    }

    @Nested
    @DisplayName("Admin - Employee: Проверка тела ответа при изменении пользователя с обязательными параметрами")
    class CheckEmployeeUpdateWithOnlyRequiredParametersResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении пользователя с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при изменении пользователя с обязательными полями")
        public void testUpdateEmployeeWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel employeeCreationForUpdateResponseBody = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();
            UpdateEmployeeRequestModel updateEmployeeRequestBody = new UpdateEmployeeRequestModel(employeeCreationForUpdateResponseBody);

            updateEmployeeRequestBody.name = DataGeneratorCommon.getRandomName();
            updateEmployeeRequestBody.roleId = RoleHelper.getRoleByName(PredefinedRoles.FULL_READ.name).id;

            CommonEmployeeResponseModel employeeUpdateResponseBody = ApiMethodsEmployee.updateEmployee(updateEmployeeRequestBody).as(CommonEmployeeResponseModel.class);

            assertAll(
                    () -> assertThat(employeeUpdateResponseBody.id).isEqualTo(updateEmployeeRequestBody.id),
                    () -> assertThat(employeeUpdateResponseBody.name).isEqualTo(updateEmployeeRequestBody.name),
                    () -> assertThat(employeeUpdateResponseBody.roleId).isEqualTo(updateEmployeeRequestBody.roleId),
                    () -> assertThat(employeeUpdateResponseBody.email).isEqualTo(employeeCreationForUpdateResponseBody.email),
                    () -> assertThat(employeeUpdateResponseBody.role).isEqualTo(PredefinedRoles.FULL_READ.name),
                    () -> assertThat(employeeUpdateResponseBody.activationDate).isNull(),
                    () -> assertThat(employeeUpdateResponseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern()),
                    () -> assertThat(employeeUpdateResponseBody.deletedUtc).isNull()
            );
        }
    }

    @Nested
    @DisplayName("Admin - Employee: Проверка тела ответа при получении пользователя с обязательными параметрами из списка пользователей")
    class CheckGetEmployeeWithOnlyRequiredParametersFromListOdataResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка пользователей")
        @Description("Тест проверяет поля в теле ответа при получении списка пользователей")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel employeeCreationResponseBody = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();
            CommonEmployeeResponseModel employeeFromOdataList = ApiMethodsEmployee.getEmployeeListOData().jsonPath().getList("value", CommonEmployeeResponseModel.class).stream()
                    .filter(element -> element.id.equals(employeeCreationResponseBody.id))
                    .findFirst().orElse(null);

            assertThat(employeeFromOdataList).isNotNull();

            assertAll(
                    () -> assertThat(employeeFromOdataList.id).isEqualTo(employeeCreationResponseBody.id),
                    () -> assertThat(employeeFromOdataList.name).isEqualTo(employeeCreationResponseBody.name),
                    () -> assertThat(employeeFromOdataList.roleId).isEqualTo(employeeCreationResponseBody.roleId),
                    () -> assertThat(employeeFromOdataList.email).isEqualTo(employeeCreationResponseBody.email),
                    () -> assertThat(employeeFromOdataList.role).isEqualTo(employeeCreationResponseBody.role),
                    () -> assertThat(employeeFromOdataList.activationDate).isEqualTo(employeeCreationResponseBody.activationDate),
                    () -> assertThat(employeeFromOdataList.createdUtc).matches(DataChecker.dateTimeCommonPattern()),
                    () -> assertThat(employeeFromOdataList.deletedUtc).isEqualTo(employeeCreationResponseBody.deletedUtc)
            );
        }
    }

    @Nested
    @DisplayName("Admin - Employee: Проверка тела ответа при получении пользователя с обязательными полями по id")
    class CheckGetEmployeeWithOnlyRequiredParametersByIdPathResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении пользователя по id")
        @Description("Тест проверяет поля в теле ответа при получении пользователя по id")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel employeeCreationRequestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();
            CommonEmployeeResponseModel employeeCreationResponseBody = ApiMethodsEmployee.addEmployee(employeeCreationRequestBody).as(CommonEmployeeResponseModel.class);
            CommonEmployeeResponseModel employeeByIdPath = ApiMethodsEmployee.getEmployeeByIdPath(employeeCreationResponseBody.id).as(CommonEmployeeResponseModel.class);

            assertAll(
                    () -> assertThat(employeeByIdPath.id).isEqualTo(employeeCreationResponseBody.id),
                    () -> assertThat(employeeByIdPath.name).isEqualTo(employeeCreationResponseBody.name),
                    () -> assertThat(employeeByIdPath.roleId).isEqualTo(employeeCreationResponseBody.roleId),
                    () -> assertThat(employeeByIdPath.email).isEqualTo(employeeCreationResponseBody.email),
                    () -> assertThat(employeeByIdPath.role).isEqualTo(employeeCreationResponseBody.role),
                    () -> assertThat(employeeByIdPath.activationDate).isEqualTo(employeeCreationResponseBody.activationDate),
                    () -> assertThat(employeeByIdPath.createdUtc).matches(DataChecker.dateTimeCommonPattern()),
                    () -> assertThat(employeeByIdPath.deletedUtc).isEqualTo(employeeCreationResponseBody.deletedUtc)
            );
        }
    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей ответа при генерации приглашения")
    class CheckUpdateEmployeeInvitationResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователи")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при генерации приглашения")
        @Description("Тест проверяет поля в теле ответа при генерации приглашения")
        public void testUpdateEmployeeInvitationCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel employeeCreationRequestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();
            CommonEmployeeResponseModel employeeCreationResponseBody = ApiMethodsEmployee.addEmployee(employeeCreationRequestBody).as(CommonEmployeeResponseModel.class);
            CommonEmployeeResponseModel employeeInvitationResponseBody = ApiMethodsEmployee.updateInvitation(employeeCreationResponseBody.id).as(CommonEmployeeResponseModel.class);

            assertAll(
                    () -> assertThat(employeeInvitationResponseBody.id).isEqualTo(employeeCreationResponseBody.id),
                    () -> assertThat(employeeInvitationResponseBody.name).isEqualTo(employeeCreationResponseBody.name),
                    () -> assertThat(employeeInvitationResponseBody.roleId).isEqualTo(employeeCreationResponseBody.roleId),
                    () -> assertThat(employeeInvitationResponseBody.email).isEqualTo(employeeCreationResponseBody.email),
                    () -> assertThat(employeeInvitationResponseBody.role).isEqualTo(employeeCreationResponseBody.role),
                    () -> assertThat(employeeInvitationResponseBody.activationDate).isNull(),
                    () -> assertThat(employeeInvitationResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(employeeInvitationResponseBody.deletedUtc).isNull()
            );

        }

    }

}
