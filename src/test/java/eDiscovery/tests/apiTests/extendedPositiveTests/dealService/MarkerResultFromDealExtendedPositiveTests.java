package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMarkerResultFromDeal;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.OdataParametersBuilder;
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
    @DisplayName("Получение результатов маркирования из сервиса Deal для карточки дела (без фильтров)")
    @Description("Тест проверяет возможность получения результатов маркирования из сервиса Deal для карточки дела (без фильтров)")
    public void testGetMarkerResultListFromDealForDealCardWithoutFilters(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultFromDealModel> responseBody = ApiMethodsMarkerResultFromDeal.getMarkerResultListFromDeal(parameters).jsonPath().getList("value", CommonMarkerResultFromDealModel.class);

    }

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

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and externalId eq %s", dealManipulationResponseModel.id))
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultFromDealModel> responseBody = ApiMethodsMarkerResultFromDeal.getMarkerResultListFromDeal(parameters).jsonPath().getList("value", CommonMarkerResultFromDealModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования из сервиса Deal")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение результатов маркирования из сервиса Deal для карточки дела с фильтром по периоду")
    @Description("Тест проверяет возможность получения результатов маркирования из сервиса Deal для карточки дела с фильтром по периоду")
    public void testGetMarkerResultListFromDealForDealCardWithFilterActionDate(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel dealManipulationResponseModel = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and externalId eq %s and actionDate ge 2024-12-03T22:00:00.000Z and actionDate le 2024-12-07T21:59:59.999Z", dealManipulationResponseModel.id))
                .withExpand("startMarker,resultMarker,startPolicy,resultPolicy")
                .withOrderBy("actionDate desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResultFromDealModel> responseBody = ApiMethodsMarkerResultFromDeal.getMarkerResultListFromDeal(parameters).jsonPath().getList("value", CommonMarkerResultFromDealModel.class);

    }









}
