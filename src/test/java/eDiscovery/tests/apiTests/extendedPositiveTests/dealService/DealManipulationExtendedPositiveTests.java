package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.data.classifierService.DataGeneratorProfile;
import eDiscovery.data.dealService.*;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.DealPriority;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.*;
import eDiscovery.models.deal.metadataFilter.CommonMetadataFilterResponseBody;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.odata.OdataListResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - DealManipulation: Расширенные позитивные тесты")
public class DealManipulationExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при создании дела с обязательными параметрами")
    class CheckDealManipulationCreationWithAllParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании дела с необязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании дела с необязательными полями")
        public void testAddDealManipulationWithAllParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody(3));

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.dealPriority).isEqualTo(requestBody.dealPriority),
                    () -> assertThat(responseBody.quarantine).isEqualTo(requestBody.quarantine),
                    () -> assertThat(responseBody.metadataFilter).usingRecursiveComparison().isEqualTo(new DealMetadataFilterModel(metadataFilterResponseBody.id, metadataFilterResponseBody.name, metadataFilterResponseBody.deletedUtc)),
                    () -> assertThat(responseBody.searchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchPlaceModel(searchPlaceModel.id, searchPlaceModel.name))),
                    () -> assertThat(responseBody.classifySearchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(new ClassifySearchPlacesModel(classifySearchPlaceResponseBody.id, classifySearchPlaceResponseBody.name))),
                    () -> assertThat(responseBody.searchPlaceGroups).usingRecursiveComparison().isEqualTo(Collections.singletonList(new SearchPlaceGroupsModel(searchPlaceGroupResponseBody.id, searchPlaceGroupResponseBody.name, searchPlaceGroupResponseBody.description))),
                    () -> assertThat(responseBody.dealSearchQueries).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchQueryModel(searchQueryModel.id, searchQueryModel.name, true))),
                    () -> assertThat(responseBody.progressInfo).isNull(),
                    () -> assertThat(responseBody.dealStatus).isEqualTo(DealStatus.Waiting.name()),
                    () -> assertThat(responseBody.excludes).isEqualTo(requestBody.excludes),
                    () -> assertThat(responseBody.needClassify).isEqualTo(requestBody.classifierDealData.needClassify),
                    () -> assertThat(responseBody.classifierProfileId).isEqualTo(requestBody.classifierDealData.classifierProfileId),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(isValidUUID(responseBody.creatorUserId)).isTrue(),
                    () -> assertThat(responseBody.creatorUserName).isEqualTo("Администратор")
            );
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при изменении дела с обязательными параметрами")
    class CheckDealManipulationUpdateWithAllParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Изменение дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении дела с необязательными полями")
        @Description("Тест проверяет поля в теле ответа при изменении дела с необязательными полями")
        public void testUpdateDealManipulationWithAllParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody(3));

            CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            UpdateDealManipulationRequestModel requestBody = new UpdateDealManipulationRequestModel(responseBodyDealCreation);

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            requestBody.name = getRandomName();
            requestBody.searchPlaces = Collections.singletonList(searchPlaceModel.id);
            requestBody.dealSearchQueries = Collections.singletonList(new DealSearchQueryModel(searchQueryModel.id, true));
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.updateDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.dealPriority).isEqualTo(requestBody.dealPriority),
                    () -> assertThat(responseBody.quarantine).isEqualTo(requestBody.quarantine),
                    () -> assertThat(responseBody.metadataFilter).usingRecursiveComparison().isEqualTo(new DealMetadataFilterModel(metadataFilterResponseBody.id, metadataFilterResponseBody.name, metadataFilterResponseBody.deletedUtc)),
                    () -> assertThat(responseBody.searchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchPlaceModel(searchPlaceModel.id, searchPlaceModel.name))),
                    () -> assertThat(responseBody.classifySearchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(new ClassifySearchPlacesModel(classifySearchPlaceResponseBody.id, classifySearchPlaceResponseBody.name))),
                    () -> assertThat(responseBody.searchPlaceGroups).usingRecursiveComparison().isEqualTo(Collections.singletonList(new SearchPlaceGroupsModel(searchPlaceGroupResponseBody.id, searchPlaceGroupResponseBody.name, searchPlaceGroupResponseBody.description))),
                    () -> assertThat(responseBody.dealSearchQueries).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchQueryModel(searchQueryModel.id, searchQueryModel.name, true))),
                    () -> assertThat(responseBody.progressInfo).isNull(),
                    () -> assertThat(responseBody.dealStatus).isEqualTo(DealStatus.Waiting.name()),
                    () -> assertThat(responseBody.excludes).isEqualTo(requestBody.excludes),
                    () -> assertThat(responseBody.needClassify).isEqualTo(requestBody.classifierDealData.needClassify),
                    () -> assertThat(responseBody.classifierProfileId).isEqualTo(requestBody.classifierDealData.classifierProfileId),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(isValidUUID(responseBody.creatorUserId)).isTrue(),
                    () -> assertThat(responseBody.creatorUserName).isEqualTo("Администратор")
            );
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при получении дела с необязательными параметрами из списка дел")
    class CheckGetDealManipulationWithAllParametersFromListOdataResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка дел")
        @Description("Тест проверяет поля в теле ответа при получении списка дел")
        public void testGetDealManipulationWithAllParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel dealManipulationCreationBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            CommonDealManipulationResponseModel dealManipulationBodyFromODataList = ApiMethodsDealManipulation.getDealManipulationListOData().jsonPath().getList("value", CommonDealManipulationResponseModel.class)
                    .stream().filter(dealManipulation -> dealManipulation.id.equals(dealManipulationCreationBody.id))
                    .findFirst().orElse(null);

            assertThat(dealManipulationBodyFromODataList).isNotNull();

            assertAll(
                    () -> assertThat(dealManipulationBodyFromODataList.id).isEqualTo(dealManipulationCreationBody.id),
                    () -> assertThat(dealManipulationBodyFromODataList.name).isEqualTo(dealManipulationCreationBody.name),
                    () -> assertThat(dealManipulationBodyFromODataList.dealPriority).isEqualTo(dealManipulationCreationBody.dealPriority),
                    () -> assertThat(dealManipulationBodyFromODataList.quarantine).isEqualTo(dealManipulationCreationBody.quarantine),
                    () -> assertThat(dealManipulationBodyFromODataList.dealStatus).isEqualTo(dealManipulationCreationBody.dealStatus),
                    () -> assertThat(dealManipulationBodyFromODataList.excludes).isEqualTo(dealManipulationCreationBody.excludes),
                    () -> assertThat(dealManipulationBodyFromODataList.needClassify).isEqualTo(dealManipulationCreationBody.needClassify),
                    () -> assertThat(dealManipulationBodyFromODataList.classifierProfileId).isEqualTo(dealManipulationCreationBody.classifierProfileId),
                    () -> assertThat(dealManipulationBodyFromODataList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(dealManipulationBodyFromODataList.creatorUserId).isEqualTo(dealManipulationCreationBody.creatorUserId),
                    () -> assertThat(dealManipulationBodyFromODataList.creatorUserName).isEqualTo(dealManipulationCreationBody.creatorUserName)
            );

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка дел")
        @Description("Тест проверяет поля в теле ответа при получении списка дел")
        public void testGetDealManipulationWithAllParametersFromListOdataCheckResponseBodyWithExpand(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel dealManipulationCreationBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("metadataFilter, searchPlaces, classifySearchPlaces, searchPlaceGroups, dealSearchQueries, progressInfo")
                    .build();

            CommonDealManipulationResponseModel dealManipulationBodyFromODataList = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class)
                    .stream().filter(dealManipulation -> dealManipulation.id.equals(dealManipulationCreationBody.id))
                    .findFirst().orElse(null);

            assertThat(dealManipulationBodyFromODataList).isNotNull();

            assertAll(
                    () -> assertThat(dealManipulationBodyFromODataList.metadataFilter).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.metadataFilter),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaces).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.searchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.classifySearchPlaces).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.classifySearchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaceGroups).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.searchPlaceGroups),
                    () -> assertThat(dealManipulationBodyFromODataList.dealSearchQueries).isEqualTo(dealManipulationCreationBody.dealSearchQueries),
                    () -> assertThat(dealManipulationBodyFromODataList.progressInfo).isEqualTo(dealManipulationCreationBody.progressInfo)
            );

        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при получении дела с необязательными параметрами по id")
    class CheckGetDealManipulationWithAllParametersByIdResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение дела по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении дела по id")
        @Description("Тест проверяет поля в теле ответа при получении дела по id")
        public void testGetDealManipulationWithAllParametersByIdCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel dealManipulationCreationBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            CommonDealManipulationResponseModel dealManipulationBodyFromODataList = ApiMethodsDealManipulation.getDealManipulationByIdPath(dealManipulationCreationBody.id).as(CommonDealManipulationResponseModel.class);

            assertThat(dealManipulationBodyFromODataList).isNotNull();

            assertAll(
                    () -> assertThat(dealManipulationBodyFromODataList.id).isEqualTo(dealManipulationCreationBody.id),
                    () -> assertThat(dealManipulationBodyFromODataList.name).isEqualTo(dealManipulationCreationBody.name),
                    () -> assertThat(dealManipulationBodyFromODataList.dealPriority).isEqualTo(dealManipulationCreationBody.dealPriority),
                    () -> assertThat(dealManipulationBodyFromODataList.quarantine).isEqualTo(dealManipulationCreationBody.quarantine),
                    () -> assertThat(dealManipulationBodyFromODataList.dealStatus).isEqualTo(dealManipulationCreationBody.dealStatus),
                    () -> assertThat(dealManipulationBodyFromODataList.excludes).isEqualTo(dealManipulationCreationBody.excludes),
                    () -> assertThat(dealManipulationBodyFromODataList.needClassify).isEqualTo(dealManipulationCreationBody.needClassify),
                    () -> assertThat(dealManipulationBodyFromODataList.classifierProfileId).isEqualTo(dealManipulationCreationBody.classifierProfileId),
                    () -> assertThat(dealManipulationBodyFromODataList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(dealManipulationBodyFromODataList.creatorUserId).isEqualTo(dealManipulationCreationBody.creatorUserId),
                    () -> assertThat(dealManipulationBodyFromODataList.creatorUserName).isEqualTo(dealManipulationCreationBody.creatorUserName)
            );

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение дела по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении дела по id")
        @Description("Тест проверяет поля в теле ответа при получении дела по id")
        public void testGetDealManipulationWithAllParametersByIdCheckResponseBodyWithExpand(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel dealManipulationCreationBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("metadataFilter, searchPlaces, classifySearchPlaces, searchPlaceGroups, dealSearchQueries, progressInfo")
                    .build();

            CommonDealManipulationResponseModel dealManipulationBodyFromODataList = ApiMethodsDealManipulation.getDealManipulationByIdPath(dealManipulationCreationBody.id, parameters).as(CommonDealManipulationResponseModel.class);

            assertThat(dealManipulationBodyFromODataList).isNotNull();

            assertAll(
                    () -> assertThat(dealManipulationBodyFromODataList.metadataFilter).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.metadataFilter),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaces).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.searchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.classifySearchPlaces).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.classifySearchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaceGroups).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.searchPlaceGroups),
                    () -> assertThat(dealManipulationBodyFromODataList.dealSearchQueries).isEqualTo(dealManipulationCreationBody.dealSearchQueries),
                    () -> assertThat(dealManipulationBodyFromODataList.progressInfo).isEqualTo(dealManipulationCreationBody.progressInfo)
            );

        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка полей ответа при получении карточки дела с необязательными параметрами")
    class CheckGetDealManipulationCardWithAllParametersInDealManipulationCheckResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Просмотр карточки дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Просмотр карточки дела через метод /card")
        @Description("Тест проверяет возможность просмотреть карточку по делу")
        public void testGetDealCardResponseBodyWithAllParameters(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel classifySearchPlaceResponseBody = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchPlaceGroupResponseModel searchPlaceGroupResponseBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();
            CommonMetadataFilterResponseBody metadataFilterResponseBody = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlaceResponseBody.id);
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupResponseBody.id);
            requestBody.excludes = Collections.singletonList("C:\\");
            requestBody.dealPriority = DealPriority.High.name();
            requestBody.metadataFilterId = metadataFilterResponseBody.id;
            requestBody.classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            CommonDealManipulationResponseModel responseBodyDealCreation = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            DealCardModel responseDealCardBody = ApiMethodsDealManipulation.getDealCard(responseBodyDealCreation.id).as(DealCardModel.class);

            assertAll(
                    () -> assertThat(responseDealCardBody.id).isEqualTo(responseBodyDealCreation.id),
                    () -> assertThat(responseDealCardBody.name).isEqualTo(responseBodyDealCreation.name),
                    () -> assertThat(responseDealCardBody.dealPriority).isEqualTo(responseBodyDealCreation.dealPriority),
                    () -> assertThat(responseDealCardBody.quarantine).isEqualTo(responseBodyDealCreation.quarantine),
                    () -> assertThat(responseDealCardBody.metadataFilter).usingRecursiveComparison().isEqualTo(responseBodyDealCreation.metadataFilter),
                    () -> assertThat(responseDealCardBody.searchPlaces).usingRecursiveComparison().isEqualTo(responseBodyDealCreation.searchPlaces),
                    () -> assertThat(responseDealCardBody.classifySearchPlaces).usingRecursiveComparison().isEqualTo(responseBodyDealCreation.classifySearchPlaces),
                    () -> assertThat(responseDealCardBody.searchPlaceGroups).usingRecursiveComparison().isEqualTo(responseBodyDealCreation.searchPlaceGroups),
                    () -> assertThat(responseDealCardBody.dealSearchQueries).usingRecursiveComparison().isEqualTo(responseBodyDealCreation.dealSearchQueries),
                    () -> assertThat(responseDealCardBody.progressInfo).isEqualTo(responseBodyDealCreation.progressInfo),
                    () -> assertThat(responseDealCardBody.dealStatus).isEqualTo(responseBodyDealCreation.dealStatus),
                    () -> assertThat(responseDealCardBody.excludes).isEqualTo(responseBodyDealCreation.excludes),
                    () -> assertThat(responseDealCardBody.needClassify).isEqualTo(responseBodyDealCreation.needClassify),
                    () -> assertThat(responseDealCardBody.classifierProfileId).isEqualTo(responseBodyDealCreation.classifierProfileId),
                    () -> assertThat(responseDealCardBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseDealCardBody.creatorUserId).isEqualTo(responseBodyDealCreation.creatorUserId),
                    () -> assertThat(responseDealCardBody.creatorUserName).isEqualTo(responseBodyDealCreation.creatorUserName)
            );

        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка odata параметров при получении списка дел")
    class CheckGetDealManipulationListOdataParameters{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по названию дела")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по названию дела")
        public void testGetDealManipulationListWithFilterName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("contains(name, '')")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по дате создания дела")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по дате создания дела")
        public void testGetDealManipulationListWithFilterCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("createdUtc ge 2024-12-31T22:00:00.000Z and createdUtc le 2025-01-12T21:59:59.999Z")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по месту поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по месту поиска")
        public void testGetDealManipulationListWithFilterSearchPlace(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("searchPlaces/any(searchplaces:((searchplaces/id eq %s)))", responseSearchPlaceCreation.id))
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по группе мест поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по группе мест поиска")
        public void testGetDealManipulationListWithFilterSearchPlaceGroup(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("searchPlaceGroups/any(searchplacegroups:((searchplacegroups/id eq %s)))", responseSearchPlaceGroupCreation.id))
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по поисковому запросу")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по поисковому запросу")
        public void testGetDealManipulationListWithFilterSearchQuery(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("dealSearchQueries/any(dealsearchqueries:((dealsearchqueries/id eq %s)))", responseSearchQueryCreation.id))
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по статусу категоризации")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по статусу категоризации")
        public void testGetDealManipulationListWithFilterNeedClassify(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("needClassify eq false")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по статусу автокарантина")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по статусу автокарантина")
        public void testGetDealManipulationListWithFilterQuarantine(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("quarantine eq false")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по автору дела")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по автору дела")
        public void testGetDealManipulationListWithFilterCreatorUserId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("creatorUserId eq 8dd105b2-7e41-40c4-8700-022403dfcdc6")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с фильтрацией по статусу дела")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по статусу дела")
        public void testGetDealManipulationListWithFilterDealStatus(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("dealStatus eq '%s'", DealStatus.Waiting.name()))
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с сортировкой по дате создания")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по дате создания")
        public void testGetDealManipulationListWithSortingCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("createdUtc desc")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с сортировкой по названию дела")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по названию дела")
        public void testGetDealManipulationListWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("name asc")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с сортировкой по статусу категоризации дела")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по статусу категоризации дела")
        public void testGetDealManipulationListWithSortingNeedClassify(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("needClassify asc")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с сортировкой по статусу автокарантина")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по статусу автокарантина")
        public void testGetDealManipulationListWithSortingQuarantine(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("quarantine asc")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Issue("ED-885")
        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с сортировкой по автору дела")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по автору дела")
        public void testGetDealManipulationListWithSortingCreatorUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("creatorUserName asc")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Issue("ED-885")
        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с сортировкой по статусу дела")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по статусу дела")
        public void testGetDealManipulationListWithSortingDealStatus(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("dealStatus asc")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(requestParameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с подсчётом количества результатов")
        @Description("Тест проверяет возможность получения списка дел с подсчётом количества результатов")
        @Test
        public void testGetDealManipulationListWithCount(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withCount(true)
                    .build();

            Response response = ApiMethodsDealManipulation.getDealManipulationListOData(parameters);

            OdataListResponseModel responseListWithCount = response.as(OdataListResponseModel.class);
            List<CommonDealManipulationResponseModel> responseElements = response.jsonPath().getList("value", CommonDealManipulationResponseModel.class);

            assertThat(Integer.parseInt(responseListWithCount.odataCount)).isEqualTo(responseElements.size());
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с ограничением количества результатов = 100")
        @Description("Тест проверяет возможность получения списка дел с ограничением количества результатов = 100")
        @Test
        public void testGetDealManipulationListWithTop100(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withTop(100)
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с пропуском первых 10 результатов")
        @Description("Тест проверяет возможность получения списка дел с пропуском первых 10 результатов")
        @Test
        public void testGetDealManipulationListWithSkip10(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withSkip(10)
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с экспандом поля metadataFilter")
        @Description("Тест проверяет возможность получения списка дел с экспандом поля metadataFilter")
        @Test
        public void testGetDealManipulationListWithExpandMetadataFilter(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("metadataFilter")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с экспандом поля searchPlaces")
        @Description("Тест проверяет возможность получения списка дел с экспандом поля searchPlaces")
        @Test
        public void testGetDealManipulationListWithExpandSearchPlaces(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("searchPlaces")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с экспандом поля classifySearchPlaces")
        @Description("Тест проверяет возможность получения списка дел с экспандом поля classifySearchPlaces")
        @Test
        public void testGetDealManipulationListWithExpandClassifySearchPlaces(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("classifySearchPlaces")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с экспандом поля searchPlaceGroups")
        @Description("Тест проверяет возможность получения списка дел с экспандом поля searchPlaceGroups")
        @Test
        public void testGetDealManipulationListWithExpandSearchPlaceGroups(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("searchPlaceGroups")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с экспандом поля dealSearchQueries")
        @Description("Тест проверяет возможность получения списка дел с экспандом поля dealSearchQueries")
        @Test
        public void testGetDealManipulationListWithExpandDealSearchQueries(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("dealSearchQueries")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел с экспандом поля progressInfo")
        @Description("Тест проверяет возможность получения списка дел с экспандом поля progressInfo")
        @Test
        public void testGetDealManipulationListWithExpandProgressInfo(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("progressInfo")
                    .build();

            ApiMethodsDealManipulation.getDealManipulationListOData(parameters);
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка работы методов, используемых на UI")
    class CheckDealManipulationUIMethods{

        @Test
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел")
        public void testGetDealListODataForDealManipulationList(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);
            assertThat(responseBody).isNotEmpty();
            assertThat(responseBody.get(0)).isNotNull();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по периоду (дате создания)")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по периоду (дате создания)")
        public void testGetDealListODataForDealManipulationListWithFilterCreationDate(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and createdUtc ge 2024-12-01T22:00:00.000Z and createdUtc le 2024-12-06T21:59:59.999Z")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по месту поиска")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по месту поиска")
        public void testGetDealListODataForDealManipulationListWithFilterSearchPlace(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and searchPlaces/any(searchplaces:((searchplaces/id eq 14de1c7b-884f-4739-be13-53b51c0e3283)))")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по нескольким местам поиска")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по нескольким местам поиска")
        public void testGetDealListODataForDealManipulationListWithFilterTwoSearchPlaces(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and searchPlaces/any(searchplaces:((searchplaces/id eq 0959d385-0097-41a9-8005-4546e03f9326) or (searchplaces/id eq 14de1c7b-884f-4739-be13-53b51c0e3283)))")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по группе мест поиска")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по группе мест поиска")
        public void testGetDealListODataForDealManipulationListWithFilterSearchPlaceGroup(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and searchPlaceGroups/any(searchplacegroups:((searchplacegroups/id eq 0cb34dd4-420b-43ed-9ae8-33e1384a0d88)))")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по нескольким группам мест поиска")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по нескольким группам мест поиска")
        public void testGetDealListODataForDealManipulationListWithFilterTwoSearchPlaceGroups(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and searchPlaceGroups/any(searchplacegroups:((searchplacegroups/id eq 0cb34dd4-420b-43ed-9ae8-33e1384a0d88) or (searchplacegroups/id eq 17840b2c-a4ab-4c0a-8792-5adda0037c16)))")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по поисковому запросу")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по поисковому запросу")
        public void testGetDealListODataForDealManipulationListWithFilterSearchQuery(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and dealSearchQueries/any(dealsearchqueries:((dealsearchqueries/id eq 1a6450a3-b6dd-4a7c-b66b-ce657a91f747)))")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по нескольким поисковым запросам")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по нескольким поисковым запросам")
        public void testGetDealListODataForDealManipulationListWithFilterTwoSearchQueries(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and dealSearchQueries/any(dealsearchqueries:((dealsearchqueries/id eq 1a6450a3-b6dd-4a7c-b66b-ce657a91f747) or (dealsearchqueries/id eq 625bf1fd-dea7-45e2-b377-48160509154d)))")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по статусу категоризации")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по статусу категоризации")
        public void testGetDealListODataForDealManipulationListWithFilterNeedClassify(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and needClassify eq true")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по статусу автокарантина")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по статусу автокарантина")
        public void testGetDealListODataForDealManipulationListWithFilterQuarantine(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and quarantine eq true")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по автору дела")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по автору дела")
        public void testGetDealListODataForDealManipulationListWithFilterCreatorUserId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'') and (creatorUserId eq 8dd105b2-7e41-40c4-8700-022403dfcdc6)")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @ParameterizedTest
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с фильтром по статусу дела")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с фильтром по статусу дела")
        @MethodSource("eDiscovery.helpers.enums.DealStatus#getValidDealStatuses")
        public void testGetDealListODataForDealManipulationListWithFilterDealStatus(DealStatus dealStatus){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(tolower(name),'') and dealStatus eq '%s'", dealStatus.name()))
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с сортировкой по Названию")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с сортировкой по Названию")
        public void testGetDealListODataForDealManipulationListWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("name asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с сортировкой по Категоризации")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с сортировкой по Категоризации")
        public void testGetDealListODataForDealManipulationListWithSortingNeedClassify(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("needClassify asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с сортировкой по Автокарантину")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с сортировкой по Автокарантину")
        public void testGetDealListODataForDealManipulationListWithSortingQuarantine(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("quarantine asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Issue("ED-885")
        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с сортировкой по Автору")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с сортировкой по Автору")
        public void testGetDealListODataForDealManipulationListWithSortingCreatorUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("creatorUserName asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с сортировкой по Дате (Дата создания)")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с сортировкой по Дате (Дата создания)")
        public void testGetDealListODataForDealManipulationListWithSortingCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("createdUtc asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Место поиска")
        @Story("Получение списка дел")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка дел по протоколу oData для страницы списка дел с сортировкой по Статусу дела")
        @Description("Тест проверяет возможность получения списка дел по протоколу oData для страницы списка дел с сортировкой по Статусу дела")
        public void testGetDealListODataForDealManipulationListWithSortingDealStatus(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withExpand("dealSearchQueries,searchPlaces")
                    .withOrderBy("dealStatus asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .withIncludeDeleted(true)
                    .build();

            List<CommonDealManipulationResponseModel> responseBody = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);

        }

    }

}


