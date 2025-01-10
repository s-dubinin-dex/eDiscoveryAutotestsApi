package eDiscovery.tests.apiTests.extendedPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.data.adminService.DataGeneratorEmployee;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.admin.PredefinedRoles;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;
import eDiscovery.models.odata.OdataListResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Admin - Employee: Расширенные позитивные тесты")
public class EmployeeExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Admin - Employee: Проверка odata параметров при получении списка пользователей")
    class CheckGetEmployeeListOdataParameters{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с фильтрацией по полю name")
        @Description("Тест проверяет возможность получения списка пользователей с фильтрацией по полю name")
        public void testGetEmployeeListWithFilterName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с фильтрацией по роли пользователя")
        @Description("Тест проверяет возможность получения списка пользователей с фильтрацией по роли пользователя")
        public void testGetEmployeeListWithFilterRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("role eq '%s'", PredefinedRoles.FULL_WRITE.name))
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с сортировкой по дате создания")
        @Description("Тест проверяет возможность получения списка пользователей с сортировкой по дате создания")
        public void testGetEmployeeListWithSortingCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("createdUtc desc")
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с сортировкой по ФИО")
        @Description("Тест проверяет возможность получения списка пользователей с сортировкой по ФИО")
        public void testGetEmployeeListWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("name asc")
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с сортировкой по роли пользователя")
        @Description("Тест проверяет возможность получения списка пользователей с сортировкой по роли пользователя")
        public void testGetEmployeeListWithSortingRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("role asc")
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с сортировкой по email")
        @Description("Тест проверяет возможность получения списка пользователей с сортировкой по роли email")
        public void testGetEmployeeListWithSortingEmail(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("email asc")
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с подсчётом количества результатов")
        @Description("Тест проверяет возможность получения списка пользователей с подсчётом количества результатов")
        public void testGetEmployeeListWithCount(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withCount(true)
                    .build();

            Response response = ApiMethodsEmployee.getEmployeeListOData(parameters);

            OdataListResponseModel responseBodyWithCount = response.as(OdataListResponseModel.class);
            List<CommonEmployeeResponseModel> responseEmployees = response.jsonPath().getList("value", CommonEmployeeResponseModel.class);

            assertThat(Integer.getInteger(responseBodyWithCount.odataCount)).isEqualTo(responseEmployees.size());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с ограничением количества результатов = 100")
        @Description("Тест проверяет возможность получения списка пользователей с ограничением количества результатов = 100")
        public void testGetEmployeeListWithTop100(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withTop(100)
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей с пропуском первых 10 результатов")
        @Description("Тест проверяет возможность получения списка пользователей с пропуском первых 10 результатов")
        public void testGetEmployeeListWithSkip10(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withSkip(10)
                    .build();

            ApiMethodsEmployee.getEmployeeListOData(parameters);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей включая удалённых пользователей")
        @Description("Тест проверяет возможность получения списка пользователей включая удалённых пользователей")
        public void testGetEmployeeListWithIncludeDeleted(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel employeeForDeletion = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsEmployee.deleteEmployee(employeeForDeletion.id);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());


            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            List<CommonEmployeeResponseModel> employeeListWithoutDeleted = ApiMethodsEmployee.getEmployeeListOData().jsonPath().getList("value", CommonEmployeeResponseModel.class);
            List<CommonEmployeeResponseModel> employeeListWithDeleted = ApiMethodsEmployee.getEmployeeListOData(parameters).jsonPath().getList("value", CommonEmployeeResponseModel.class);

            assertThat(employeeListWithoutDeleted.stream().filter(employee -> employee.id.equals(employeeForDeletion.id))).hasSize(0);
            assertThat(employeeListWithDeleted.stream().filter(employee -> employee.id.equals(employeeForDeletion.id))).hasSize(1);
        }

    }

    @Nested
    @DisplayName("Admin - Employee: Проверка полей удалённого пользователя в списке")
    class CheckDeletedEmployeeInList{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Получение списка пользователей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей в теле ответа при получении списка пользователей по удалённому пользователю")
        @Description("Тест проверяет поля в теле ответа при получении списка пользователей по удалённому пользователю")
        public void testGetDeletedEmployeeWithOnlyRequiredParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel employeeForDeletion = DataGeneratorEmployee.createEmployeeWithOnlyRequiredParameters();
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsEmployee.deleteEmployee(employeeForDeletion.id);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
            CommonEmployeeResponseModel deletedEmployeeResponseBody = ApiMethodsEmployee.getEmployeeListOData(params).jsonPath().getList("value", CommonEmployeeResponseModel.class).stream()
                    .filter(element -> element.id.equals(employeeForDeletion.id))
                    .findFirst().orElse(null);

            assertThat(deletedEmployeeResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(deletedEmployeeResponseBody.id).isEqualTo(employeeForDeletion.id),
                    () -> assertThat(deletedEmployeeResponseBody.name).isEqualTo(employeeForDeletion.name),
                    () -> assertThat(deletedEmployeeResponseBody.roleId).isEqualTo(employeeForDeletion.roleId),
                    () -> assertThat(deletedEmployeeResponseBody.email).isEqualTo(String.format("%s.deleted#%s", employeeForDeletion.id, employeeForDeletion.email)),
                    () -> assertThat(deletedEmployeeResponseBody.role).isEqualTo(employeeForDeletion.role),
                    () -> assertThat(deletedEmployeeResponseBody.activationDate).isNull(),
                    () -> assertThat(deletedEmployeeResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(deletedEmployeeResponseBody.deletedUtc).matches(dateTimeCommonPattern())
            );

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
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка пользователей для выпадающего списка пользователей")
        @Description("Тест проверяет возможность получения списка пользователей для выпадающего списка пользователей")
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
