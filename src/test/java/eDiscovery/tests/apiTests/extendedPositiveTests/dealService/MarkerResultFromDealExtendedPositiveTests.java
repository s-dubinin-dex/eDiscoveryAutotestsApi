package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMarkerResultFromDeal;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.markerResult.CommonMarkerResultFromDealModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DisplayName("Extended positive tests: Deal - MarkerResultFromDeal")
public class MarkerResultFromDealExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования из сервиса Deal")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение результатов маркирования из сервиса Deal для карточки дела")
    @Description("Тест проверяет возможность получения результатов маркирования из сервиса Deal для карточки дела")
    public void testGetMarkerResultListFromDealForDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel dealManipulationResponseModel = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$filter", String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and externalId eq %s", dealManipulationResponseModel.id));
        parameters.put("$expand", "startMarker,resultMarker");
        parameters.put("$orderby", "actionDate desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");


        List<CommonMarkerResultFromDealModel> responseBody = ApiMethodsMarkerResultFromDeal.getMarkerResultListFromDeal(parameters).jsonPath().getList("value", CommonMarkerResultFromDealModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования из сервиса Deal")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение результатов маркирования из сервиса Deal для карточки дела (без фильтров)")
    @Description("Тест проверяет возможность получения результатов маркирования из сервиса Deal для карточки дела (без фильтров)")
    public void testGetMarkerResultListFromDealForDealCardWithoutFilters(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$expand", "startMarker,resultMarker");
        parameters.put("$orderby", "actionDate desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");

        List<CommonMarkerResultFromDealModel> responseBody = ApiMethodsMarkerResultFromDeal.getMarkerResultListFromDeal(parameters).jsonPath().getList("value", CommonMarkerResultFromDealModel.class);

    }

}
