package eDiscovery.tests.apiTests.extendedPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsRole;
import eDiscovery.data.adminService.DataGeneratorRole;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.admin.role.AddRoleRequestModel;
import eDiscovery.models.admin.role.CommonRoleResponseModel;
import eDiscovery.models.admin.role.UpdateRoleRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.dateTimeCommonPattern;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Admin - Role: Расширенные позитивные тесты")
public class RoleExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при создании роли с необязательными параметрами")
    class CheckRoleCreationWithAllParametersCheckResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Создание роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании роли с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании роли с обязательными полями")
        public void testAddRoleWithAllParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel requestBody = AddRoleRequestModel.builder()
                    .name(getRandomName())
                    .policies(Collections.singletonList("ed.deal.read"))
                    .description(getRandomName())
                    .build();

            CommonRoleResponseModel responseBody = ApiMethodsRole.addRole(requestBody).as(CommonRoleResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.description).isEqualTo(requestBody.description),
                    () -> assertThat(responseBody.policies).isEqualTo(requestBody.policies),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при изменении роли с необязательными параметрами")
    class CheckRoleUpdateWithAllParametersCheckResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Изменение роли")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении роли с необязательными полями")
        @Description("Тест проверяет поля в теле ответа при изменении роли с необязательными полями")
        public void testUpdateRoleWithAllParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel responseBodyRoleCreation = DataGeneratorRole.createRoleWithOnlyRequiredParameters();
            UpdateRoleRequestModel requestBody = new UpdateRoleRequestModel(responseBodyRoleCreation);

            requestBody.name = getRandomName();
            requestBody.policies = Collections.singletonList("ed.deal.write");
            requestBody.description = getRandomName();

            CommonRoleResponseModel responseBody = ApiMethodsRole.updateRole(requestBody).as(CommonRoleResponseModel.class);

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(requestBody.id),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.description).isEqualTo(requestBody.description),
                    () -> assertThat(responseBody.policies).isEqualTo(requestBody.policies),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при получении роли с необязательными параметрами из списка ролей")
    class CheckGetRoleWithAllParametersFromListOdataResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Получение списка ролей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка ролей")
        @Description("Тест проверяет поля в теле ответа при получении списка ролей")
        public void testGetRoleWithAllParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel roleCreationRequestBody = AddRoleRequestModel.builder()
                    .name(getRandomName())
                    .policies(Collections.singletonList("ed.deal.read"))
                    .description(getRandomName())
                    .build();

            CommonRoleResponseModel roleCreationResponseBody = ApiMethodsRole.addRole(roleCreationRequestBody).as(CommonRoleResponseModel.class);

            CommonRoleResponseModel roleFromOdataList = ApiMethodsRole.getRolesListOData().jsonPath().getList("value", CommonRoleResponseModel.class).stream()
                    .filter(element -> element.id.equals(roleCreationResponseBody.id))
                    .findFirst().orElse(null);

            assertThat(roleFromOdataList).isNotNull();

            assertAll(
                    () -> assertThat(roleFromOdataList.id).isEqualTo(roleCreationResponseBody.id),
                    () -> assertThat(roleFromOdataList.name).isEqualTo(roleCreationResponseBody.name),
                    () -> assertThat(roleFromOdataList.description).isEqualTo(roleCreationResponseBody.description),
                    () -> assertThat(roleFromOdataList.policies).isEqualTo(roleCreationResponseBody.policies),
                    () -> assertThat(roleFromOdataList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(roleFromOdataList.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка тела ответа при получении роли с необязательными параметрами по id")
    class CheckGetRoleWithAllParametersByIdOdataResponseBody{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Получение роли по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении роли по id")
        @Description("Тест проверяет поля в теле ответа при получении роли по id")
        public void testGetRoleWithAllParametersByIdCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRoleRequestModel roleCreationRequestBody = AddRoleRequestModel.builder()
                    .name(getRandomName())
                    .policies(Collections.singletonList("ed.deal.read"))
                    .description(getRandomName())
                    .build();

            CommonRoleResponseModel roleCreationResponseBody = ApiMethodsRole.addRole(roleCreationRequestBody).as(CommonRoleResponseModel.class);

            CommonRoleResponseModel roleFromOdataList = ApiMethodsRole.getRoleODataByIdPath(roleCreationResponseBody.id).as(CommonRoleResponseModel.class);

            assertThat(roleFromOdataList).isNotNull();

            assertAll(
                    () -> assertThat(roleFromOdataList.id).isEqualTo(roleCreationResponseBody.id),
                    () -> assertThat(roleFromOdataList.name).isEqualTo(roleCreationResponseBody.name),
                    () -> assertThat(roleFromOdataList.description).isEqualTo(roleCreationResponseBody.description),
                    () -> assertThat(roleFromOdataList.policies).isEqualTo(roleCreationResponseBody.policies),
                    () -> assertThat(roleFromOdataList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(roleFromOdataList.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка odata параметров при получении списка ролей")
    class CheckGetRoleListOdataParameters{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Получение списка ролей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка ролей включая удалённые роли")
        @Description("Тест проверяет возможность получения списка ролей включая удалённые роли")
        public void testGetRoleListWithIncludeDeleted(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel roleForDeletion = DataGeneratorRole.createRoleWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsRole.deleteRole(roleForDeletion.id);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            List<CommonRoleResponseModel> roleListWithoutDeleted = ApiMethodsRole.getRolesListOData().jsonPath().getList("value", CommonRoleResponseModel.class);
            List<CommonRoleResponseModel> roleListWithDeleted = ApiMethodsRole.getRolesListOData(parameters).jsonPath().getList("value", CommonRoleResponseModel.class);

            assertThat(roleListWithoutDeleted.stream().filter(role -> role.id.equals(roleForDeletion.id))).hasSize(0);
            assertThat(roleListWithDeleted.stream().filter(role -> role.id.equals(roleForDeletion.id))).hasSize(1);
        }
    }

    @Nested
    @DisplayName("Admin - Role: Проверка полей удалённой роли в списке")
    class CheckDeletedERoleInList{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Получение списка ролей")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей в теле ответа при получении списка ролей по удалённой роли")
        @Description("Тест проверяет, что при получении списка ролей для удалённого роли возвращается корректный id")
        public void testGetDeletedRoleWithOnlyRequiredParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRoleResponseModel roleForDeletion = DataGeneratorRole.createRoleWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsRole.deleteRole(roleForDeletion.id);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
            CommonRoleResponseModel deletedRoleResponseBody = ApiMethodsRole.getRolesListOData(params).jsonPath().getList("value", CommonRoleResponseModel.class).stream()
                    .filter(element -> element.id.equals(roleForDeletion.id))
                    .findFirst().orElse(null);

            assertThat(deletedRoleResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(deletedRoleResponseBody.id).isEqualTo(roleForDeletion.id),
                    () -> assertThat(deletedRoleResponseBody.name).isEqualTo(String.format("%s.deleted#%s", roleForDeletion.id, roleForDeletion.name)),
                    () -> assertThat(deletedRoleResponseBody.policies).isEqualTo(roleForDeletion.policies),
                    () -> assertThat(deletedRoleResponseBody.description).isNull(),
                    () -> assertThat(deletedRoleResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(deletedRoleResponseBody.deletedUtc).matches(dateTimeCommonPattern())
            );

        }

    }

    @Nested
    @DisplayName("Admin - Role: Проверка работы методов, используемых на UI")
    class CheckRoleUIMethods{

        @Test
        @Epic("Сервис Admin")
        @Feature("Роли")
        @Story("Получение списка ролей")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка ролей для выпадающего списка ролей")
        @Description("Тест проверяет возможность получения списка ролей для выпадающего списка ролей")
        public void testGetRoleListWEBUIForFilterRole(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            ApiMethodsRole.getRolesListOData(parameters);
        }

    }

}
