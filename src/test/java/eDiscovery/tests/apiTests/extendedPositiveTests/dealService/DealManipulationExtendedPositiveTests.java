package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealManipulation.DealSearchPlaceModel;
import eDiscovery.models.deal.dealManipulation.DealSearchQueryModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.dateTimeISOPattern;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests - DealManipulation")
public class DealManipulationExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка дел")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска по протоколу oData для страницы списка дел")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData для страницы списка дел")
    public void testGetDealListODataForDealManipulationList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$filter", "contains(tolower(name),'')");
        parameters.put("$expand", "dealSearchQueries,searchPlaces");
        parameters.put("$orderby", "createdUtc desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");
        parameters.put("includeDeleted", "true");

        Response response = ApiMethodsDealManipulation.getDealManipulationListOData(parameters);

        List<CommonDealManipulationResponseModel> responseBody = response.jsonPath().getList("value", CommonDealManipulationResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Получение дела по ID")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение дела по id для карточки дела")
    @Description("Тест проверяет возможность получить дело по id в path param для карточки дела")
    public void testGetDealManipulationForDealManipulationCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$expand", "dealSearchQueries,searchPlaces,progressInfo,classifySearchPlaces,fileTypes,searchPlaceGroups");

        CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseBodyCreation.id, parameters).as(CommonDealManipulationResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.dealPriority).isEqualTo(responseBodyCreation.dealPriority);
        assertThat(responseBody.quarantine).isEqualTo(responseBodyCreation.quarantine);
        assertThat(responseBody.fileTypes).isEqualTo(responseBodyCreation.fileTypes);
        assertThat(responseBody.searchPlaces).usingRecursiveComparison().isEqualTo(
                Collections.singletonList(
                        new DealSearchPlaceModel(responseBodyCreation.searchPlaces.get(0).id, responseBodyCreation.searchPlaces.get(0).name)
                )
        );
        assertThat(responseBody.classifySearchPlaces).isEqualTo(responseBodyCreation.classifySearchPlaces);
        assertThat(responseBody.searchPlaceGroups).isEqualTo(responseBodyCreation.searchPlaceGroups);
        assertThat(responseBody.dealSearchQueries).isEqualTo(
                Collections.singletonList(
                        new DealSearchQueryModel(
                                responseBodyCreation.dealSearchQueries.get(0).isActive,
                                responseBodyCreation.dealSearchQueries.get(0).id,
                                responseBodyCreation.dealSearchQueries.get(0).name
                        )
                )
        );
        assertThat(responseBody.progressInfo).isEqualTo(responseBodyCreation.progressInfo);
        assertThat(responseBody.dealStatus).isEqualTo(responseBodyCreation.dealStatus);
        assertThat(responseBody.excludes).isEqualTo(responseBodyCreation.excludes);
        assertThat(responseBody.searchMask).isEqualTo(responseBodyCreation.searchMask);
        assertThat(responseBody.needClassify).isEqualTo(responseBodyCreation.needClassify);
        assertThat(responseBody.classifierProfileId).isEqualTo(responseBodyCreation.classifierProfileId);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.creatorUserId).isEqualTo(responseBodyCreation.creatorUserId);
        assertThat(responseBody.creatorUserName).isEqualTo(responseBodyCreation.creatorUserName);

    }

}
