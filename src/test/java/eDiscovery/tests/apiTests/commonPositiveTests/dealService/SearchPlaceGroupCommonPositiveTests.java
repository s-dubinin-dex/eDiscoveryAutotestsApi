package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlaceGroup;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchPlaceGroup;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlaceGroup.AddSearchPlaceGroupRequestModel;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;
import eDiscovery.models.deal.searchPlaceGroup.UpdateSearchPlaceGroupRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.isValidUUID;
import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - SearchPlaceGroup: Основные позитивные тесты")
public class SearchPlaceGroupCommonPositiveTests extends TestBase {

    @Nested
    @Tag("smoke")
    @DisplayName("Deal - SearchPlaceGroup: Базовая проверка CRUD")
    class CheckBaseCRUDDealSearchPlaceGroup {

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Создание группы мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Создание группы мест поиска")
        @Description("Тест проверяет возможность создания группы мест поиска")
        public void testAddSearchPlaceGroup() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Изменение группы мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Изменение группы мест поиска")
        @Description("Тест проверяет возможность изменения группы мест поиска")
        public void testUpdateSearchPlaceGroup(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseBodyCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            UpdateSearchPlaceGroupRequestModel requestBody = new UpdateSearchPlaceGroupRequestModel(responseBodyCreation);
            ApiMethodsSearchPlaceGroup.updateSearchPlaceGroup(requestBody);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Удаление группы мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Удаление группы мест поиска")
        @Description("Тест проверяет возможность удаления группы мест поиска")
        public void testDeleteSearchPlaceGroup(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseBodyCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchPlaceGroup.deleteSearchPlaceGroup(responseBodyCreation.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка групп мест поиска")
        @Description("Тест проверяет возможность получения списка групп мест поиска")
        public void testGetSearchPlaceGroupList(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupList();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка групп мест поиска по протоколу odata")
        @Description("Тест проверяет возможность получения списка групп мест поиска по протоколу odata")
        public void testGetSearchPlaceGroupListOData(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение группы мест поиска по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("УПолучение группы мест поиска по id")
        @Description("Получение группы мест поиска по id")
        public void testGetSearchPlaceGroupById(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseBodyCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupById(responseBodyCreation.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение группы мест поиска по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("УПолучение группы мест поиска по id")
        @Description("Получение группы мест поиска по id в path param")
        public void testGetSearchPlaceGroupByIdPath(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseBodyCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupByIdPath(responseBodyCreation.id);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка тела ответа при создании группы мест поиска с обязательными параметрами")
    class CheckSearchPlaceGroupCreationWithOnlyRequiredParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Создание группы мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании группы мест поиска с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании группы мест поиска с обязательными полями")
        public void testAddSearchPlaceGroupWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            AddSearchPlaceGroupRequestModel requestBody = AddSearchPlaceGroupRequestModel.builder()
                    .name(getRandomName())
                    .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                    .description(getRandomName())
                    .build();

            CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBody).as(CommonSearchPlaceGroupResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.searchPlaces).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(Collections.singletonList(searchPlaceBody)),
                    () -> assertThat(responseBody.description).isEqualTo(requestBody.description),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка тела ответа при изменении группы мест поиска с обязательными параметрами")
    class CheckSearchPlaceGroupUpdateWithOnlyRequiredParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Изменение группы мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении группы мест поиска с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при изменении группы мест поиска с обязательными полями")
        public void testUpdateSearchPlaceGroupWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();
            UpdateSearchPlaceGroupRequestModel requestBody = new UpdateSearchPlaceGroupRequestModel(responseSearchPlaceGroupCreation);
            requestBody.name = getRandomName();
            requestBody.searchPlaces = Collections.singletonList(searchPlaceBody.id);
            requestBody.description = getRandomName();

            CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.updateSearchPlaceGroup(requestBody).as(CommonSearchPlaceGroupResponseModel.class);

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(requestBody.id),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.searchPlaces).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(Collections.singletonList(searchPlaceBody)),
                    () -> assertThat(responseBody.description).isEqualTo(requestBody.description),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка тела ответа при получении группы мест поиска с обязательными параметрами из списка мест поиска")
    class CheckGetSearchPlaceGroupWithOnlyRequiredParametersFromListOdataResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка групп мест поиска")
        @Description("Тест проверяет поля в теле ответа при получении списка групп мест поиска")
        public void testGetSearchPlaceGroupWithOnlyRequiredParametersFromListOdataCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            CommonSearchPlaceGroupResponseModel searchPlaceGroupBodyFromODataListResponseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData().jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class)
                    .stream().filter(searchPlaceGroup -> searchPlaceGroup.id.equals(responseSearchPlaceGroupCreation.id))
                    .findFirst().orElse(null);

            assertThat(searchPlaceGroupBodyFromODataListResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(searchPlaceGroupBodyFromODataListResponseBody.id).isEqualTo(responseSearchPlaceGroupCreation.id),
                    () -> assertThat(searchPlaceGroupBodyFromODataListResponseBody.name).isEqualTo(responseSearchPlaceGroupCreation.name),
                    () -> assertThat(searchPlaceGroupBodyFromODataListResponseBody.description).isEqualTo(responseSearchPlaceGroupCreation.description),
                    () -> assertThat(searchPlaceGroupBodyFromODataListResponseBody.deletedUtc).isEqualTo(responseSearchPlaceGroupCreation.deletedUtc)
            );
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка групп мест поиска")
        @Description("Тест проверяет поля в теле ответа при получении списка групп мест поиска")
        public void testGetSearchPlaceGroupWithOnlyRequiredParametersFromListOdataCheckResponseBodyWithExpand() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            AddSearchPlaceGroupRequestModel requestBody = AddSearchPlaceGroupRequestModel.builder()
                    .name(getRandomName())
                    .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                    .description(getRandomName())
                    .build();

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBody).as(CommonSearchPlaceGroupResponseModel.class);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("searchPlaces")
                    .build();

            CommonSearchPlaceGroupResponseModel searchPlaceGroupBodyFromODataListResponseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class)
                    .stream().filter(searchPlaceGroup -> searchPlaceGroup.id.equals(responseSearchPlaceGroupCreation.id))
                    .findFirst().orElse(null);

            assertThat(searchPlaceGroupBodyFromODataListResponseBody).isNotNull();

            assertThat(searchPlaceGroupBodyFromODataListResponseBody.searchPlaces).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(Collections.singletonList(searchPlaceBody));
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка тела ответа при получении группы мест поиска с обязательными параметрами по id")
    class CheckGetSearchPlaceGroupWithOnlyRequiredParametersByIdResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение группы мест поиска по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении группы мест поиска по id")
        @Description("Тест проверяет поля в теле ответа при получении группы мест поиска по id")
        public void testGetSearchPlaceGroupWithOnlyRequiredParametersByIdCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            CommonSearchPlaceGroupResponseModel searchPlaceGroupBodyByIdResponseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupByIdPath(responseSearchPlaceGroupCreation.id).as(CommonSearchPlaceGroupResponseModel.class);

            assertAll(
                    () -> assertThat(searchPlaceGroupBodyByIdResponseBody.id).isEqualTo(responseSearchPlaceGroupCreation.id),
                    () -> assertThat(searchPlaceGroupBodyByIdResponseBody.name).isEqualTo(responseSearchPlaceGroupCreation.name),
                    () -> assertThat(searchPlaceGroupBodyByIdResponseBody.description).isEqualTo(responseSearchPlaceGroupCreation.description),
                    () -> assertThat(searchPlaceGroupBodyByIdResponseBody.deletedUtc).isEqualTo(responseSearchPlaceGroupCreation.deletedUtc)
            );
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение группы мест поиска по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении группы мест поиска по id")
        @Description("Тест проверяет поля в теле ответа при получении группы мест поиска по id")
        public void testGetSearchPlaceGroupWithOnlyRequiredParametersByIdCheckResponseBodyWithExpand() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            AddSearchPlaceGroupRequestModel requestBody = AddSearchPlaceGroupRequestModel.builder()
                    .name(getRandomName())
                    .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                    .description(getRandomName())
                    .build();

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBody).as(CommonSearchPlaceGroupResponseModel.class);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("searchPlaces")
                    .build();

            CommonSearchPlaceGroupResponseModel searchPlaceGroupBodyByIdResponseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupByIdPath(responseSearchPlaceGroupCreation.id, params).as(CommonSearchPlaceGroupResponseModel.class);

            assertThat(searchPlaceGroupBodyByIdResponseBody.searchPlaces).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(Collections.singletonList(searchPlaceBody));
        }

    }

}
