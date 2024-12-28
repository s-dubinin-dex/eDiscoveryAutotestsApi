package eDiscovery.tests.apiTests.commonPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.data.adminService.DataGeneratorEmployee;
import eDiscovery.helpers.DataChecker;
import eDiscovery.helpers.admin.RoleHelper;
import eDiscovery.models.admin.emplyee.AddEmployeeRequestModel;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;
import eDiscovery.models.admin.emplyee.UpdateEmployeeRequestModel;
import eDiscovery.models.admin.role.CommonRoleResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Admin - Employee: Основные позитивные тесты")
public class EmployeeCommonPositiveTests extends TestBase {

    private static AddEmployeeRequestModel EMPLOYEE_CREATION_REQUEST_BODY;
    private static CommonEmployeeResponseModel EMPLOYEE_CREATION_RESPONSE_BODY;

    @BeforeAll
    public static void setUp(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        EMPLOYEE_CREATION_REQUEST_BODY = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();
        EMPLOYEE_CREATION_RESPONSE_BODY = ApiMethodsEmployee.addEmployee(EMPLOYEE_CREATION_REQUEST_BODY).as(CommonEmployeeResponseModel.class);
    };

    @Nested
    @DisplayName("Admin - Employee: Проверка базового CRUD")
    class CheckBaseCRUDAdminEmployee{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Создание пользователя")
        @Description("Тест проверяет возможность создания пользователя")
        public void testAddEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorEmployee.createEmployeeModelWithOnlyRequiredParameters();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Изменение пользователя")
        @Description("Тест проверяет возможность изменения пользователя")
        public void testUpdateEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeModelWithOnlyRequiredParameters().as(CommonEmployeeResponseModel.class);

            UpdateEmployeeRequestModel requestBodyForUpdate = new UpdateEmployeeRequestModel(responseBodyEmployeeCreation);

            ApiMethodsEmployee.updateEmployee(requestBodyForUpdate);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Удаление пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Удаление пользователя")
        @Description("Тест проверяет возможность удаления пользователя")
        public void testDeleteEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

