package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import eDiscovery.helpers.enums.SearchQueryType;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests - SearchQuery")
public class SearchQueryCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание поискового запроса")
    @Description("Тест проверяет возможность создания поискового запроса")
    public void testAddSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQuery.addSearchQuery(requestBody);

        CommonSearchQueryResponseModel responseBody = response.as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.type).isEqualTo(SearchQueryType.Regex.name());
        assertThat(responseBody.value).isEqualTo(requestBody.value);
        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.createdUtc).matches(dateTimeUTCPattern());
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение поискового запроса")
    @Description("Тест проверяет возможность изменения поискового запроса")
    public void testUpdateSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        UpdateSearchQueryRequestModel requestModelSearchQueryUpdate = UpdateSearchQueryRequestModel.builder()
                .name(getRandomName())
                .type(SearchQueryType.Text.name())
                .value("\\d{11}")
                .id(responseBodySearchQueryCreation.id)
                .build();

        Response responseSearchQueryUpdate = ApiMethodsSearchQuery.updateSearchQuery(requestModelSearchQueryUpdate);

        CommonSearchQueryResponseModel responseBodySearchQueryUpdate = responseSearchQueryUpdate.as(CommonSearchQueryResponseModel.class);

        assertThat(responseBodySearchQueryUpdate.name).isEqualTo(requestModelSearchQueryUpdate.name);
        assertThat(responseBodySearchQueryUpdate.type).isEqualTo(SearchQueryType.Text.name());
        assertThat(responseBodySearchQueryUpdate.value).isEqualTo(requestModelSearchQueryUpdate.value);
        assertThat(isValidUUID(responseBodySearchQueryUpdate.id)).isTrue();
        assertThat(responseBodySearchQueryUpdate.createdUtc).matches(dateTimeUTCPattern());
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление поискового запроса")
    @Description("Тест проверяет возможность удаления поискового запроса")
    public void testDeleteSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQuery.getSearchQueryList();

        List<CommonSearchQueryResponseModel> responseBody = response.jsonPath().getList("", CommonSearchQueryResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData")
    public void testGetSearchQueryListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQuery.getSearchQueryListOData(new HashMap<>());

        List<CommonSearchQueryResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchQueryResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение поискового запроса по протоколу oData по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение поискового запроса по протоколу oData по id")
    @Description("Тест проверяет возможность получения поискового запроса по протоколу oData по id в скобках")
    public void testGetSearchQueryODataById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        String searchQueryNameForFilter = "testSearchQueryODataByIdInRoundBrackets" + getRandomName();

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(searchQueryNameForFilter)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
        ApiMethodsSearchQuery.addSearchQuery(requestBody);

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");

        List<CommonSearchQueryResponseModel> responseBodyWithFilter =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        CommonSearchQueryResponseModel responseBodyOData = responseBodyWithFilter.get(0);

        CommonSearchQueryResponseModel responseBodyODataById = ApiMethodsSearchQuery.getSearchQueryODataById(responseBodyOData.id).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBodyODataById.id).isEqualTo(responseBodyOData.id);
        assertThat(responseBodyODataById.name).isEqualTo(responseBodyOData.name);
        assertThat(responseBodyODataById.type).isEqualTo(responseBodyOData.type);
        assertThat(responseBodyODataById.value).isEqualTo(responseBodyOData.value);
        assertThat(responseBodyODataById.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyODataById.createdUtc).isEqualTo(responseBodyOData.createdUtc);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение поискового запроса по протоколу oData по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение поискового запроса по протоколу oData по id")
    @Description("Тест проверяет возможность получения поискового запроса по протоколу oData по id в path param")
    public void testGetSearchQueryODataByIdInPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        String searchQueryNameForFilter = "testSearchQueryODataByIdInRoundBrackets" + getRandomName();

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(searchQueryNameForFilter)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
        ApiMethodsSearchQuery.addSearchQuery(requestBody);

        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("$filter", "contains(name, '" + searchQueryNameForFilter + "')");

        List<CommonSearchQueryResponseModel> responseBodyWithFilter =
                ApiMethodsSearchQuery.getSearchQueryListOData(requestParameters)
                        .jsonPath().getList("value", CommonSearchQueryResponseModel.class);

        CommonSearchQueryResponseModel responseBodyOData = responseBodyWithFilter.get(0);

        CommonSearchQueryResponseModel responseBodyODataById = ApiMethodsSearchQuery.getSearchQueryODataByIdPath(responseBodyOData.id).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBodyODataById.id).isEqualTo(responseBodyOData.id);
        assertThat(responseBodyODataById.name).isEqualTo(responseBodyOData.name);
        assertThat(responseBodyODataById.type).isEqualTo(responseBodyOData.type);
        assertThat(responseBodyODataById.value).isEqualTo(responseBodyOData.value);
        assertThat(responseBodyODataById.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyODataById.createdUtc).isEqualTo(responseBodyOData.createdUtc);

    }

}
