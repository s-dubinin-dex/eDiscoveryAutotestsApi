package eDiscovery.tests.apiTests.commonPositiveTestsApi.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchQueryCommonPositiveTests extends TestBase {

    @Test
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание поискового запроса")
    @Description("Тест проверяет возможность создания поискового запроса")
    public void testAddSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = DataGeneratorDealService.getBasicSearchQueryModel();
        Response response = ApiMethodsSearchQuery.addSearchQuery(requestBody);

        CommonSearchQueryResponseModel responseBody = response.as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.type).isEqualTo(requestBody.type);
        assertThat(responseBody.id).isNotBlank();
    }

    @Test
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение поискового запроса")
    @Description("Тест проверяет возможность изменения поискового запроса")
    public void testUpdateSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBodySearchQueryCreation = DataGeneratorDealService.getBasicSearchQueryModel();
        Response responseSearchQueryCreation = ApiMethodsSearchQuery.addSearchQuery(requestBodySearchQueryCreation);

        CommonSearchQueryResponseModel responseBodySearchQueryCreation = responseSearchQueryCreation.as(CommonSearchQueryResponseModel.class);

        UpdateSearchQueryRequestModel requestModelSearchQueryCreation = UpdateSearchQueryRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .type("Regex")
                .value("\\d{11}")
                .id(responseBodySearchQueryCreation.id)
                .build();

        Response responseSearchQueryUpdate = ApiMethodsSearchQuery.updateSearchQuery(requestModelSearchQueryCreation);

        CommonSearchQueryResponseModel responseBodySearchQueryUpdate = responseSearchQueryUpdate.as(CommonSearchQueryResponseModel.class);

        assertThat(responseBodySearchQueryUpdate.name).isEqualTo(requestModelSearchQueryCreation.name);
        assertThat(responseBodySearchQueryUpdate.type).isEqualTo(requestModelSearchQueryCreation.type);
        assertThat(responseBodySearchQueryUpdate.id).isNotBlank();
    }

    @Test
    @Feature("Поисковый запрос")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление поискового запроса")
    @Description("Тест проверяет возможность удаления поискового запроса")
    public void testDeleteSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchQueryRequestModel requestBodySearchQueryCreation = DataGeneratorDealService.getBasicSearchQueryModel();
        Response responseSearchQueryCreation = ApiMethodsSearchQuery.addSearchQuery(requestBodySearchQueryCreation);
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = responseSearchQueryCreation.as(CommonSearchQueryResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchQuery.deleteSearchQuery(responseBodySearchQueryCreation.id);
    }

    @Test
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов")
    @Description("Тест проверяет возможность получения списка поисковых запросов")
    public void testGetSearchQueryList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsSearchQuery.addSearchQuery(DataGeneratorDealService.getBasicSearchQueryModel());
        Response response = ApiMethodsSearchQuery.getSearchQueryList();

        List<CommonSearchQueryResponseModel> responseBody = response.jsonPath().getList("");
        assertThat(responseBody).isNotEmpty();
    }

    @Test
    @Feature("Поисковый запрос")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData")
    public void testGetSearchPlaceListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsSearchQuery.addSearchQuery(DataGeneratorDealService.getBasicSearchQueryModel());
        Response response = ApiMethodsSearchQuery.getSearchQueryListOData();

        ArrayList<CommonSearchQueryResponseModel> responseBody = response.jsonPath().get("value");
        assertThat(responseBody).isNotEmpty();
    }

}
