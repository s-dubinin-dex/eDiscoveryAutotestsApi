package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMarkerResultFromDeal;
import eDiscovery.models.deal.markerResult.CommonMarkerResultFromDealModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Common positive tests - MarkerResultFromDeal")
public class MarkerResultFromDealCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования из сервиса Deal")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение результатов маркирования из сервиса Deal")
    @Description("Тест проверяет возможность получения результатов маркирования из сервиса Deal")
    public void testGetMarkerResultListFromDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonMarkerResultFromDealModel> responseBody = ApiMethodsMarkerResultFromDeal.getMarkerResultListFromDeal().jsonPath().getList("value", CommonMarkerResultFromDealModel.class);

    }
}
