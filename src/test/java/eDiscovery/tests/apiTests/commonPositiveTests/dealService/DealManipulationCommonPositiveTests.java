package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.enums.DealPriority;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.*;
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
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - DealManipulation")
public class DealManipulationCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Создание дела")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание дела")
    @Description("Тест проверяет возможность создания дела")
    public void testAddDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Изменение дела")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение дела")
    @Description("Тест проверяет возможность изменения дела")
    public void testUpdateDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddSearchPlaceRequestModel searchPlaceRequestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
        CommonSearchPlaceResponseModel searchPlaceResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceRequestBody).as(CommonSearchPlaceResponseModel.class);

        AddSearchQueryRequestModel searchQueryRequestBody = DataGeneratorSearchQuery.getSearchQueryModelWithOnlyRequiredParameters();
        CommonSearchQueryResponseModel searchQueryResponseBody = ApiMethodsSearchQuery.addSearchQuery(searchQueryRequestBody).as(CommonSearchQueryResponseModel.class);

        AddDealManipulationRequestModel requestBodyDealCreation = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                searchPlaceResponseBody.id,
                searchQueryResponseBody.id
        );

        Response responseDealCreation = ApiMethodsDealManipulation.addDeal(requestBodyDealCreation);
        CommonDealManipulationResponseModel responseBodyDealCreation = responseDealCreation.as(CommonDealManipulationResponseModel.class);

        UpdateDealManipulationRequestModel requestBodyDealUpdate = UpdateDealManipulationRequestModel.builder()
                .id(responseBodyDealCreation.id)
                .name(getRandomName())
                .searchPlaces(requestBodyDealCreation.searchPlaces)
                .dealSearchQueries(requestBodyDealCreation.dealSearchQueries)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealUpdate = ApiMethodsDealManipulation.updateDeal(requestBodyDealUpdate).as(CommonDealManipulationResponseModel.class);

        assertThat(responseBodyDealUpdate.id).isEqualTo(responseBodyDealCreation.id);
        assertThat(responseBodyDealUpdate.name).isEqualTo(requestBodyDealUpdate.name);
        assertThat(responseBodyDealUpdate.dealPriority).isEqualTo(DealPriority.Medium.name());
        assertThat(responseBodyDealUpdate.quarantine).isFalse();
        assertThat(responseBodyDealUpdate.fileTypes).hasSize(0);
        assertThat(responseBodyDealUpdate.searchPlaces).usingRecursiveComparison().isEqualTo(
                Collections.singletonList(
                        new DealSearchPlaceModel(searchPlaceResponseBody.id, searchPlaceResponseBody.name)
                )
        );
        assertThat(responseBodyDealUpdate.classifySearchPlaces).hasSize(0);
        assertThat(responseBodyDealUpdate.searchPlaceGroups).hasSize(0);
        assertThat(responseBodyDealUpdate.dealSearchQueries).isEqualTo(
                Collections.singletonList(
                        new DealSearchQueryModel(
                                requestBodyDealCreation.dealSearchQueries.get(0).isActive,
                                requestBodyDealCreation.dealSearchQueries.get(0).id,
                                searchQueryRequestBody.name
                        )
                )
        );
        assertThat(responseBodyDealUpdate.progressInfo).isNull();
        assertThat(responseBodyDealUpdate.dealStatus).isEqualTo(DealStatus.Waiting.name());
        assertThat(responseBodyDealUpdate.excludes).hasSize(0);
        assertThat(responseBodyDealUpdate.searchMask).isEqualTo(responseBodyDealCreation.searchMask);
        assertThat(responseBodyDealUpdate.classifierDealData.needClassify).isEqualTo(responseBodyDealCreation.classifierDealData.needClassify);
        assertThat(responseBodyDealUpdate.classifierDealData.classifierProfileId).isEqualTo(responseBodyDealCreation.classifierDealData.classifierProfileId);
        assertThat(responseBodyDealUpdate.createdUtc).matches(dateTimeUTCPattern());
        assertThat(responseBodyDealUpdate.creatorUserId).isEqualTo(responseBodyDealCreation.creatorUserId);
        assertThat(responseBodyDealUpdate.creatorUserName).isEqualTo(responseBodyDealCreation.creatorUserName);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Удаление дела")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Удаление дела")
    @Description("Тест проверяет возможность удаления дела")
    public void testDeleteDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsDealManipulation.deleteDeal(responseBodyDealCreation.id);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Просмотр информации по делу")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Просмотр информации по делу")
    @Description("Тест проверяет возможность просмотреть информацию по делу")
    public void testGetDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        AddSearchPlaceRequestModel searchPlaceRequestBody = DataGeneratorSearchPlace.getBasicSearchPlaceModelArmLocal();
        CommonSearchPlaceResponseModel searchPlaceResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceRequestBody).as(CommonSearchPlaceResponseModel.class);

        AddSearchQueryRequestModel searchQueryRequestBody = DataGeneratorSearchQuery.getBasicSearchQueryModel();
        CommonSearchQueryResponseModel searchQueryResponseBody = ApiMethodsSearchQuery.addSearchQuery(searchQueryRequestBody).as(CommonSearchQueryResponseModel.class);

        AddDealManipulationRequestModel requestDealCreationBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                searchPlaceResponseBody.id,
                searchQueryResponseBody.id
        );

        CommonDealManipulationResponseModel responseDealCreationBody = ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        Response responseDealCard = ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);

        DealCardModel responseDealCardBody = responseDealCard.as(DealCardModel.class);

        assertThat(responseDealCardBody.id).isEqualTo(responseDealCreationBody.id);
        assertThat(responseDealCardBody.name).isEqualTo(responseDealCreationBody.name);
        assertThat(responseDealCardBody.dealPriority).isEqualTo(DealPriority.Medium.name());
        assertThat(responseDealCardBody.quarantine).isEqualTo(responseDealCreationBody.quarantine);
        assertThat(responseDealCardBody.fileTypes).hasSize(0);
        assertThat(responseDealCardBody.searchPlaces).usingRecursiveComparison().isEqualTo(
                Collections.singletonList(
                        new DealSearchPlaceModel(searchPlaceResponseBody.id, searchPlaceResponseBody.name)
                )
        );
        assertThat(responseDealCardBody.classifySearchPlaces).hasSize(0);
        assertThat(responseDealCardBody.searchPlaceGroups).hasSize(0);
        assertThat(responseDealCardBody.dealSearchQueries).isEqualTo(
                Collections.singletonList(
                        new DealSearchQueryModel(
                                responseDealCreationBody.dealSearchQueries.get(0).isActive,
                                responseDealCreationBody.dealSearchQueries.get(0).id,
                                searchQueryRequestBody.name
                        )
                )
        );
        assertThat(responseDealCardBody.progressInfo).isNull();
        assertThat(responseDealCardBody.dealStatus).isEqualTo(DealStatus.Waiting.name());
        assertThat(responseDealCardBody.excludes).hasSize(0);
        assertThat(responseDealCardBody.searchMask).isEqualTo(responseDealCreationBody.searchMask);
        assertThat(responseDealCardBody.classifierDealData.needClassify).isEqualTo(responseDealCreationBody.classifierDealData.needClassify);
        assertThat(responseDealCardBody.classifierDealData.classifierProfileId).isEqualTo(responseDealCreationBody.classifierDealData.classifierProfileId);
        assertThat(responseDealCardBody.createdUtc).matches(dateTimeUTCPattern());
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Остановка дела")
    @Description("Тест проверяет возможность остановить дело")
    public void testStopDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Запуск дела")
    @Description("Тест проверяет возможность запустить дело")
    public void testStartDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка дел")
    @Description("Тест проверяет возможность получения списка дел")
    public void testGetDealList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody(5));

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        Response response = ApiMethodsDealManipulation.getDealManipulationList();

        List<CommonDealManipulationResponseModel> responseBody = response.jsonPath().getList("", CommonDealManipulationResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка дел")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка мест поиска по протоколу oData")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData")
    public void testGetDealListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        Response response = ApiMethodsDealManipulation.getDealManipulationListOData();

        List<CommonDealManipulationResponseModel> responseBody = response.jsonPath().getList("value", CommonDealManipulationResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Получение дела по ID")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение дела по id")
    @Description("Тест проверяет возможность получить дело по id в скобках")
    public void testGetDealManipulationById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.getDealManipulationById(responseBodyCreation.id).as(CommonDealManipulationResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.dealPriority).isEqualTo(responseBodyCreation.dealPriority);
        assertThat(responseBody.quarantine).isEqualTo(responseBodyCreation.quarantine);
        assertThat(responseBody.progressInfo).isEqualTo(responseBodyCreation.progressInfo);
        assertThat(responseBody.dealStatus).isEqualTo(responseBodyCreation.dealStatus);
        assertThat(responseBody.excludes).isEqualTo(responseBodyCreation.excludes);
        assertThat(responseBody.searchMask).isEqualTo(responseBodyCreation.searchMask);
        assertThat(responseBody.classifierDealData.needClassify).isEqualTo(responseBodyCreation.classifierDealData.needClassify);
        assertThat(responseBody.classifierDealData.classifierProfileId).isEqualTo(responseBodyCreation.classifierDealData.classifierProfileId);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.creatorUserId).isEqualTo(responseBodyCreation.creatorUserId);
        assertThat(responseBody.creatorUserName).isEqualTo(responseBodyCreation.creatorUserName);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Получение дела по ID")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение дела по id")
    @Description("Тест проверяет возможность получить дело по id в path param")
    public void testGetDealManipulationByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseBodyCreation.id).as(CommonDealManipulationResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.dealPriority).isEqualTo(responseBodyCreation.dealPriority);
        assertThat(responseBody.quarantine).isEqualTo(responseBodyCreation.quarantine);
        assertThat(responseBody.progressInfo).isEqualTo(responseBodyCreation.progressInfo);
        assertThat(responseBody.dealStatus).isEqualTo(responseBodyCreation.dealStatus);
        assertThat(responseBody.excludes).isEqualTo(responseBodyCreation.excludes);
        assertThat(responseBody.searchMask).isEqualTo(responseBodyCreation.searchMask);
        assertThat(responseBody.classifierDealData.needClassify).isEqualTo(responseBodyCreation.classifierDealData.needClassify);
        assertThat(responseBody.classifierDealData.classifierProfileId).isEqualTo(responseBodyCreation.classifierDealData.classifierProfileId);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.creatorUserId).isEqualTo(responseBodyCreation.creatorUserId);
        assertThat(responseBody.creatorUserName).isEqualTo(responseBodyCreation.creatorUserName);

    }

}