            CommonEmployeeResponseModel responseBodyEmployeeCreation = DataGeneratorEmployee.createEmployeeModelWithOnlyRequiredParameters().as(CommonEmployeeResponseModel.class);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsEmployee.deleteEmployee(responseBodyEmployeeCreation.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения")
        @Description("Тест проверяет возможность генерации нового приглашения с новым токеном активации")
        public void testUpdateEmployeeInvitation(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsEmployee.updateInvitation(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
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
        @Feature("Пользователь")
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
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение пользователя по протоколу oData по id в скобках")
        @Description("Тест проверяет возможность получения пользователя по протоколу oData по id в скобках")
        public void testGetEmployeeById(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsEmployee.getEmployeeById(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение пользователя по протоколу oData по id в path param")
        @Description("Тест проверяет возможность получения пользователя по протоколу oData по id в path param")
        public void testGetEmployeeByIdPath(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsEmployee.getEmployeeByIdPath(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей ответа при создании сотрудника с обязательными параметрами")
    class CheckEmployeeCreationWithOnlyRequiredParametersResponseFields{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается корректный id")
        @Description("Тест проверяет, что при создании пользователя возвращается корректный id")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsValidId(){
            assertThat(isValidUUID(EMPLOYEE_CREATION_RESPONSE_BODY.id)).isTrue();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается name, переданный при создании")
        @Description("Тест проверяет, что при создании пользователя возвращается name, переданный при создании")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsName(){
            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.name).isEqualTo(EMPLOYEE_CREATION_REQUEST_BODY.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается roleId, переданный при создании")
        @Description("Тест проверяет, что при создании пользователя возвращается roleId, переданный при создании")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsRoleId(){
            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.roleId).isEqualTo(EMPLOYEE_CREATION_REQUEST_BODY.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается email, переданный при создании")
        @Description("Тест проверяет, что при создании пользователя возвращается email, переданный при создании")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsEmail(){
            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.email).isEqualTo(EMPLOYEE_CREATION_REQUEST_BODY.email);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается название переданной роли")
        @Description("Тест проверяет, что при создании пользователя возвращается название переданной роли")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel role = RoleHelper.getRoleById(EMPLOYEE_CREATION_REQUEST_BODY.roleId);

            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.role).isEqualTo(role.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя activation date = null")
        @Description("Тест проверяет, что при создании пользователя activation date = null")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsNullActivationDate(){
            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.activationDate).isNull();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается createdUtc в корректном формате")
        @Description("Тест проверяет, что при создании пользователя возвращается createdUtc в корректном формате")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsCreatedUtc(){
            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.createdUtc).matches(DataChecker.dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя deletedUtc = null")
        @Description("Тест проверяет, что при создании пользователя deletedUtc = null")
        public void testAddEmployeeWithOnlyRequiredParametersReturnsNullDeletedUtc(){
            assertThat(EMPLOYEE_CREATION_RESPONSE_BODY.deletedUtc).isNull();
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей ответа при изменении сотрудника с обязательными параметрами")
    class CheckEmployeeUpdateWithOnlyRequiredParametersResponseFields{
        private static CommonEmployeeResponseModel EMPLOYEE_CREATION_FOR_UPDATE_RESPONSE_BODY;
        private static UpdateEmployeeRequestModel EMPLOYEE_UPDATE_REQUEST_BODY;
        private static CommonEmployeeResponseModel EMPLOYEE_UPDATE_RESPONSE_BODY;

        @BeforeAll
        public static void setUp(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

            EMPLOYEE_CREATION_FOR_UPDATE_RESPONSE_BODY = DataGeneratorEmployee.createEmployeeModelWithOnlyRequiredParameters().as(CommonEmployeeResponseModel.class);

            EMPLOYEE_UPDATE_REQUEST_BODY = new UpdateEmployeeRequestModel(EMPLOYEE_CREATION_FOR_UPDATE_RESPONSE_BODY);

            EMPLOYEE_UPDATE_RESPONSE_BODY = ApiMethodsEmployee.updateEmployee(EMPLOYEE_UPDATE_REQUEST_BODY).as(CommonEmployeeResponseModel.class);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается id, переданный при создании")
        @Description("Тест проверяет, что при изменении пользователя возвращается id, переданный при создании")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsId(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.id).isEqualTo(EMPLOYEE_UPDATE_REQUEST_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается name, переданный при создании")
        @Description("Тест проверяет, что при изменении пользователя возвращается name, переданный при создании")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsName(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.name).isEqualTo(EMPLOYEE_UPDATE_REQUEST_BODY.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается roleId, переданный при создании")
        @Description("Тест проверяет, что при изменении пользователя возвращается roleId, переданный при создании")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsRoleId(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.roleId).isEqualTo(EMPLOYEE_UPDATE_REQUEST_BODY.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается email, переданный при создании")
        @Description("Тест проверяет, что при изменении пользователя возвращается email, переданный при создании")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsEmail(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.email).isEqualTo(EMPLOYEE_CREATION_FOR_UPDATE_RESPONSE_BODY.email);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается название переданной роли")
        @Description("Тест проверяет, что при изменении пользователя возвращается название переданной роли")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.role).isEqualTo(RoleHelper.getRoleById(EMPLOYEE_UPDATE_REQUEST_BODY.roleId).name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается activationDate = null")
        @Description("Тест проверяет, что при изменении пользователя возвращается activationDate = null")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsNullActivationDate(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.activationDate).isNull();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается createdUtc")
        @Description("Тест проверяет, что при изменении пользователя возвращается createdUtc")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsCreatedUtc(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Изменение пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При изменении пользователя возвращается deletedUtc = null")
        @Description("Тест проверяет, что при изменении пользователя возвращается deletedUtc = null")
        public void testUpdateEmployeeReturnsWithOnlyRequiredParametersReturnsNullDeletedUtc(){
            assertThat(EMPLOYEE_UPDATE_RESPONSE_BODY.deletedUtc).isNull();
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей ответа при получении сотрудника, созданного с обязательными полями, из списка сотрудников (odata)")
    class CheckGetEmployeeWithOnlyRequiredParametersFromListOdataResponseFields{

        private static CommonEmployeeResponseModel EMPLOYEE_FROM_ODATA_LIST;

        @BeforeAll
        public static void setUp(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            EMPLOYEE_FROM_ODATA_LIST = ApiMethodsEmployee.getEmployeeListOData().jsonPath().getList("value", CommonEmployeeResponseModel.class).stream()
                    .filter(element -> element.id.equals(EMPLOYEE_CREATION_RESPONSE_BODY.id))
                    .findFirst().orElse(null);
        }


        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей возвращается корректный id")
        @Description("Тест проверяет, что при получении списка пользователей возвращается корректный id")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsValidId(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.id).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей возвращается name, переданный при создании")
        @Description("Тест проверяет, что при получении списка пользователей возвращается name, переданный при создании")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsName(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.name).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей возвращается roleId, переданный при создании")
        @Description("Тест проверяет, что при получении списка пользователей возвращается roleId, переданный при создании")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsRoleId(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.roleId).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей возвращается email, переданный при создании")
        @Description("Тест проверяет, что при получении списка пользователей возвращается email, переданный при создании")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsEmail(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.email).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.email);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей возвращается название переданной роли")
        @Description("Тест проверяет, что при получении списка пользователей возвращается название переданной роли")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsRole(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.role).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.role);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей activation date = null")
        @Description("Тест проверяет, что при получении списка пользователей activation date = null")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsNullActivationDate(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.activationDate).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.activationDate);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей возвращается createdUtc в корректном формате")
        @Description("Тест проверяет, что при получении списка пользователей возвращается createdUtc в корректном формате")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsCreatedUtc(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.createdUtc).matches(DataChecker.dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении списка пользователей deletedUtc = null")
        @Description("Тест проверяет, что при получении списка пользователей deletedUtc = null")
        public void testGetEmployeeWithOnlyRequiredParametersFromListOdataReturnsNullDeletedUtc(){
            assertThat(EMPLOYEE_FROM_ODATA_LIST.deletedUtc).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.deletedUtc);
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей ответа при получении сотрудника, созданного с обязательными полями, по id (path)")
    class CheckGetEmployeeWithOnlyRequiredParametersByIdPathResponseFields{

        private static CommonEmployeeResponseModel EMPLOYEE_BY_ID_PATH;

        @BeforeAll
        public static void setUp(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            EMPLOYEE_BY_ID_PATH = ApiMethodsEmployee.getEmployeeByIdPath(EMPLOYEE_CREATION_RESPONSE_BODY.id).as(CommonEmployeeResponseModel.class);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param возвращается корректный id")
        @Description("Тест проверяет, что при получении пользователя по id в path param возвращается корректный id")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsValidId(){
            assertThat(EMPLOYEE_BY_ID_PATH.id).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param возвращается name, переданный при создании")
        @Description("Тест проверяет, что при получении пользователя по id в path param возвращается name, переданный при создании")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsName(){
            assertThat(EMPLOYEE_BY_ID_PATH.name).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param возвращается roleId, переданный при создании")
        @Description("Тест проверяет, что при получении пользователя по id в path param возвращается roleId, переданный при создании")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsRoleId(){
            assertThat(EMPLOYEE_BY_ID_PATH.roleId).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param возвращается email, переданный при создании")
        @Description("Тест проверяет, что при получении пользователя по id в path param возвращается email, переданный при создании")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsEmail(){
            assertThat(EMPLOYEE_BY_ID_PATH.email).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.email);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param возвращается название переданной роли")
        @Description("Тест проверяет, что при получении пользователя по id в path param возвращается название переданной роли")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsRole(){
            assertThat(EMPLOYEE_BY_ID_PATH.role).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.role);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param activation date = null")
        @Description("Тест проверяет, что при получении пользователя по id в path param activation date = null")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsNullActivationDate(){
            assertThat(EMPLOYEE_BY_ID_PATH.activationDate).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.activationDate);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param возвращается createdUtc в корректном формате")
        @Description("Тест проверяет, что при получении пользователя по id в path param возвращается createdUtc в корректном формате")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsCreatedUtc(){
            assertThat(EMPLOYEE_BY_ID_PATH.createdUtc).matches(DataChecker.dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение пользователя по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При получении пользователя по id в path param deletedUtc = null")
        @Description("Тест проверяет, что при получении пользователя по id в path param deletedUtc = null")
        public void testGetEmployeeWithOnlyRequiredParametersByIdPathReturnsNullDeletedUtc(){
            assertThat(EMPLOYEE_BY_ID_PATH.deletedUtc).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.deletedUtc);
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей ответа при генерации приглашения")
    class CheckUpdateEmployeeInvitationResponseFields{
        private static final CommonEmployeeResponseModel EMPLOYEE_INVITATION_RESPONSE_BODY = ApiMethodsEmployee.updateInvitation(EMPLOYEE_CREATION_RESPONSE_BODY.id).as(CommonEmployeeResponseModel.class);

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает id сотрудника")
        @Description("Тест проверяет, что при генерации приглашения возвращается id")
        public void testUpdateEmployeeInvitationReturnsId(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.id).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает name")
        @Description("Тест проверяет, что при генерации приглашения возвращается name")
        public void testUpdateEmployeeInvitationReturnsName(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.name).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает roleId")
        @Description("Тест проверяет, что при генерации приглашения возвращается roleId")
        public void testUpdateEmployeeInvitationReturnsRoleId(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.roleId).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает email")
        @Description("Тест проверяет, что при генерации приглашения возвращается email")
        public void testUpdateEmployeeInvitationReturnsEmail(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.email).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.email);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает role")
        @Description("Тест проверяет, что при генерации приглашения возвращается role")
        public void testUpdateEmployeeInvitationReturnsRole(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.role).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.role);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает activationDate = null")
        @Description("Тест проверяет, что при генерации приглашения возвращается activationDate = null")
        public void testUpdateEmployeeInvitationReturnsNullActivationDate(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.activationDate).isNull();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает createdUtc")
        @Description("Тест проверяет, что при генерации приглашения возвращается createdUtc")
        public void testUpdateEmployeeInvitationReturnsCreatedUtc(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.createdUtc).matches(dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Генерация приглашения")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Генерация приглашения возвращает deletedUtc = null")
        @Description("Тест проверяет, что при генерации приглашения возвращается deletedUtc = null")
        public void testUpdateEmployeeInvitationReturnsNullDeletedUtc(){
            assertThat(EMPLOYEE_INVITATION_RESPONSE_BODY.deletedUtc).isNull();
        }

    }

}
