package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.models.odata.OdataListResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - SearchQuery: Расширенные позитивные тесты")
public class SearchQueryExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка odata параметров при получении списка поисковых запросов")
    class CheckGetSearchQueryListOdataParameters{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с фильтрацией по названию поискового запроса")
        @Description("Тест проверяет возможность получения списка поисковых запросов с фильтрацией по названию поискового запроса")
        public void testGetSearchQueryListWithFilterName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(name, '')")
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка фильтрации по названию поискового запроса при получении списка поисковых запросов")
        @Description("Тест проверяет фильтрацию по названию поискового запроса при получении списка поисковых запросов")
        public void testGetSearchQueryListWithFilterNameCheckFiltering(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            String searchQueryNameForFilter = getRandomName();

            AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
            requestBody.name = searchQueryNameForFilter;

            CommonSearchQueryResponseModel responseBodySearchQueryCreation = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(name, '%s')", searchQueryNameForFilter))
                    .build();

            CommonSearchQueryResponseModel responseBodySearchQueryFromList = ApiMethodsSearchQuery.getSearchQueryListOData(parameters).jsonPath().getList("value", CommonSearchQueryResponseModel.class).get(0);

            assertAll(
                    () -> assertThat(responseBodySearchQueryFromList.id).isEqualTo(responseBodySearchQueryCreation.id),
                    () -> assertThat(responseBodySearchQueryFromList.name).isEqualTo(responseBodySearchQueryCreation.name),
                    () -> assertThat(responseBodySearchQueryFromList.type).isEqualTo(responseBodySearchQueryCreation.type),
                    () -> assertThat(responseBodySearchQueryFromList.value).isEqualTo(responseBodySearchQueryCreation.value),
                    () -> assertThat(responseBodySearchQueryFromList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBodySearchQueryFromList.deletedUtc).isEqualTo(responseBodySearchQueryCreation.deletedUtc)
            );

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с фильтрацией по типу поискового запроса")
        @Description("Тест проверяет возможность получения списка поисковых запросов с фильтрацией по типу поискового запроса")
        public void testGetSearchQueryListWithFilterType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("type eq '%s'", SearchQueryType.Regex.name()))
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с сортировкой по дате создания")
        @Description("Тест проверяет возможность получения списка поисковых запросов с сортировкой по дате создания")
        public void testGetSearchQueryListWithSortingCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("createdUtc desc")
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с сортировкой по названию поискового запроса")
        @Description("Тест проверяет возможность получения списка поисковых запросов с сортировкой по названию поискового запроса")
        @Test
        public void testGetSearchQueryListWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("name desc")
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка сортировки по названию при получении списка поисковых запросов")
        @Description("Тест проверяет сортировку по названию при получении списка поисковых запросов")
        @Test
        public void testGetSearchQueryListWithSortingNameCheckSorting(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            String searchQueryNameForFilter = getRandomName(10);

            for (int i = 0; i < 3; i++){
                AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
                requestBody.name = searchQueryNameForFilter + i;
                ApiMethodsSearchQuery.addSearchQuery(requestBody);
            }

            Map<String, String> requestParameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(name, '%s')", searchQueryNameForFilter))
                    .withOrderBy("name asc")
                    .build();

            List<CommonSearchQueryResponseModel> responseBodyWithSorting =
                    ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

            assertThat(responseBodyWithSorting.size()).isEqualTo(3);
            assertThat(responseBodyWithSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
            assertThat(responseBodyWithSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
            assertThat(responseBodyWithSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с сортировкой по типу поискового запроса")
        @Description("Тест проверяет возможность получения списка поисковых запросов с сортировкой по типу поискового запроса")
        @Test
        public void testGetSearchQueryListWithSortingType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withOrderBy("type asc")
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с подсчётом количества результатов")
        @Description("Тест проверяет возможность получения списка поисковых запросов с подсчётом количества результатов")
        @Test
        public void testGetSearchQueryListWithCount(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withCount(true)
                    .build();

            Response response = ApiMethodsSearchQuery.getSearchQueryListOData(parameters);

            OdataListResponseModel responseListWithCount = response.as(OdataListResponseModel.class);
            List<CommonSearchQueryResponseModel> responseElements = response.jsonPath().getList("value", CommonSearchQueryResponseModel.class);

            assertThat(Integer.parseInt(responseListWithCount.odataCount)).isEqualTo(responseElements.size());
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с ограничением количества результатов = 100")
        @Description("Тест проверяет возможность получения списка поисковых запросов с ограничением количества результатов = 100")
        @Test
        public void testGetSearchQueryListWithTop100(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withTop(100)
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов по протоколу oData с лимитированием количества объектов в результате")
        @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с лимитированием количества объектов в результате")
        @Test
        public void testGetSearchQueryListODataWithLimit(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            String searchQueryNameForFilter = getRandomName(10);

            for (int i = 0; i < 3; i++){
                AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
                requestBody.name = searchQueryNameForFilter + i;

                ApiMethodsSearchQuery.addSearchQuery(requestBody);
            }

            Map<String, String> requestParameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(name, '%s')", searchQueryNameForFilter))
                    .withTop(2)
                    .build();

            List<CommonSearchQueryResponseModel> responseBodyWithLimiting =
                    ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

            assertThat(responseBodyWithLimiting.size()).isEqualTo(2);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов с пропуском первых 10 результатов")
        @Description("Тест проверяет возможность получения списка поисковых запросов с пропуском первых 10 результатов")
        @Test
        public void testGetSearchQueryListWithSkip10(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withSkip(10)
                    .build();

            ApiMethodsSearchQuery.getSearchQueryListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов по протоколу oData с пагинацией результата")
        @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с пагинацией результата")
        @Test
        public void testGetSearchQueryListODataWithPagination(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            String searchQueryNameForFilter = getRandomName(10);

            for (int i = 0; i < 3; i++){
                AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
                requestBody.name = searchQueryNameForFilter + i;

                ApiMethodsSearchQuery.addSearchQuery(requestBody);
            }

            Map<String, String> requestParameters = OdataParametersBuilder.builder()
                    .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                    .withOrderBy("name")
                    .withSkip(0)
                    .build();

            List<CommonSearchQueryResponseModel> responseBodyWithPagination =
                    ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

            assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
            assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
            assertThat(responseBodyWithPagination.get(2).name).isEqualTo(searchQueryNameForFilter + 2);

            requestParameters.put("$skip", "1");

            responseBodyWithPagination =
                    ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

            assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchQueryNameForFilter + 1);
            assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchQueryNameForFilter + 2);
        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка создания поисковых запросов с различными параметрами")
    class CheckAddSearchQueryWithDifferentFieldsValues{

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Создание поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание поискового запроса c различными наименованиями")
        @Description("Тест проверяет возможность создания поискового запроса c различными наименованиями")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryNames")
        public void testAddSearchQueryWithDifferentValidNames(String name){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
            requestBody.name = name;

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertThat(responseBody.name).isEqualTo(name);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Создание поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание поискового запроса c различными type")
        @Description("Тест проверяет возможность создания поискового запроса c различными type")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryTypes")
        public void testAddSearchQueryWithDifferentValidTypes(SearchQueryType type){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
            requestBody.type = type.name();

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertThat(responseBody.type).isEqualTo(type.name());
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Создание поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание поискового запроса c различными values")
        @Description("Тест проверяет возможность создания поискового запроса c различными values")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryValues")
        public void testAddSearchQueryWithDifferentValidValues(String value){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
            requestBody.value = value;

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertThat(responseBody.value).isEqualTo(value);
        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка изменения поисковых запросов с различными параметрами")
    class CheckUpdateSearchQueryWithDifferentFieldsValues{

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Изменение поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение наименования в поисковом запросе")
        @Description("Тест проверяет возможность изменения наименования в поисковом запросе")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryNames")
        public void testUpdateSearchQueryWithDifferentValidNames(String name){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
            UpdateSearchQueryRequestModel requestBody = new UpdateSearchQueryRequestModel(responseSearchQueryCreation);
            requestBody.name = name;

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertThat(responseBody.name).isEqualTo(name);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Изменение поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение type в поисковом запросе")
        @Description("Тест проверяет возможность изменения type в поисковом запросе")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryTypes")
        public void testUpdateSearchQueryWithDifferentValidTypes(SearchQueryType type){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
            UpdateSearchQueryRequestModel requestBody = new UpdateSearchQueryRequestModel(responseSearchQueryCreation);
            requestBody.type = type.name();

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertThat(responseBody.type).isEqualTo(type.name());
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Изменение поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение value в поисковом запросе")
        @Description("Тест проверяет возможность изменения value в поисковом запросе")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryValues")
        public void testUpdateSearchQueryWithDifferentValidValues(String value){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
            UpdateSearchQueryRequestModel requestBody = new UpdateSearchQueryRequestModel(responseSearchQueryCreation);
            requestBody.value = value;

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
            assertThat(responseBody.value).isEqualTo(value);
        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка изменения поискового запроса, используемого в созданном деле")
    class CheckUpdateSearchQueryInCreatedDeal{

        @Test
        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Изменение поискового запроса")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение поискового запроса, который используется в созданном деле")
        @Description("Тест проверяет возможность изменения поискового запроса, который используется в созданном деле")
        public void testUpdateSearchQueryInCreatedDeal(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
            CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters(
                    responseSearchPlaceCreation.id,
                    responseSearchQueryCreation.id
            );

            UpdateSearchQueryRequestModel requestBody = new  UpdateSearchQueryRequestModel(responseSearchQueryCreation);
            requestBody.name = getRandomName();
            requestBody.type = SearchQueryType.Text.name();
            requestBody.value = "abc";

            CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

            assertAll(
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.type).isEqualTo(requestBody.type),
                    () -> assertThat(responseBody.value).isEqualTo(requestBody.value)
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchQuery: Проверка работы методов, используемых на UI")
    class CheckSearchQueryUIMethods{

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов")
        @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов")
        @Test
        public void testGetSearchQueryListForSearchQueriesListWEBUI() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name), '')")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchQueryResponseModel> resultBody = ApiMethodsSearchQuery.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов для выпадающего списка поисковых запросов")
        @Description("Тест проверяет возможность получения списка поисковых запросов для выпадающего списка поисковых запросов")
        @Test
        public void testGetSearchQueryListForSearchQueriesListWEBUIForFilterSearchQuery() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name), '')")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchQueryResponseModel> resultBody = ApiMethodsSearchQuery.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        }

        @ParameterizedTest
        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с фильтром по типу поискового запроса")
        @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с фильтром по типу поискового запроса")
        @MethodSource("eDiscovery.helpers.enums.SearchQueryType#getValidSearchQueryTypes")
        public void testGetSearchQueryListForSearchQueriesListWEBUIWithFilterSearchQueryType(SearchQueryType searchQueryType) {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(tolower(name),'') and type eq '%s'", searchQueryType.name()))
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchQueryResponseModel> resultBody = ApiMethodsSearchQuery.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Названию")
        @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Названию")
        @Test
        public void testGetSearchQueryListForSearchQueriesListWEBUIWithSortingName() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name), '')")
                    .withOrderBy("name asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchQueryResponseModel> resultBody = ApiMethodsSearchQuery.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        }

        @Epic("Сервис Deal")
        @Feature("Поисковые запросы")
        @Story("Получение списка поисковых запросов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Типу")
        @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Типу")
        @Test
        public void testGetSearchQueryListForSearchQueriesListWEBUIWithSortingType() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name), '')")
                    .withOrderBy("type asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchQueryResponseModel> resultBody = ApiMethodsSearchQuery.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        }

    }

}
