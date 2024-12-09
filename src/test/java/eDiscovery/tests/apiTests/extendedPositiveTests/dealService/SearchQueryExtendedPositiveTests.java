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
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - SearchQuery")
public class SearchQueryExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными наименованиями")
    @Description("Тест проверяет возможность создания поискового запроса c различными наименованиями")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryNames")
    public void testAddSearchQueryWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(name)
                .type(SearchQueryType.Regex.name())
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными type")
    @Description("Тест проверяет возможность создания поискового запроса c различными type")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryTypes")
    public void testAddSearchQueryWithDifferentValidTypes(String type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(getRandomName())
                .type(type)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными values")
    @Description("Тест проверяет возможность создания поискового запроса c различными values")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryValues")
    public void testAddSearchQueryWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(getRandomName())
                .type(SearchQueryType.Regex.name())
                .value(value)
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.value).isEqualTo(value);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
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

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(name)
                .type(SearchQueryType.Regex.name())
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования в поисковом запросе, который используется в деле")
    @Description("Тест проверяет возможность изменения наименования в поисковом запросе, который используется в деле")
    public void testUpdateSearchQueryNameInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters(
                responseSearchPlaceCreation.id,
                responseSearchQueryCreation.id
        );

        String nameForUpdate = getRandomName();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(nameForUpdate)
                .type(SearchQueryType.Regex.name())
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
        assertThat(responseBody.name).isEqualTo(nameForUpdate);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение type в поисковом запросе")
    @Description("Тест проверяет возможность изменения type в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getValidSearchQueryTypes")
    public void testUpdateSearchQueryWithDifferentValidTypes(String type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(type)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение type в поисковом запросе, который используется в деле")
    @Description("Тест проверяет возможность изменения type в поисковом запросе, который используется в деле")
    public void testUpdateSearchQueryTypeInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters(
                responseSearchPlaceCreation.id,
                responseSearchQueryCreation.id
        );

        String typeForUpdating = SearchQueryType.Text.name();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(typeForUpdating)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
        assertThat(responseBody.type).isEqualTo(typeForUpdating);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
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

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(SearchQueryType.Regex.name())
                .value(value)
                .build();

        ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение value в поисковом запросе, который используется в деле")
    @Description("Тест проверяет возможность изменения value в поисковом запросе, который используется в деле")
    public void testUpdateSearchQueryValueInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters(
                responseSearchPlaceCreation.id,
                responseSearchQueryCreation.id
        );

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(SearchQueryType.Regex.name())
                .value(getRandomName())
                .build();

        ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.MINOR)
    @Link("http://jira.lan:8080/browse/ED-203")
    @DisplayName("Получение списка поисковых запросов с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @Description("Тест проверяет возможность получения списка поисковых запросов с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    public void testGetSearchQueryListWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQuery.getSearchQueryListWithIncludeDeletedParameter(includeDeleted);

        List<CommonSearchQueryResponseModel> responseBody = response.jsonPath().getList("", CommonSearchQueryResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.MINOR)
    @Link("http://jira.lan:8080/browse/ED-203")
    @DisplayName("Получение списка поисковых запросов по протоколу oData с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    public void testGetSearchQueryListODataWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQuery.getSearchQueryListOData(includeDeleted);

        List<CommonSearchQueryResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с фильтрацией результата")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с фильтрацией результата")
    @Test
    public void testGetSearchQueryListODataWithFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithFilter" + getRandomName();

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(searchQueryNameForFilter)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
        ApiMethodsSearchQuery.addSearchQuery(requestBody);


        List<CommonSearchQueryResponseModel> responseBodyWithoutFilter =
                ApiMethodsSearchQuery.getSearchQueryListOData(new HashMap<>()).jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithoutFilter.size()).isGreaterThan(1);
        assertThat(responseBodyWithoutFilter.get(0)).isNotNull();

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                .build();

        List<CommonSearchQueryResponseModel> responseBodyWithFilter =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithFilter.size()).isEqualTo(1);
        assertThat(responseBodyWithFilter.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с подсчётом количества результатов")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с подсчётом количества результатов")
    @Test
    public void testGetSearchQueryListODataWithCount(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withCount(true)
                .build();

        Response responseBodyWithCount = ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters);

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        Response responseBodyWithCountAfterAddNewOne = ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters);

        int resultCount = responseBodyWithCount.jsonPath().getInt("\"@odata.count\"");
        int resultCountAfterAddNewOne = responseBodyWithCountAfterAddNewOne.jsonPath().getInt("\"@odata.count\"");

        List<CommonSearchQueryResponseModel> responseBody = responseBodyWithCountAfterAddNewOne.jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(resultCountAfterAddNewOne).isEqualTo(resultCount + 1);
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с дефолтной сортировкой результата (по возрастанию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с дефолтной сортировкой результата (по возрастанию)")
    @Test
    public void testGetSearchQueryListODataWithDefaultAscendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithDefaultAscendingSorting" + getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                .withOrderBy("name")
                .build();

        List<CommonSearchQueryResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithFilterSorting.size()).isEqualTo(5);
        assertThat(responseBodyWithFilterSorting.get(1)).isNotNull();
        assertThat(responseBodyWithFilterSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
        assertThat(responseBodyWithFilterSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithFilterSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithFilterSorting.get(3).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithFilterSorting.get(4).name).isEqualTo(searchQueryNameForFilter + 4);

    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с явной сортировкой результата (по возрастанию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с явной сортировкой результата (по возрастанию)")
    @Test
    public void testGetSearchQueryListODataWithExplicitAscendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithExplicitAscendingSorting" + getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                .withOrderBy("name ASC")
                .build();

        List<CommonSearchQueryResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithFilterSorting.size()).isEqualTo(5);
        assertThat(responseBodyWithFilterSorting.get(1)).isNotNull();
        assertThat(responseBodyWithFilterSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
        assertThat(responseBodyWithFilterSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithFilterSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithFilterSorting.get(3).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithFilterSorting.get(4).name).isEqualTo(searchQueryNameForFilter + 4);

    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с явной сортировкой результата (по убыванию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с явной сортировкой результата (по убыванию)")
    @Test
    public void testGetSearchQueryListODataWithExplicitDescendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithExplicitDescendingSorting" + getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                .withOrderBy("name DESC")
                .build();

        List<CommonSearchQueryResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithFilterSorting.size()).isEqualTo(5);
        assertThat(responseBodyWithFilterSorting.get(1)).isNotNull();
        assertThat(responseBodyWithFilterSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 4);
        assertThat(responseBodyWithFilterSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithFilterSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithFilterSorting.get(3).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithFilterSorting.get(4).name).isEqualTo(searchQueryNameForFilter + 0);

    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с пагинацией результата")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с пагинацией результата")
    @Test
    public void testGetSearchQueryListODataWithPagination(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithPagination" + getRandomName(10);

        for (int i = 0; i < 10; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                .withOrderBy("name")
                .withTop(5)
                .withSkip(0)
                .build();

        List<CommonSearchQueryResponseModel> responseBodyWithPagination =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithPagination.size()).isEqualTo(5);
        assertThat(responseBodyWithPagination.get(1)).isNotNull();
        assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
        assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithPagination.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithPagination.get(3).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithPagination.get(4).name).isEqualTo(searchQueryNameForFilter + 4);

        requestParameters.put("$skip", "5");

        responseBodyWithPagination =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithPagination.size()).isEqualTo(5);
        assertThat(responseBodyWithPagination.get(1)).isNotNull();
        assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchQueryNameForFilter + 5);
        assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchQueryNameForFilter + 6);
        assertThat(responseBodyWithPagination.get(2).name).isEqualTo(searchQueryNameForFilter + 7);
        assertThat(responseBodyWithPagination.get(3).name).isEqualTo(searchQueryNameForFilter + 8);
        assertThat(responseBodyWithPagination.get(4).name).isEqualTo(searchQueryNameForFilter + 9);

    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с лимитированием количества объектов в результате")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с лимитированием количества объектов в результате")
    @Test
    public void testGetSearchQueryListODataWithLimit(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithPagination" + getRandomName(10);

        for (int i = 0; i < 3; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        Map<String, String> requestParameters = OdataParametersBuilder.builder()
                .withFilter("contains(name, '" + searchQueryNameForFilter + "')")
                .withTop(2)
                .build();

        List<CommonSearchQueryResponseModel> responseBodyWithLimiting =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithLimiting.size()).isEqualTo(2);
        assertThat(responseBodyWithLimiting.get(1)).isNotNull();

    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
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
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
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
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с фильтром по типу поискового запроса")
    @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с фильтром по типу поискового запроса")
    @MethodSource("eDiscovery.helpers.enums.SearchQueryType#getValidSearchQueryTypes")
    public void testGetSearchQueryListForSearchQueriesListWEBUIWithFilterSearchQueryType(String searchQueryType) {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter(String.format("contains(tolower(name),'') and type eq '%s'", searchQueryType))
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchQueryResponseModel> resultBody = ApiMethodsSearchQuery.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryResponseModel.class);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
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
    @Feature("Поисковый запрос")
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
