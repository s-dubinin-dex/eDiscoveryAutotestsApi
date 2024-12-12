package eDiscovery.tests.apiTests.extendedPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.data.adminService.DataGeneratorEmployee;
import eDiscovery.helpers.DataChecker;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.admin.PredefinedRoles;
import eDiscovery.helpers.admin.RoleHelper;
import eDiscovery.models.admin.emplyee.AddEmployeeRequestModel;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Admin - Employee")
public class EmployeeExtendedPositiveTests extends TestBase {

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

    @Nested
    @DisplayName("Проверка заполнения полей Admin - Employee в теле ответа при создании")
    class CheckEmployeeCreationResponseFields{

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается корректный id")
        @Description("Тест проверяет, что при создании пользователя возвращается корректный id")
        public void testAddEmployeeReturnsValidId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEmployeeResponseModel responseBody = DataGeneratorEmployee.createEmployeeModelWithOnlyRequiredParameters().as(CommonEmployeeResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается name, переданный при создании")
        @Description("Тест проверяет, что при создании пользователя возвращается name, переданный при создании")
        public void testAddEmployeeReturnsName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel requestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.name).isEqualTo(requestBody.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается roleId, переданный при создании")
        @Description("Тест проверяет, что при создании пользователя возвращается roleId, переданный при создании")
        public void testAddEmployeeReturnsRoleId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel requestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.roleId).isEqualTo(requestBody.roleId);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается email, переданный при создании")
        @Description("Тест проверяет, что при создании пользователя возвращается email, переданный при создании")
        public void testAddEmployeeReturnsEmail(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel requestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.email).isEqualTo(requestBody.email);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается название переданной роли")
        @Description("Тест проверяет, что при создании пользователя возвращается название переданной роли")
        public void testAddEmployeeReturnsRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            PredefinedRoles role = PredefinedRoles.FULL_WRITE;

            AddEmployeeRequestModel requestBody = AddEmployeeRequestModel.builder()
                    .name(getRandomName())
                    .roleId(RoleHelper.getRoleByName(role.name).id)
                    .email(faker.internet().emailAddress())
                    .build();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.role).isEqualTo(role.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя activation date = null")
        @Description("Тест проверяет, что при создании пользователя activation date = null")
        public void testAddEmployeeReturnsNullActivationDate(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel requestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.activationDate).isNull();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя возвращается дата создания в корректном формате")
        @Description("Тест проверяет, что при создании пользователя возвращается дата создания в корректном формате")
        public void testAddEmployeeReturnsCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel requestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.createdUtc).matches(DataChecker.dateTimeUTCPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Пользователь")
        @Story("Создание пользователя")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("При создании пользователя deletedUtc = null")
        @Description("Тест проверяет, что при создании пользователя deletedUtc = null")
        public void testAddEmployeeReturnsNullDeletedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddEmployeeRequestModel requestBody = DataGeneratorEmployee.getAddEmployeeModelWithOnlyRequiredParameters();

            CommonEmployeeResponseModel responseBody = ApiMethodsEmployee.addEmployee(requestBody).as(CommonEmployeeResponseModel.class);

            assertThat(responseBody.deletedUtc).isNull();
        }

    }

}
