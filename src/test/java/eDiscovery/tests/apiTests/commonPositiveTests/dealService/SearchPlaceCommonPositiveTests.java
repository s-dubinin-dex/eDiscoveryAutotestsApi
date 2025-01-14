package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import eDiscovery.helpers.enums.SearchPlaceType;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - SearchPlace: Основные позитивные тесты")
public class SearchPlaceCommonPositiveTests extends TestBase {

    @Nested
    @Tag("smoke")
    @DisplayName("Deal - SearchPlace: Базовая проверка CRUD")
    class CheckBaseCRUDDealSearchPlace {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Создание места поиска")
        @Description("Тест проверяет возможность создания места поиска")
        public void testAddSearchPlace() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Изменение места поиска")
        @Description("Тест проверяет возможность изменения места поиска")
        public void testUpdateSearchPlace() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            UpdateSearchPlaceRequestModel requestBodySearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);

            ApiMethodsSearchPlace.updateSearchPlace(requestBodySearchPlaceUpdate);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Удаление места поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Удаление места поиска")
        @Description("Тест проверяет возможность удаления места поиска")
        public void testDeleteSearchPlace() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchPlace.deleteSearchPlace(responseBodySearchPlaceCreation.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка мест поиска")
        @Description("Тест проверяет возможность получения списка мест поиска")
        public void testGetSearchPlaceList() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsSearchPlace.getSearchPlaceList();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка мест поиска по протоколу oData")
        @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData")
        public void testGetSearchPlaceListOData() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsSearchPlace.getSearchPlaceListOData();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение места поиска по id")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение места поиска по протоколу oData по id")
        @Description("Тест проверяет возможность получения места поиска по протоколу oData по id в скобках")
        public void testGetSearchPlaceODataById() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            ApiMethodsSearchPlace.getSearchPlaceODataById(responseBody.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение места поиска по id")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение места поиска по протоколу oData по id")
        @Description("Тест проверяет возможность получения места поиска по протоколу oData по id в path param")
        public void testGetSearchPlaceODataByIdInPath() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            ApiMethodsSearchPlace.getSearchPlaceODataByIdPath(responseBody.id);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при создании поискового запроса с обязательными параметрами")
    class CheckSearchPlaceCreationWithOnlyRequiredParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании места поиска с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании места поиска с обязательными полями")
        public void testAddSearchPlaceWithOnlyRequiredParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.categoryType).isEqualTo(requestBody.categoryType),
                    () -> assertThat(responseBody.type).isEqualTo(requestBody.type),
                    () -> assertThat(responseBody.parameters).isNull(),
                    () -> assertThat(responseBody.excludes).isEmpty(),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при изменении поискового запроса с обязательными параметрами")
    class CheckSearchPlaceUpdateWithOnlyRequiredParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении места поиска с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при изменении места поиска с обязательными полями")
        public void testUpdateSearchPlaceWithOnlyRequiredParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            UpdateSearchPlaceRequestModel requestBody = new UpdateSearchPlaceRequestModel(responseSearchPlaceCreation);
            requestBody.name = getRandomName();
            requestBody.type = SearchPlaceType.FTP.name();

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.updateSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(requestBody.id),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.categoryType).isEqualTo(requestBody.categoryType),
                    () -> assertThat(responseBody.type).isEqualTo(requestBody.type),
                    () -> assertThat(responseBody.parameters).isEqualTo(requestBody.parameters),
                    () -> assertThat(responseBody.excludes).isEmpty(),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при получении места поиска с обязательными параметрами из списка мест поиска")
    class CheckGetSearchPlaceWithOnlyRequiredParametersFromListOdataResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка поисковых запросов")
        @Description("Тест проверяет поля в теле ответа при получении списка поисковых запросов")
        public void testGetSearchPlaceWithOnlyRequiredParametersFromListOdataCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceCreationResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel searchPlaceBodyFromODataListResponseBody = ApiMethodsSearchPlace.getSearchPlaceListOData().jsonPath().getList("value", CommonSearchPlaceResponseModel.class)
                    .stream().filter(searchPlace -> searchPlace.id.equals(searchPlaceCreationResponseBody.id))
                    .findFirst().orElse(null);

            assertThat(searchPlaceBodyFromODataListResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.id).isEqualTo(searchPlaceCreationResponseBody.id),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.name).isEqualTo(searchPlaceCreationResponseBody.name),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.categoryType).isEqualTo(searchPlaceCreationResponseBody.categoryType),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.type).isEqualTo(searchPlaceCreationResponseBody.type),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.parameters).isEqualTo(searchPlaceCreationResponseBody.parameters),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.excludes).isEqualTo(searchPlaceCreationResponseBody.excludes),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.deletedUtc).isEqualTo(searchPlaceCreationResponseBody.deletedUtc)
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при получении места поиска с обязательными параметрами по id")
    class CheckGetSearchPlaceWithOnlyRequiredParametersByIdResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении поискового запроса по id")
        @Description("Тест проверяет поля в теле ответа при получении поискового запроса по id")
        public void testGetSearchQPlaceWithOnlyRequiredParametersByIdCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceCreationResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel searchPlaceBodyByIdResponseBody = ApiMethodsSearchPlace.getSearchPlaceODataByIdPath(searchPlaceCreationResponseBody.id).as(CommonSearchPlaceResponseModel.class);

            assertAll(
                    () -> assertThat(searchPlaceBodyByIdResponseBody.id).isEqualTo(searchPlaceCreationResponseBody.id),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.name).isEqualTo(searchPlaceCreationResponseBody.name),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.categoryType).isEqualTo(searchPlaceCreationResponseBody.categoryType),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.type).isEqualTo(searchPlaceCreationResponseBody.type),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.parameters).isEqualTo(searchPlaceCreationResponseBody.parameters),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.excludes).isEqualTo(searchPlaceCreationResponseBody.excludes),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(searchPlaceBodyByIdResponseBody.deletedUtc).isEqualTo(searchPlaceCreationResponseBody.deletedUtc)
            );

        }

    }

}
