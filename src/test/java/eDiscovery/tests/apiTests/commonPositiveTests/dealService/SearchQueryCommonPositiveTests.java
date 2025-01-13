package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - SearchQuery: Основные позитивные тесты")
public class SearchQueryCommonPositiveTests extends TestBase {

    @Nested
    @Tag("smoke")
    @DisplayName("Deal - SearchQuery: Базовая проверка CRUD")
    class CheckBaseCRUDDealSearchQuery{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Создание поискового запроса")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Создание поискового запроса")
        @Description("Тест проверяет возможность создания поискового запроса")
        public void testAddSearchQuery(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Изменение поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение поискового запроса")
        @Description("Тест проверяет возможность изменения поискового запроса")
        public void testUpdateSearchQuery(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            UpdateSearchQueryRequestModel requestModelSearchQueryUpdate = new UpdateSearchQueryRequestModel(responseBodySearchQueryCreation);

            ApiMethodsSearchQuery.updateSearchQuery(requestModelSearchQueryUpdate);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Удаление поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Удаление поискового запроса")
        @Description("Тест проверяет возможность удаления поискового запроса")
        public void testDeleteSearchQuery(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchQuery.deleteSearchQuery(responseBodySearchQueryCreation.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов")
        @Description("Тест проверяет возможность получения списка поисковых запросов")
        public void testGetSearchQueryList(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsSearchQuery.getSearchQueryList();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов по протоколу oData")
        @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData")
        public void testGetSearchQueryListOData(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsSearchQuery.getSearchQueryListOData();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Получение поискового запроса по протоколу oData по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение поискового запроса по протоколу oData по id")
        @Description("Тест проверяет возможность получения поискового запроса по протоколу oData по id в скобках")
        public void testGetSearchQueryODataById(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseBody = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            ApiMethodsSearchQuery.getSearchQueryODataById(responseBody.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Tag("webui")
        @Story("Получение поискового запроса по протоколу oData по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение поискового запроса по протоколу oData по id")
        @Description("Тест проверяет возможность получения поискового запроса по протоколу oData по id в path param")
        public void testGetSearchQueryODataByIdInPath(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseBody = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            ApiMethodsSearchQuery.getSearchQueryODataByIdPath(responseBody.id);
        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка тела ответа при создании поискового запроса с обязательными параметрами")
    class CheckSearchQueryCreationWithOnlyRequiredParametersResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Создание поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании поискового запроса с обязательными полями")
        @Description("Тест проверяет поля в теле ответа прии создании поискового запроса с обязательными полями")
        public void testAddSearchQueryWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.type).isEqualTo(requestBody.type),
                    () -> assertThat(responseBody.value).isEqualTo(requestBody.value),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка тела ответа при изменении поискового запроса с обязательными параметрами")
    class CheckSearchQueryUpdateWithOnlyRequiredParametersResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Изменение поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении поискового запроса с обязательными полями")
        @Description("Тест проверяет поля в теле ответа прии изменении поискового запроса с обязательными полями")
        public void testUpdateSearchQueryWithOnlyRequiredParametersCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            UpdateSearchQueryRequestModel requestBodySearchQueryUpdate = new UpdateSearchQueryRequestModel(responseBodySearchQueryCreation);
            requestBodySearchQueryUpdate.name = getRandomName();
            requestBodySearchQueryUpdate.type = SearchQueryType.Text.name();
            requestBodySearchQueryUpdate.value = "abc";

            CommonSearchQueryResponseModel responseBodySearchQueryUpdate  = ApiMethodsSearchQuery.updateSearchQuery(requestBodySearchQueryUpdate).as(CommonSearchQueryResponseModel.class);

            assertAll(
                    () -> assertThat(responseBodySearchQueryUpdate.id).isEqualTo(requestBodySearchQueryUpdate.id),
                    () -> assertThat(responseBodySearchQueryUpdate.name).isEqualTo(requestBodySearchQueryUpdate.name),
                    () -> assertThat(responseBodySearchQueryUpdate.type).isEqualTo(requestBodySearchQueryUpdate.type),
                    () -> assertThat(responseBodySearchQueryUpdate.value).isEqualTo(requestBodySearchQueryUpdate.value),
                    () -> assertThat(responseBodySearchQueryUpdate.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBodySearchQueryUpdate.deletedUtc).isNull()
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка тела ответа при получении поискового запроса с обязательными параметрами из списка поисковых запросов")
    class CheckGetESearchQueryWithOnlyRequiredParametersFromListOdataResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка поисковых запросов")
        @Description("Тест проверяет поля в теле ответа при получении списка поисковых запросов")
        public void testGetSearchQueryWithOnlyRequiredParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel searchQueryCreationResponseBody = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchQueryResponseModel searchQueryBodyFromODataListResponseBody = ApiMethodsSearchQuery.getSearchQueryListOData().jsonPath().getList("value", CommonSearchQueryResponseModel.class).stream()
                    .filter(searchQuery -> searchQuery.id.equals(searchQueryCreationResponseBody.id))
                    .findFirst().orElse(null);

            assertThat(searchQueryBodyFromODataListResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(searchQueryBodyFromODataListResponseBody.id).isEqualTo(searchQueryCreationResponseBody.id),
                    () -> assertThat(searchQueryBodyFromODataListResponseBody.name).isEqualTo(searchQueryCreationResponseBody.name),
                    () -> assertThat(searchQueryBodyFromODataListResponseBody.type).isEqualTo(searchQueryCreationResponseBody.type),
                    () -> assertThat(searchQueryBodyFromODataListResponseBody.value).isEqualTo(searchQueryCreationResponseBody.value),
                    () -> assertThat(searchQueryBodyFromODataListResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(searchQueryBodyFromODataListResponseBody.deletedUtc).isEqualTo(searchQueryCreationResponseBody.deletedUtc)
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка тела ответа при получении поискового запроса с обязательными параметрами по id")
    class CheckGetESearchQueryWithOnlyRequiredParametersByIdPathResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковый запрос")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка поисковых запросов")
        @Description("Тест проверяет поля в теле ответа при получении списка поисковых запросов")
        public void testGetSearchQueryWithOnlyRequiredParametersByIdCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel searchQueryCreationResponseBody = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchQueryResponseModel searchQueryBodyByIdResponseBody = ApiMethodsSearchQuery.getSearchQueryODataByIdPath(searchQueryCreationResponseBody.id).as(CommonSearchQueryResponseModel.class);

            assertAll(
                    () -> assertThat(searchQueryBodyByIdResponseBody.id).isEqualTo(searchQueryCreationResponseBody.id),
                    () -> assertThat(searchQueryBodyByIdResponseBody.name).isEqualTo(searchQueryCreationResponseBody.name),
                    () -> assertThat(searchQueryBodyByIdResponseBody.type).isEqualTo(searchQueryCreationResponseBody.type),
                    () -> assertThat(searchQueryBodyByIdResponseBody.value).isEqualTo(searchQueryCreationResponseBody.value),
                    () -> assertThat(searchQueryBodyByIdResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(searchQueryBodyByIdResponseBody.deletedUtc).isEqualTo(searchQueryCreationResponseBody.deletedUtc)
            );

        }

    }

}