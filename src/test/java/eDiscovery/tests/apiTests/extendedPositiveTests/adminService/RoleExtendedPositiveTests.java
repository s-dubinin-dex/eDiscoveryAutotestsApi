package eDiscovery.tests.apiTests.extendedPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsRole;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.data.adminService.DataGeneratorRole;
import eDiscovery.helpers.DataChecker;
import eDiscovery.models.admin.role.AddRoleRequestModel;
import eDiscovery.models.admin.role.CommonRoleResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Admin - Role")
public class RoleExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Проверка заполнения полей Admin - Role в теле ответа при создании")
    class CheckRoleCreationResponseFields{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При создании роли возвращается корректный id")
        @Description("Тест проверяет, что при создании роли возвращается корректный id")
        public void testAddRoleReturnsValidId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBody = DataGeneratorRole.createRoleWithOnlyRequiredParameters().as(CommonRoleResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При создании роли возвращается name, переданный при создании")
        @Description("Тест проверяет, что при создании роли возвращается name, переданный при создании")
        public void testAddRoleReturnsName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = DataGeneratorRole.getRoleModelWithOnlyRequiredParameters();

            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertThat(responseBody.name).isEqualTo(requestBody.name);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При создании роли возвращается description, переданный при создании")
        @Description("Тест проверяет, что при создании роли возвращается description, переданный при создании")
        public void testAddRoleReturnsDescription(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = DataGeneratorRole.getRoleModelWithOnlyRequiredParameters();
            requestBody.description = DataGeneratorCommon.getRandomName();

            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertThat(responseBody.description).isEqualTo(requestBody.description);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При создании роли возвращаются policies, переданные при создании")
        @Description("Тест проверяет, что при создании роли возвращаются policies, переданные при создании")
        public void testAddRoleReturnsPolicies(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = DataGeneratorRole.getRoleModelWithOnlyRequiredParameters();

            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertThat(responseBody.policies).isEqualTo(requestBody.policies);
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При создании роли возвращается дата создания в корректном формате")
        @Description("Тест проверяет, что при создании роли возвращается дата создания в корректном формате")
        public void testAddRoleReturnCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = DataGeneratorRole.getRoleModelWithOnlyRequiredParameters();

            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertThat(responseBody.createdUtc).matches(DataChecker.dateTimeUTCPattern());
        }

        @Test
        @Epic("Сервис Admin")
        @Feature("Роль")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("При создании роли возвращается deletedUtc = null")
        @Description("Тест проверяет, что при создании роли возвращается deletedUtc = null")
        public void testAddRoleReturnDeletedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = DataGeneratorRole.getRoleModelWithOnlyRequiredParameters();

            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertThat(responseBody.deletedUtc).isNull();
        }

    }

}
