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
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - DealManipulation")
public class DealManipulationExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Проверка заполнения полей Deal - DealManipulation в теле ответа при создании")
    class CheckDealManipulationCreationResponseFields{

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает валидный id")
        @Description("Тест проверяет, что при создании дела возвращается валидный id")
        public void testAddDealReturnsId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(isValidUUID(responseBody.id)).isTrue();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает name")
        @Description("Тест проверяет, что при создании дела возвращается переданный name")
        public void testAddDealReturnsName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.name).isEqualTo(requestBody.name);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает name")
        @Description("Тест проверяет, что при создании дела возвращается dealPriority = Medium по умолчанию")
        public void testAddDealReturnsDefaultDealPriority(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(responseBody.dealPriority).isEqualTo(DealPriority.Medium.name());
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает name")
        @Description("Тест проверяет, что при создании дела возвращается quarantine = false по умолчанию")
        public void testAddDealReturnsDefaultQuarantine(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(responseBody.quarantine).isFalse();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает metadataFilterId")
        @Description("Тест проверяет, что при создании дела возвращается metadataFilterId")
        public void testAddDealReturnsFileTypes(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonMetadataFilterResponseBody metadataFilter = DataGeneratorMetadataFilter.createBasicMetadataFilter();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();
            requestBody.metadataFilterId = metadataFilter.id;

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.metadataFilter).usingRecursiveComparison().isEqualTo(metadataFilter);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает searchPlaces")
        @Description("Тест проверяет, что при создании дела возвращается searchPlaces")
        public void testAddDealReturnsSearchPlaces(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.searchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(searchPlaceModel));
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает classifySearchPlaces")
        @Description("Тест проверяет, что при создании дела возвращается classifySearchPlaces")
        public void testAddDealReturnsClassifySearchPlaces(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel classifySearchPlace = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();
            requestBody.classifySearchPlaces = Collections.singletonList(classifySearchPlace.id);

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.classifySearchPlaces).usingRecursiveComparison().isEqualTo(Collections.singletonList(classifySearchPlace));
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает searchPlaceGroups")
        @Description("Тест проверяет, что при создании дела возвращается searchPlaceGroups")
        public void testAddDealReturnsSearchPlaceGroups(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel searchPlaceGroupBody = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();
            requestBody.searchPlaceGroups = Collections.singletonList(searchPlaceGroupBody.id);

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.searchPlaceGroups).usingRecursiveComparison().isEqualTo(Collections.singletonList(searchPlaceGroupBody));
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает dealSearchQueries")
        @Description("Тест проверяет, что при создании дела возвращается dealSearchQueries")
        public void testAddDealReturnsDealSearchQueries(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceModel = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
            CommonSearchQueryResponseModel searchQueryModel = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(searchPlaceModel.id, searchQueryModel.id);

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.dealSearchQueries).usingRecursiveComparison().isEqualTo(Collections.singletonList(
                    new DealSearchQueryModel(
                            true,
                            searchQueryModel.id,
                            searchQueryModel.name
                    )
            ));
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает progressInfo = null")
        @Description("Тест проверяет, что при создании дела возвращается progressInfo = null")
        public void testAddDealReturnsNullProgressInfo(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(responseBody.progressInfo).isNull();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает name")
        @Description("Тест проверяет, что при создании дела возвращается dealStatus = Waiting по умолчанию")
        public void testAddDealReturnsDefaultDealStatus(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(responseBody.dealStatus).isEqualTo(DealStatus.Waiting.name());
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает excludes")
        @Description("Тест проверяет, что при создании дела возвращается excludes")
        public void testAddDealReturnsExcludes(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            List<String> excludes = Collections.singletonList(getRandomName());

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();
            requestBody.excludes = excludes;

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.excludes).isEqualTo(excludes);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает classifierDealData.needClassify = null по умолчанию")
        @Description("Тест проверяет, что при создании дела возвращается classifierDealData.needClassify = null по умолчанию")
        public void testAddDealReturnsDefaultClassifierDealDataNeedClassify(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.classifierDealData.needClassify).isFalse();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает classifierDealData.needClassify")
        @Description("Тест проверяет, что при создании дела возвращается searchMask")
        public void testAddDealReturnsClassifierDealDataNeedClassify(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ClassifierDealData classifierDealData = new ClassifierDealData(true, null);

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();
            requestBody.classifierDealData = classifierDealData;

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.classifierDealData.needClassify).isEqualTo(classifierDealData.needClassify);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает classifierDealData.classifierProfileId")
        @Description("Тест проверяет, что при создании дела возвращается classifierProfileId")
        public void testAddDealReturnsClassifierDealDataClassifierProfileId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            ClassifierDealData classifierDealData = new ClassifierDealData(true, DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters().id);

            AddDealManipulationRequestModel requestBody = DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters();
            requestBody.classifierDealData = classifierDealData;

            CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.addDeal(requestBody).as(CommonDealManipulationResponseModel.class);

            assertThat(responseBody.classifierDealData.classifierProfileId).isEqualTo(classifierDealData.classifierProfileId);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает createdUtc в валидном формате")
        @Description("Тест проверяет, что при создании дела возвращается createdUtc в валидном формате")
        public void testAddDealReturnsCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(responseBody.createdUtc).matches(dateTimeUTCPattern());
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает creatorUserId")
        @Description("Тест проверяет, что при создании дела возвращается creatorUserId")
        public void testAddDealReturnsCreatorUserId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Дело")
        @Story("Создание дела")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание дела возвращает creatorUserName")
        @Description("Тест проверяет, что при создании дела возвращается creatorUserName")
        public void testAddDealReturnsCreatorUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonDealManipulationResponseModel responseBody = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

            assertThat(responseBody.creatorUserName).isEqualTo("Администратор");
        }

    }

    @Test
    @Epic("Сервис Deal")
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
                .withFilter("contains(tolower(name),'') and searchPlaces/any(searchplaces:searchplaces/id eq 14de1c7b-884f-4739-be13-53b51c0e3283)")
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
                .withFilter("contains(tolower(name),'') and classifierDealData/needClassify eq true")
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
                .withOrderBy("classifierDealData/needClassify asc")
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

    @Test
    @Epic("Сервис Deal")
    @Feature("Дело")
    @Story("Получение дела по ID")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение дела по id для карточки дела")
    @Description("Тест проверяет возможность получить дело по id в path param для карточки дела")
    public void testGetDealManipulationForDealManipulationCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withExpand("dealSearchQueries,searchPlaces,progressInfo,classifySearchPlaces,searchPlaceGroups")
                .build();
                new HashMap<>();

        CommonDealManipulationResponseModel responseBody = ApiMethodsDealManipulation.getDealManipulationByIdPath(responseBodyCreation.id, parameters).as(CommonDealManipulationResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.dealPriority).isEqualTo(responseBodyCreation.dealPriority);
        assertThat(responseBody.quarantine).isEqualTo(responseBodyCreation.quarantine);
        assertThat(responseBody.metadataFilter).isEqualTo(responseBodyCreation.metadataFilter);
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
        assertThat(responseBody.classifierDealData.needClassify).isEqualTo(responseBodyCreation.classifierDealData.needClassify);
        assertThat(responseBody.classifierDealData.classifierProfileId).isEqualTo(responseBodyCreation.classifierDealData.classifierProfileId);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.creatorUserId).isEqualTo(responseBodyCreation.creatorUserId);
        assertThat(responseBody.creatorUserName).isEqualTo(responseBodyCreation.creatorUserName);

    }

}
