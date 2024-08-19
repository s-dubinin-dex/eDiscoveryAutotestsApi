package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.AddDealManipulationRequestModel;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealManipulation.DealCardModel;
import eDiscovery.models.deal.dealManipulation.UpdateDealManipulationRequestModel;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests - DealManipulation")
public class DealManipulationCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Создание дела")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание дела")
    @Description("Тест проверяет возможность создания дела")
    public void testAddDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel searchPlaceRequestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
        CommonSearchPlaceResponseModel searchPlaceResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceRequestBody).as(CommonSearchPlaceResponseModel.class);

        AddSearchQueryRequestModel searchQueryRequestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
        CommonSearchQueryResponseModel searchQueryResponseBody = ApiMethodsSearchQuery.addSearchQuery(searchQueryRequestBody).as(CommonSearchQueryResponseModel.class);

        AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                Collections.singletonList(searchPlaceResponseBody.id),
                Collections.singletonList(searchQueryResponseBody.id)
        );

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        Response response = ApiMethodsDealManipulation.addDeal(requestBody);
        CommonDealManipulationResponseModel responseBody = response.as(CommonDealManipulationResponseModel.class);

        assertThat(responseBody.id).isNotBlank();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.quarantine).isEqualTo(false);
        assertThat(responseBody.searchPlaces).isEqualTo(Collections.singletonList(searchPlaceRequestBody.name));
        assertThat(responseBody.searchQueries).isEqualTo(Collections.singletonList(searchQueryRequestBody.name));
        assertThat(responseBody.dealStatus).isEqualTo(DealStatus.Waiting.name());
        assertThat(responseBody.creatorUserId).isNotBlank();
        assertThat(responseBody.creatorUserName).isEqualTo("Администратор");

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Изменение дела")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение дела")
    @Description("Тест проверяет возможность изменения дела")
    public void testUpdateDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel searchPlaceRequestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
        CommonSearchPlaceResponseModel searchPlaceResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceRequestBody).as(CommonSearchPlaceResponseModel.class);

        AddSearchQueryRequestModel searchQueryRequestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
        CommonSearchQueryResponseModel searchQueryResponseBody = ApiMethodsSearchQuery.addSearchQuery(searchQueryRequestBody).as(CommonSearchQueryResponseModel.class);

        AddDealManipulationRequestModel requestBodyDealCreation = DataGeneratorDealManipulation.getBasicDealManipulationModel(
                Collections.singletonList(searchPlaceResponseBody.id),
                Collections.singletonList(searchQueryResponseBody.id)
        );

        Response responseDealCreation = ApiMethodsDealManipulation.addDeal(requestBodyDealCreation);
        CommonDealManipulationResponseModel responseBodyDealCreation = responseDealCreation.as(CommonDealManipulationResponseModel.class);

        UpdateDealManipulationRequestModel requestBodyDealUpdate = UpdateDealManipulationRequestModel.builder()
                .id(responseBodyDealCreation.id)
                .name(getRandomName())
                .searchPlaces(requestBodyDealCreation.searchPlaces)
                .searchQueries(requestBodyDealCreation.searchQueries)
                .dealStatus(DealStatus.Waiting.name())
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Response responseDealUpdate = ApiMethodsDealManipulation.updateDeal(requestBodyDealUpdate);
        CommonDealManipulationResponseModel responseBodyDealUpdate = responseDealUpdate.as(CommonDealManipulationResponseModel.class);

        assertThat(responseBodyDealUpdate.id).isNotBlank();
        assertThat(responseBodyDealUpdate.name).isEqualTo(requestBodyDealUpdate.name);
        assertThat(responseBodyDealUpdate.quarantine).isEqualTo(false);
        assertThat(responseBodyDealUpdate.searchPlaces).isEqualTo(Collections.singletonList(searchPlaceRequestBody.name));
        assertThat(responseBodyDealUpdate.searchQueries).isEqualTo(Collections.singletonList(searchQueryRequestBody.name));
        assertThat(responseBodyDealUpdate.dealStatus).isEqualTo(DealStatus.Waiting.name());
        assertThat(responseBodyDealUpdate.creatorUserId).isNotBlank();
        assertThat(responseBodyDealUpdate.creatorUserName).isEqualTo("Администратор");

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Удаление дела")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление дела")
    @Description("Тест проверяет возможность удаления дела")
    public void testDeleteDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsDealManipulation.deleteDeal(responseBodyDealCreation.id);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Просмотр информации по делу")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Просмотр информации по делу")
    @Description("Тест проверяет возможность просмотреть информацию по делу")
    public void testGetDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel searchPlaceRequestBody = DataGeneratorSearchPlace.getBasicSearchPlaceModelArmLocal();
        CommonSearchPlaceResponseModel searchPlaceResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceRequestBody).as(CommonSearchPlaceResponseModel.class);

        AddSearchQueryRequestModel searchQueryRequestBody = DataGeneratorSearchQuery.getBasicSearchQueryModel();
        CommonSearchQueryResponseModel searchQueryResponseBody = ApiMethodsSearchQuery.addSearchQuery(searchQueryRequestBody).as(CommonSearchQueryResponseModel.class);

        AddDealManipulationRequestModel requestDealCreationBody = DataGeneratorDealManipulation.getBasicDealManipulationModel(
                Collections.singletonList(searchPlaceResponseBody.id),
                Collections.singletonList(searchQueryResponseBody.id)
        );

        Response responseDealCreation = ApiMethodsDealManipulation.addDeal(requestDealCreationBody);
        CommonDealManipulationResponseModel responseDealCreationBody = responseDealCreation.as(CommonDealManipulationResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        Response responseDealCard = ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);

        DealCardModel responseDealCardBody = responseDealCard.as(DealCardModel.class);

        assertThat(responseDealCardBody.id).isEqualTo(responseDealCreationBody.id);
        assertThat(responseDealCardBody.name).isEqualTo(requestDealCreationBody.name);
        assertThat(responseDealCardBody.quarantine).isEqualTo(requestDealCreationBody.quarantine);
        assertThat(responseDealCardBody.searchPlaces).isEqualTo(Collections.singletonList(searchPlaceRequestBody.name));
        assertThat(responseDealCardBody.searchQueries).isEqualTo(Collections.singletonList(searchQueryRequestBody.name));
        assertThat(responseDealCardBody.dealStatus).isEqualTo(DealStatus.Waiting.name());
        assertThat(responseDealCardBody.creatorUserId).isEqualTo(responseDealCreationBody.creatorUserId);
        assertThat(responseDealCardBody.creatorUserName).isEqualTo(responseDealCreationBody.creatorUserName);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Восстановление удалённого дела")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Восстановление удалённого дела (Служебный метод)")
    @Description("Тест проверяет возможность восстановить удалённое дело (Служебный метод, должен быть удалён)")
    public void testRestoreDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        ApiMethodsDealManipulation.deleteDeal(responseDealCreationBody.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());
        ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        ApiMethodsDealManipulation.restoreDeal(responseDealCreationBody.id);
        ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Закрытие дела")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Закрытие дела")
    @Description("Тест проверяет возможность закрыть (Архивировать) дело")
    public void testCloseDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        Response responseCloseDeal = ApiMethodsDealManipulation.closeDeal(responseDealCreationBody.id);

        CommonDealManipulationResponseModel responseCloseDealBody = responseCloseDeal.as(CommonDealManipulationResponseModel.class);
        assertThat(responseCloseDealBody.dealStatus).isEqualTo(DealStatus.Closed.name());

        Response responseDealCard = ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);
        DealCardModel responseDealCardBody = responseDealCard.as(DealCardModel.class);
        assertThat(responseDealCardBody.dealStatus).isEqualTo(DealStatus.Closed.name());

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Открытие закрытого дела")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Открытие закрытого дела (Служебный метод)")
    @Description("Тест проверяет возможность открыть закрытое дело (Служебный метод, должен быть удалён)")
    public void testUncloseDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        ApiMethodsDealManipulation.closeDeal(responseDealCreationBody.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Response responseUncloseDeal = ApiMethodsDealManipulation.uncloseDeal(responseDealCreationBody.id);
        CommonDealManipulationResponseModel responseUncloseDealBody = responseUncloseDeal.as(CommonDealManipulationResponseModel.class);
        assertThat(responseUncloseDealBody.dealStatus).isEqualTo(DealStatus.Stopped.name());

        Response responseDealCard = ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);
        DealCardModel responseDealCardBody = responseDealCard.as(DealCardModel.class);
        assertThat(responseDealCardBody.dealStatus).isEqualTo(DealStatus.Stopped.name());

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Остановка дела")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Остановка дела")
    @Description("Тест проверяет возможность остановить дело")
    public void testStopDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Response responseStopDeal = ApiMethodsDealManipulation.stopDeal(responseDealCreationBody.id);
        CommonDealManipulationResponseModel responseStopDealBody = responseStopDeal.as(CommonDealManipulationResponseModel.class);
        assertThat(responseStopDealBody.dealStatus).isEqualTo(DealStatus.Stopped.name());

        Response responseDealCard = ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);
        DealCardModel responseDealCardBody = responseDealCard.as(DealCardModel.class);
        assertThat(responseDealCardBody.dealStatus).isEqualTo(DealStatus.Stopped.name());

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Запуск дела")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Запуск дела")
    @Description("Тест проверяет возможность запустить дело")
    public void testStartDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        CommonDealManipulationResponseModel responseStopDealBody = ApiMethodsDealManipulation.startDeal(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);

        assertThat(responseStopDealBody.dealStatus).isEqualTo(DealStatus.Running.name());

        Response responseDealCard = ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);
        DealCardModel responseDealCardBody = responseDealCard.as(DealCardModel.class);
        assertThat(responseDealCardBody.dealStatus).isEqualTo(DealStatus.Running.name());

    }


    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Получение списка дел")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка дел")
    @Description("Тест проверяет возможность получения списка дел")
    public void testGetDealList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        Response response = ApiMethodsDealManipulation.getDealManipulationList();

        List<CommonDealManipulationResponseModel> responseBody = response.jsonPath().getList("", CommonDealManipulationResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска по протоколу oData")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData")
    public void testGetDealListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        Response response = ApiMethodsDealManipulation.getDealManipulationListOData();

        List<CommonDealManipulationResponseModel> responseBody = response.jsonPath().getList("value", CommonDealManipulationResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

}
