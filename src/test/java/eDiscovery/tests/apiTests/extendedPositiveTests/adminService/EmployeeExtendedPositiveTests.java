package eDiscovery.tests.apiTests.extendedPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.data.adminService.DataGeneratorEmployee;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Admin - Employee: Расширенные позитивные тесты")
public class EmployeeExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Admin - Employee: Проверка отображения удалённых сотрудников в списке")
    class CheckDeletedEmployeeInList{
        private static CommonEmployeeResponseModel EMPLOYEE_CREATION_RESPONSE_BODY;
        private static CommonEmployeeResponseModel DELETED_EMPLOYEE_RESPONSE_BODY;

        @BeforeAll
        public static void setUp(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

            EMPLOYEE_CREATION_RESPONSE_BODY = DataGeneratorEmployee.createEmployeeModelWithOnlyRequiredParameters().as(CommonEmployeeResponseModel.class);

            ApiMethodsEmployee.deleteEmployee(EMPLOYEE_CREATION_RESPONSE_BODY.id);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            DELETED_EMPLOYEE_RESPONSE_BODY = ApiMethodsEmployee.getEmployeeListOData(params).jsonPath().getList("value", CommonEmployeeResponseModel.class).stream()
                    .filter(element -> element.id.equals(EMPLOYEE_CREATION_RESPONSE_BODY.id))
                    .findFirst().orElse(null);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Удалённый пользователь отображается в списке при includeDeleted = true")
        @Description("Тест проверяет, что удалённый пользователь отображается в списке при includeDeleted = true")
        public void testDeletedEmployeePresentsInList(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY).isNotNull();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается корректный id")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается корректный id")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsValidId(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.id).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.id);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается name")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается name")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsName(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.name).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается roleId")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается roleId")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsRoleId(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.roleId).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается email")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается email")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsEmail(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.email).isEqualTo(String.format("%s.deleted#%s", EMPLOYEE_CREATION_RESPONSE_BODY.id, EMPLOYEE_CREATION_RESPONSE_BODY.email));
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается role")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается role")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsRole(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.role).isEqualTo(EMPLOYEE_CREATION_RESPONSE_BODY.role);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается activationDate = null")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается activationDate = null")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsNullActivationDate(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.activationDate).isNull();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается createdUtc")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается createdUtc")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsCreatedUtc(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.createdUtc).matches(dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При получении списка пользователей для удалённого пользователя возвращается deletedUtc")
        @Description("Тест проверяет, что при получении списка пользователей для удалённого пользователя возвращается deletedUtc")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataReturnsDeletedUtc(){
            assertThat(DELETED_EMPLOYEE_RESPONSE_BODY.deletedUtc).matches(dateTimeCommonPattern());
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка работы методов, используемых на UI")
    class CheckEmployeeUIMethods{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка сотрудников для выпадающего списка сотрудников")
        @Description("Тест проверяет возможность получения списка сотрудников для выпадающего списка сотрудников")
        public void testGetEmployeeListWEBUIForFilterEmployee(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonEmployeeResponseModel> responseBody = ApiMethodsEmployee.getEmployeeListOData(parameters).jsonPath().getList("value", CommonEmployeeResponseModel.class);
        }

    }

}
