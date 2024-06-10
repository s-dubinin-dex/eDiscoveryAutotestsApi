package eDiscovery.tests.apiTests.commonPositiveTestsApi.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        Response response = ApiMethodsSearchPlace.addSearchPlace(DataGeneratorDealService.getBasicSearchPlaceModel());

        CommonSearchPlaceResponseModel responseBody = response.as(CommonSearchPlaceResponseModel.class);
    }
}
