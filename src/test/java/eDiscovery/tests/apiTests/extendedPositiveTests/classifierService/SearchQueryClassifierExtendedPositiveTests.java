package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsSearchQueryClassifier;
import eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.classifier.searchQuery.AddSearchQueryClassifierRequestModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;
import eDiscovery.models.classifier.searchQuery.UpdateSearchQueryClassifierRequestModel;
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

@DisplayName("Extended positive tests: Classifier - SearchQuery")
public class SearchQueryClassifierExtendedPositiveTests extends TestBase {


    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными наименованиями")
    @Description("Тест проверяет возможность создания поискового запроса c различными наименованиями")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier#getValidSearchQueryNames")
    public void testAddSearchQueryClassifierWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                .name(name)
                .type(SearchQueryType.Regex.name())
                .value("abc")
                .build();

        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными type")
    @Description("Тест проверяет возможность создания поискового запроса c различными type")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier#getValidSearchQueryTypes")
    public void testAddSearchQueryClassifierWithDifferentValidTypes(SearchQueryType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                .name(getRandomName())
                .type(type.name())
                .value("abc")
                .build();

        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type.name());
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными values")
    @Description("Тест проверяет возможность создания поискового запроса c различными values")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier#getValidSearchQueryValues")
    public void testAddSearchQueryClassifierWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                .name(getRandomName())
                .type(SearchQueryType.Regex.name())
                .value(value)
                .build();

        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.value).isEqualTo(value);
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования в поисковом запросе")
    @Description("Тест проверяет возможность изменения наименования в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier#getValidSearchQueryNames")
    public void testUpdateSearchQueryClassifierWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryClassifierResponseModel responseSearchQueryCreation = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

        UpdateSearchQueryClassifierRequestModel requestBody = UpdateSearchQueryClassifierRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(name)
                .type(SearchQueryType.Regex.name())
                .value("abc")
                .build();

        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.updateSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение type в поисковом запросе")
    @Description("Тест проверяет возможность изменения type в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier#getValidSearchQueryTypes")
    public void testUpdateSearchQueryClassifierWithDifferentValidTypes(SearchQueryType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryClassifierResponseModel responseSearchQueryCreation = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

        UpdateSearchQueryClassifierRequestModel requestBody = UpdateSearchQueryClassifierRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(type.name())
                .value("abc")
                .build();

        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.updateSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type.name());
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение value в поисковом запросе")
    @Description("Тест проверяет возможность изменения value в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier#getValidSearchQueryValues")
    public void testUpdateSearchQueryClassifierWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryClassifierResponseModel responseSearchQueryCreation = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

        UpdateSearchQueryClassifierRequestModel requestBody = UpdateSearchQueryClassifierRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(SearchQueryType.Regex.name())
                .value(value)
                .build();

        ApiMethodsSearchQueryClassifier.updateSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.MINOR)
    @Link("http://jira.lan:8080/browse/ED-203")
    @DisplayName("Получение списка поисковых запросов с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @Description("Тест проверяет возможность получения списка поисковых запросов с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    public void testGetSearchQueryClassifierListWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQueryClassifier.getSearchQueryListWithIncludeDeletedParameter(includeDeleted);

        List<CommonSearchQueryClassifierResponseModel> responseBody = response.jsonPath().getList("", CommonSearchQueryClassifierResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.MINOR)
    @Link("http://jira.lan:8080/browse/ED-203")
    @DisplayName("Получение списка поисковых запросов по протоколу oData с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    public void testGetSearchQueryClassifierListODataWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(includeDeleted);

        List<CommonSearchQueryClassifierResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с фильтрацией результата")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с фильтрацией результата")
    @Test
    public void testGetSearchQueryClassifierClassifierListODataWithFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithFilter" + getRandomName();

        AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                .name(searchQueryNameForFilter)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
        ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);


        List<CommonSearchQueryClassifierResponseModel> responseBodyWithoutFilter =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(new HashMap<>()).jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithoutFilter.size()).isGreaterThan(1);
        assertThat(responseBodyWithoutFilter.get(0)).isNotNull();

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");

        List<CommonSearchQueryClassifierResponseModel> responseBodyWithFilter =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithFilter.size()).isEqualTo(1);
        assertThat(responseBodyWithFilter.get(0)).isNotNull();
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с подсчётом количества результатов")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с подсчётом количества результатов")
    @Test
    public void testGetSearchQueryClassifierListODataWithCount(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$count", "true");

        Response responseBodyWithCount =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters);

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        Response responseBodyWithCountAfterAddNewOne =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters);

        int resultCount = responseBodyWithCount.jsonPath().getInt("\"@odata.count\"");
        int resultCountAfterAddNewOne = responseBodyWithCountAfterAddNewOne.jsonPath().getInt("\"@odata.count\"");

        List<CommonSearchQueryClassifierResponseModel> responseBody = responseBodyWithCountAfterAddNewOne.jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(resultCountAfterAddNewOne).isEqualTo(resultCount + 1);
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с дефолтной сортировкой результата (по возрастанию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с дефолтной сортировкой результата (по возрастанию)")
    @Test
    public void testGetSearchQueryClassifierListODataWithDefaultAscendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithDefaultAscendingSorting" + getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name");


        List<CommonSearchQueryClassifierResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithFilterSorting.size()).isEqualTo(5);
        assertThat(responseBodyWithFilterSorting.get(1)).isNotNull();
        assertThat(responseBodyWithFilterSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
        assertThat(responseBodyWithFilterSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithFilterSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithFilterSorting.get(3).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithFilterSorting.get(4).name).isEqualTo(searchQueryNameForFilter + 4);

    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с явной сортировкой результата (по возрастанию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с явной сортировкой результата (по возрастанию)")
    @Test
    public void testGetSearchQueryClassifierListODataWithExplicitAscendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithExplicitAscendingSorting" + getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name ASC");

        List<CommonSearchQueryClassifierResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithFilterSorting.size()).isEqualTo(5);
        assertThat(responseBodyWithFilterSorting.get(1)).isNotNull();
        assertThat(responseBodyWithFilterSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
        assertThat(responseBodyWithFilterSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithFilterSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithFilterSorting.get(3).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithFilterSorting.get(4).name).isEqualTo(searchQueryNameForFilter + 4);

    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с явной сортировкой результата (по убыванию)")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с явной сортировкой результата (по убыванию)")
    @Test
    public void testGetSearchQueryClassifierListODataWithExplicitDescendingSorting(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithExplicitDescendingSorting" + getRandomName(10);

        for (int i = 0; i < 5; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name DESC");


        List<CommonSearchQueryClassifierResponseModel> responseBodyWithFilterSorting =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithFilterSorting.size()).isEqualTo(5);
        assertThat(responseBodyWithFilterSorting.get(1)).isNotNull();
        assertThat(responseBodyWithFilterSorting.get(0).name).isEqualTo(searchQueryNameForFilter + 4);
        assertThat(responseBodyWithFilterSorting.get(1).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithFilterSorting.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithFilterSorting.get(3).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithFilterSorting.get(4).name).isEqualTo(searchQueryNameForFilter + 0);

    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с пагинацией результата")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с пагинацией результата")
    @Test
    public void testGetSearchQueryClassifierListODataWithPagination(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithPagination" + getRandomName(10);

        for (int i = 0; i < 10; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$orderby", "name");
        requestParameters.put("$top", "5");
        requestParameters.put("$skip", "0");

        List<CommonSearchQueryClassifierResponseModel> responseBodyWithPagination =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithPagination.size()).isEqualTo(5);
        assertThat(responseBodyWithPagination.get(1)).isNotNull();
        assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchQueryNameForFilter + 0);
        assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchQueryNameForFilter + 1);
        assertThat(responseBodyWithPagination.get(2).name).isEqualTo(searchQueryNameForFilter + 2);
        assertThat(responseBodyWithPagination.get(3).name).isEqualTo(searchQueryNameForFilter + 3);
        assertThat(responseBodyWithPagination.get(4).name).isEqualTo(searchQueryNameForFilter + 4);

        requestParameters.put("$skip", "5");

        responseBodyWithPagination =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithPagination.size()).isEqualTo(5);
        assertThat(responseBodyWithPagination.get(1)).isNotNull();
        assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchQueryNameForFilter + 5);
        assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchQueryNameForFilter + 6);
        assertThat(responseBodyWithPagination.get(2).name).isEqualTo(searchQueryNameForFilter + 7);
        assertThat(responseBodyWithPagination.get(3).name).isEqualTo(searchQueryNameForFilter + 8);
        assertThat(responseBodyWithPagination.get(4).name).isEqualTo(searchQueryNameForFilter + 9);

    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData с лимитированием количества объектов в результате")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData с лимитированием количества объектов в результате")
    @Test
    public void testGetSearchQueryClassifierListODataWithLimit(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        String searchQueryNameForFilter = "testSearchQueryListODataWithPagination" + getRandomName(10);

        for (int i = 0; i < 3; i++){
            String searchQueryNameForFilterWithNumber = searchQueryNameForFilter + i;
            AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                    .name(searchQueryNameForFilterWithNumber)
                    .type(SearchQueryType.Regex.name())
                    .value("\\d{10}")
                    .build();
            ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);
        }

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");
        requestParameters.put("$top", "2");

        List<CommonSearchQueryClassifierResponseModel> responseBodyWithLimiting =
                ApiMethodsSearchQueryClassifier.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyWithLimiting.size()).isEqualTo(2);
        assertThat(responseBodyWithLimiting.get(1)).isNotNull();

    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка поисковых запросов для выпадающего списка поисковых запросов")
    @Description("Тест проверяет возможность получения списка поисковых запросов для выпадающего списка поисковых запросов")
    @Test
    public void testGetSearchQueryClassifierListForSearchQueriesListWEBUIForFilterSearchQuery() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name), '')")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchQueryClassifierResponseModel> resultBody = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов")
    @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов")
    @Test
    public void testGetSearchQueryClassifierListForSearchQueriesListWEBUI() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name), '')")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchQueryClassifierResponseModel> resultBody = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
    }

    @ParameterizedTest
    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с фильтром по типу поискового запроса")
    @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с фильтром по типу поискового запроса")
    @MethodSource("eDiscovery.helpers.enums.SearchQueryType#getValidSearchQueryTypes")
    public void testGetSearchQueryClassifierListForSearchQueriesListWEBUIWithFilterSearchQueryType(SearchQueryType searchQueryType) {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter(String.format("contains(tolower(name),'') and type eq '%s'", searchQueryType.name()))
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchQueryClassifierResponseModel> resultBody = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Названию")
    @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Названию")
    @Test
    public void testGetSearchQueryClassifierListForSearchQueriesListWEBUIWithSortingName() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name), '')")
                .withOrderBy("name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchQueryClassifierResponseModel> resultBody = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
    }

    @Epic("Сервис Classifier")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Типу")
    @Description("Тест проверяет возможность получения списка поисковых запросов для отображения в списке поисковых запросов с сортировкой по Типу")
    @Test
    public void testGetSearchQueryClassifierListForSearchQueriesListWEBUIWithSortingType() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name), '')")
                .withOrderBy("type asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchQueryClassifierResponseModel> resultBody = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(params).jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
    }
}
