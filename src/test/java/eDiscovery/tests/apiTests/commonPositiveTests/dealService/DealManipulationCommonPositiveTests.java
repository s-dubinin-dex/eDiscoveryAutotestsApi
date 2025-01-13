package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.data.dealService.*;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.DealPriority;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.*;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - DealManipulation: Основные позитивные тесты")
public class DealManipulationCommonPositiveTests extends TestBase {

    @Nested
    @Tag("smoke")
    @DisplayName("Deal - DealManipulation: Базовая проверка CRUD")
    class CheckBaseCRUDDealDealManipulation {

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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            UpdateDealManipulationRequestModel requestBodyDealUpdate = new UpdateDealManipulationRequestModel(responseBodyDealCreation);

            ApiMethodsDealManipulation.updateDeal(requestBodyDealUpdate);
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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.getDealCard(responseDealCreationBody.id);
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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
            ApiMethodsDealManipulation.restoreDeal(responseDealCreationBody.id);
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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.closeDeal(responseDealCreationBody.id);
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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.uncloseDeal(responseDealCreationBody.id);
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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.stopDeal(responseDealCreationBody.id);
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
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.startDeal(responseDealCreationBody.id);
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

            ApiMethodsDealManipulation.getDealManipulationList();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка мест поиска по протоколу oData")
        @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData")
        public void testGetDealListOData(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ApiMethodsDealManipulation.getDealManipulationListOData();
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

            ApiMethodsDealManipulation.getDealManipulationById(responseBodyCreation.id);
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

            ApiMethodsDealManipulation.getDealManipulationByIdPath(responseBodyCreation.id);
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при создании дела с обязательными параметрами")
    class CheckDealManipulationCreationWithOnlyRequiredParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании дела с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании дела с обязательными полями")
        public void testAddDealManipulationWithOnlyRequiredParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.dealPriority).isEqualTo(DealPriority.Medium.name()),
                    () -> assertThat(responseBody.quarantine).isFalse(),
                    () -> assertThat(responseBody.metadataFilter).isNull(),
                    () -> assertThat(responseBody.searchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchPlaceModel(searchPlaceModel.id, searchPlaceModel.name))),
                    () -> assertThat(responseBody.classifySearchPlaces).isEmpty(),
                    () -> assertThat(responseBody.searchPlaceGroups).isEmpty(),
                    () -> assertThat(responseBody.dealSearchQueries).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchQueryModel(searchQueryModel.id, searchQueryModel.name, true))),
                    () -> assertThat(responseBody.progressInfo).isNull(),
                    () -> assertThat(responseBody.dealStatus).isEqualTo(DealStatus.Waiting.name()),
                    () -> assertThat(responseBody.excludes).isEmpty(),
                    () -> assertThat(responseBody.needClassify).isFalse(),
                    () -> assertThat(responseBody.classifierProfileId).isNull(),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(isValidUUID(responseBody.creatorUserId)).isTrue(),
                    () -> assertThat(responseBody.creatorUserName).isEqualTo("Администратор")
            );
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при изменении дела с обязательными параметрами")
    class CheckDealManipulationUpdateWithOnlyRequiredParametersResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Изменение дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при изменении дела с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при изменении дела с обязательными полями")
        public void testUpdateDealManipulationWithOnlyRequiredParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            UpdateDealManipulationRequestModel requestBody = new UpdateDealManipulationRequestModel(responseBodyDealCreation);
            requestBody.name = getRandomName();
            requestBody.searchPlaces = Collections.singletonList(searchPlaceModel.id);
            requestBody.dealSearchQueries = Collections.singletonList(new DealSearchQueryModel(searchQueryModel.id, true));

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.updateDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.dealPriority).isEqualTo(DealPriority.Medium.name()),
                    () -> assertThat(responseBody.quarantine).isFalse(),
                    () -> assertThat(responseBody.metadataFilter).isNull(),
                    () -> assertThat(responseBody.searchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchPlaceModel(searchPlaceModel.id, searchPlaceModel.name))),
                    () -> assertThat(responseBody.classifySearchPlaces).isEmpty(),
                    () -> assertThat(responseBody.searchPlaceGroups).isEmpty(),
                    () -> assertThat(responseBody.dealSearchQueries).usingRecursiveComparison().isEqualTo(Collections.singletonList(new DealSearchQueryModel(searchQueryModel.id, searchQueryModel.name, true))),
                    () -> assertThat(responseBody.progressInfo).isNull(),
                    () -> assertThat(responseBody.dealStatus).isEqualTo(DealStatus.Waiting.name()),
                    () -> assertThat(responseBody.excludes).isEmpty(),
                    () -> assertThat(responseBody.needClassify).isFalse(),
                    () -> assertThat(responseBody.classifierProfileId).isNull(),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(isValidUUID(responseBody.creatorUserId)).isTrue(),
                    () -> assertThat(responseBody.creatorUserName).isEqualTo("Администратор")
            );
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при получении дела с обязательными параметрами из списка дел")
    class CheckGetDealManipulationWithOnlyRequiredParametersFromListOdataResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение списка дел")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка дел")
        @Description("Тест проверяет поля в теле ответа при получении списка дел")
        public void testGetDealManipulationWithOnlyRequiredParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel dealManipulationCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

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
        public void testGetDealManipulationWithOnlyRequiredParametersFromListOdataCheckResponseBodyWithExpand(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel dealManipulationCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("metadataFilter, searchPlaces, classifySearchPlaces, searchPlaceGroups, dealSearchQueries, progressInfo")
                    .build();

            CommonDealManipulationResponseModel dealManipulationBodyFromODataList = ApiMethodsDealManipulation.getDealManipulationListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class)
                    .stream().filter(dealManipulation -> dealManipulation.id.equals(dealManipulationCreationBody.id))
                    .findFirst().orElse(null);

            assertThat(dealManipulationBodyFromODataList).isNotNull();

            assertAll(
                    () -> assertThat(dealManipulationBodyFromODataList.metadataFilter).isEqualTo(dealManipulationCreationBody.metadataFilter),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaces).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.searchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.classifySearchPlaces).isEqualTo(dealManipulationCreationBody.classifySearchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaceGroups).isEqualTo(dealManipulationCreationBody.searchPlaceGroups),
                    () -> assertThat(dealManipulationBodyFromODataList.dealSearchQueries).isEqualTo(dealManipulationCreationBody.dealSearchQueries),
                    () -> assertThat(dealManipulationBodyFromODataList.progressInfo).isEqualTo(dealManipulationCreationBody.progressInfo)
            );

        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка тела ответа при получении дела с обязательными параметрами по id")
    class CheckGetDealManipulationWithOnlyRequiredParametersByIdResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Получение дела по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении дела по id")
        @Description("Тест проверяет поля в теле ответа при получении дела по id")
        public void testGetDealManipulationWithOnlyRequiredParametersByIdCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel dealManipulationCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

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
        public void testGetDealManipulationWithOnlyRequiredParametersByIdCheckResponseBodyWithExpand(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel dealManipulationCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("metadataFilter, searchPlaces, classifySearchPlaces, searchPlaceGroups, dealSearchQueries, progressInfo")
                    .build();

            CommonDealManipulationResponseModel dealManipulationBodyFromODataList = ApiMethodsDealManipulation.getDealManipulationByIdPath(dealManipulationCreationBody.id, parameters).as(CommonDealManipulationResponseModel.class);

            assertThat(dealManipulationBodyFromODataList).isNotNull();

            assertAll(
                    () -> assertThat(dealManipulationBodyFromODataList.metadataFilter).isEqualTo(dealManipulationCreationBody.metadataFilter),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaces).usingRecursiveComparison().isEqualTo(dealManipulationCreationBody.searchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.classifySearchPlaces).isEqualTo(dealManipulationCreationBody.classifySearchPlaces),
                    () -> assertThat(dealManipulationBodyFromODataList.searchPlaceGroups).isEqualTo(dealManipulationCreationBody.searchPlaceGroups),
                    () -> assertThat(dealManipulationBodyFromODataList.dealSearchQueries).isEqualTo(dealManipulationCreationBody.dealSearchQueries),
                    () -> assertThat(dealManipulationBodyFromODataList.progressInfo).isEqualTo(dealManipulationCreationBody.progressInfo)
            );

        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка полей ответа при получении карточки дела")
    class CheckGetDealManipulationCardWithOnlyRequiredParametersInDealManipulationCheckResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Просмотр карточки дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Просмотр карточки дела через метод /card")
        @Description("Тест проверяет возможность просмотреть карточку по делу")
        public void testGetDealCardResponseBodyWithOnlyRequiredParameters(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            AddDealManipulationRequestModel requestBodyDealCreation = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);
            CommonDealManipulationResponseModel responseBodyDealCreation = ApiMethodsDealManipulation.addDeal(requestBodyDealCreation).as(CommonDealManipulationResponseModel.class);

            DealCardModel responseDealCardBody = ApiMethodsDealManipulation.getDealCard(responseBodyDealCreation.id).as(DealCardModel.class);

            assertAll(
                    () -> assertThat(responseDealCardBody.id).isEqualTo(responseBodyDealCreation.id),
                    () -> assertThat(responseDealCardBody.name).isEqualTo(responseBodyDealCreation.name),
                    () -> assertThat(responseDealCardBody.dealPriority).isEqualTo(responseBodyDealCreation.dealPriority),
                    () -> assertThat(responseDealCardBody.quarantine).isEqualTo(responseBodyDealCreation.quarantine),
                    () -> assertThat(responseDealCardBody.metadataFilter).isEqualTo(responseBodyDealCreation.metadataFilter),
                    () -> assertThat(responseDealCardBody.searchPlaces).usingRecursiveComparison().isEqualTo(responseBodyDealCreation.searchPlaces),
                    () -> assertThat(responseDealCardBody.classifySearchPlaces).isEqualTo(responseBodyDealCreation.classifySearchPlaces),
                    () -> assertThat(responseDealCardBody.searchPlaceGroups).isEqualTo(responseBodyDealCreation.searchPlaceGroups),
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
    @DisplayName("Deal - DealManipulation: Проверка восстановления удалённого дела")
    class CheckRestoreDeletedDealManipulation{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Восстановление удалённого дела")
        @Severity(SeverityLevel.TRIVIAL)
        @DisplayName("Восстановление удалённого дела (Служебный метод)")
        @Description("Тест проверяет восстановление удалённого дела (Служебный метод, должен быть удалён)")
        public void testRestoreDealResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsDealManipulation.deleteDeal(responseDealCreationBody.id);


            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());
            ApiMethodsDealManipulation.getDealManipulationByIdPath(responseDealCreationBody.id);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
            ApiMethodsDealManipulation.restoreDeal(responseDealCreationBody.id);

            ApiMethodsDealManipulation.getDealManipulationByIdPath(responseDealCreationBody.id);
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка закрытия дела")
    class CheckCloseDealManipulation{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Закрытие дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Закрытие дела")
        @Description("Тест проверяет возможность закрыть (Архивировать) дело")
        public void testCloseDealResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            CommonDealManipulationResponseModel responseCloseDealBody = ApiMethodsDealManipulation.closeDeal(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);
            CommonDealManipulationResponseModel responseDealBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);

            assertThat(responseCloseDealBody.dealStatus).isEqualTo(DealStatus.Closed.name());
            assertThat(responseDealBody.dealStatus).isEqualTo(DealStatus.Closed.name());
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка открытия закрытого дела")
    class CheckUncloseDealManipulation{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Открытие закрытого дела")
        @Severity(SeverityLevel.TRIVIAL)
        @DisplayName("Открытие закрытого дела (Служебный метод)")
        @Description("Тест проверяет возможность открыть закрытое дело (Служебный метод, должен быть удалён)")
        public void testUncloseDealResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.closeDeal(responseDealCreationBody.id);

            CommonDealManipulationResponseModel responseUncloseDealBody = ApiMethodsDealManipulation.uncloseDeal(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);
            CommonDealManipulationResponseModel responseDealBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);

            assertThat(responseUncloseDealBody.dealStatus).isEqualTo(DealStatus.Stopped.name());
            assertThat(responseDealBody.dealStatus).isEqualTo(DealStatus.Stopped.name());
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка остановки дела")
    class CheckStopDealManipulation{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Остановка дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Остановка дела")
        @Description("Тест проверяет возможность остановить дело")
        public void testStopDealResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            CommonDealManipulationResponseModel responseStopDealBody = ApiMethodsDealManipulation.stopDeal(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);
            CommonDealManipulationResponseModel responseDealBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);

            assertThat(responseStopDealBody.dealStatus).isEqualTo(DealStatus.Stopped.name());
            assertThat(responseDealBody.dealStatus).isEqualTo(DealStatus.Stopped.name());
        }

    }

    @Nested
    @DisplayName("Deal - DealManipulation: Проверка запуска дела")
    class CheckStartDealManipulation{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Запуск дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Запуск дела")
        @Description("Тест проверяет возможность запустить дело")
        public void testStartDealResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseDealCreationBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            CommonDealManipulationResponseModel responseStopDealBody = ApiMethodsDealManipulation.startDeal(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);
            CommonDealManipulationResponseModel responseDealBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseDealCreationBody.id).as(CommonDealManipulationResponseModel.class);

            assertThat(responseStopDealBody.dealStatus).isEqualTo(DealStatus.Running.name());
            assertThat(responseDealBody.dealStatus).isEqualTo(DealStatus.Running.name());
        }

    }

}
