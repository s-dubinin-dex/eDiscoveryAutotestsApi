package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests - SearchQuery")
public class SearchQueryExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными наименованиями")
    @Description("Тест проверяет возможность создания поискового запроса c различными наименованиями")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryNames")
    public void testAddSearchQueryWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(name)
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
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryTypes")
    public void testAddSearchQueryWithDifferentValidTypes(SearchQueryType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
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
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryValues")
    public void testAddSearchQueryWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .value(value)
                .build();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования в поисковом запросе")
    @Description("Тест проверяет возможность изменения наименования в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryNames")
    public void testUpdateSearchQueryWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(name)
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreation.id),
                Collections.singletonList(responseSearchQueryCreation.id)
        );

        String nameForUpdate = DataGeneratorDealService.getRandomName();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(nameForUpdate)
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
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryTypes")
    public void testUpdateSearchQueryWithDifferentValidTypes(SearchQueryType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();

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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreation.id),
                Collections.singletonList(responseSearchQueryCreation.id)
        );

        SearchQueryType typeForUpdating = SearchQueryType.Text;

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
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryValues")
    public void testUpdateSearchQueryWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreation.id),
                Collections.singletonList(responseSearchQueryCreation.id)
        );

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .value(DataGeneratorDealService.getRandomName())
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQuery.getSearchQueryListODataWithIncludeDeletedParameter(includeDeleted);

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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithFilter" + DataGeneratorDealService.getRandomName();

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(searchQueryNameForFilter)
                .value("\\d{10}")
                .build();
        ApiMethodsSearchQuery.addSearchQuery(requestBody);


        List<CommonSearchQueryResponseModel> responseBodyWithoutFilter =
                ApiMethodsSearchQuery.getSearchQueryListOData().jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithoutFilter.size()).isGreaterThan(1);
        assertThat(responseBodyWithoutFilter.get(0)).isNotNull();

        HashMap<String, String> responseParameters = new HashMap<>();
        responseParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");

        List<CommonSearchQueryResponseModel> responseBodyWithFilter =
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(responseParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithFilter.size()).isEqualTo(1);
        assertThat(responseBodyWithFilter.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с дефолтной сортировкой результата (по возрастанию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с дефолтной сортировкой результата (по возрастанию)")
    @Test
    public void testGetSearchQueryListODataWithDefaultAscendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithDefaultAscendingSorting" + DataGeneratorDealService.getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name");


        List<CommonSearchQueryResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(requestParameters)
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithExplicitAscendingSorting" + DataGeneratorDealService.getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name ASC");

        List<CommonSearchQueryResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(requestParameters)
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithExplicitDescendingSorting" + DataGeneratorDealService.getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name DESC");


        List<CommonSearchQueryResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(requestParameters)
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithPagination" + DataGeneratorDealService.getRandomName(10);

        for (int i = 0; i < 10; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name");
        requestParameters.put("$top", "5");
        requestParameters.put("$skip", "0");

        List<CommonSearchQueryResponseModel> responseBodyWithPagination =
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(requestParameters)
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
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(requestParameters)
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithPagination" + DataGeneratorDealService.getRandomName(10);

        for (int i = 0; i < 3; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQuery.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$top", "2");

        List<CommonSearchQueryResponseModel> responseBodyWithLimiting =
                ApiMethodsSearchQuery.getSearchQueryListODataWithParametersMap(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        assertThat(responseBodyWithLimiting.size()).isEqualTo(2);
        assertThat(responseBodyWithLimiting.get(1)).isNotNull();

    }
}