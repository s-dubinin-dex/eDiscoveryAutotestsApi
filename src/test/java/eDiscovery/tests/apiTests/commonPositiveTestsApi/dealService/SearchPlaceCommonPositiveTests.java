package eDiscovery.tests.apiTests.commonPositiveTestsApi.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPlaceCommonPositiveTests extends TestBase {
    @Test
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет возможность создания места поиска")
    public void testAddSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = DataGeneratorDealService.getBasicSearchPlaceModel();

        Response response = ApiMethodsSearchPlace.addSearchPlace(requestBody);
        CommonSearchPlaceResponseModel responseBody = response.as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.categoryType).isEqualTo(requestBody.categoryType);
        assertThat(responseBody.type).isEqualTo(requestBody.type);
        assertThat(responseBody.parameters).isEqualTo(requestBody.parameters);
        assertThat(responseBody.excludes).isEqualTo(requestBody.excludes);
        assertThat(responseBody.id).isNotBlank();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
    }
}
