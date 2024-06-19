package eDiscovery.tests.apiTests.commonPositiveTestsApi.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealManipulationCommonPositiveTests extends TestBase {

    @Test
    @Story("Дело")
    @Feature("Создание дела")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание дела")
    @Description("Тест проверяет возможность создания дела")
    public void testAddDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel searchPlaceRequestBody = DataGeneratorDealService.getBasicSearchPlaceModelArmLocal();
        CommonSearchPlaceResponseModel searchPlaceResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceRequestBody).as(CommonSearchPlaceResponseModel.class);

        AddSearchQueryRequestModel searchQueryRequestBody = DataGeneratorDealService.getBasicSearchQueryModel();
        CommonSearchQueryResponseModel searchQueryResponseBody = ApiMethodsSearchQuery.addSearchQuery(searchQueryRequestBody).as(CommonSearchQueryResponseModel.class);



    }

}
