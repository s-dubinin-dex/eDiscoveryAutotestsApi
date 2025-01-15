package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.SearchPlaceParametersModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
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

import java.util.*;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - SearchPlace: Расширенные позитивные тесты")
public class SearchPlaceExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при создании роли с необязательными параметрами")
    class CheckSearchPlaceCreationWithAllParametersCheckResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании места поиска с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании места поиска с обязательными полями")
        public void testAddSearchPlaceWithAllParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri(faker.internet().url())
                    .username(faker.name().username())
                    .password(faker.internet().password())
                    .build();

            AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .excludes(List.of("C:\\", "D:\\"))
                    .parameters(parameters)
                    .build();

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.categoryType).isEqualTo(requestBody.categoryType),
                    () -> assertThat(responseBody.type).isEqualTo(requestBody.type),
                    () -> assertThat(responseBody.parameters).usingRecursiveComparison().isEqualTo(requestBody.parameters),
                    () -> assertThat(responseBody.excludes).isEqualTo(requestBody.excludes),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при изменении роли с необязательными параметрами")
    class CheckSearchPlaceUpdateWithAllParametersCheckResponseBody {

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при создании места поиска с обязательными полями")
        @Description("Тест проверяет поля в теле ответа при создании места поиска с обязательными полями")
        public void testUpdateSearchPlaceWithAllParametersCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri(faker.internet().url())
                    .username(faker.name().username())
                    .password(faker.internet().password())
                    .build();

            UpdateSearchPlaceRequestModel requestBody = UpdateSearchPlaceRequestModel.builder()
                    .id(responseBodySearchPlaceCreation.id)
                    .name(getRandomName())
                    .categoryType(responseBodySearchPlaceCreation.categoryType)
                    .type(SearchPlaceType.NFS.name())
                    .excludes(List.of("C:\\", "D:\\", "Z:\\"))
                    .parameters(parameters)
                    .build();

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.updateSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(requestBody.id),
                    () -> assertThat(responseBody.name).isEqualTo(requestBody.name),
                    () -> assertThat(responseBody.categoryType).isEqualTo(requestBody.categoryType),
                    () -> assertThat(responseBody.type).isEqualTo(requestBody.type),
                    () -> assertThat(responseBody.parameters).usingRecursiveComparison().isEqualTo(requestBody.parameters),
                    () -> assertThat(responseBody.excludes).isEqualTo(requestBody.excludes),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBody.deletedUtc).isNull()
            );
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при получении места поиска с необязательными параметрами из списка мест поиска")
    class CheckGetSearchPlaceWithAllParametersFromListOdataResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении списка мест поиска")
        @Description("Тест проверяет поля в теле ответа при получении списка мест поиска")
        public void testGetSearchPlaceWithAllParametersFromListOdataCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri(faker.internet().url())
                    .username(faker.name().username())
                    .password(faker.internet().password())
                    .build();

            AddSearchPlaceRequestModel searchPlaceCreationRequestBody = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .excludes(List.of("C:\\", "D:\\"))
                    .parameters(parameters)
                    .build();

            CommonSearchPlaceResponseModel searchPlaceCreationResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceCreationRequestBody).as(CommonSearchPlaceResponseModel.class);

            CommonSearchPlaceResponseModel searchPlaceBodyFromODataListResponseBody = ApiMethodsSearchPlace.getSearchPlaceListOData().jsonPath().getList("value", CommonSearchPlaceResponseModel.class)
                            .stream().filter(searchPlace -> searchPlace.id.equals(searchPlaceCreationResponseBody.id))
                            .findFirst().orElse(null);

            assertThat(searchPlaceBodyFromODataListResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.id).isEqualTo(searchPlaceCreationResponseBody.id),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.name).isEqualTo(searchPlaceCreationResponseBody.name),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.categoryType).isEqualTo(searchPlaceCreationResponseBody.categoryType),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.type).isEqualTo(searchPlaceCreationResponseBody.type),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.parameters).usingRecursiveComparison().isEqualTo(searchPlaceCreationResponseBody.parameters),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.excludes).isEqualTo(searchPlaceCreationResponseBody.excludes),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.deletedUtc).isEqualTo(searchPlaceCreationResponseBody.deletedUtc)
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка тела ответа при получении места поиска с необязательными параметрами по id")
    class CheckGetSearchPlaceWithAllParametersByIdResponseBody{

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение места поиска по id")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка полей тела ответа при получении места поиска по id")
        @Description("Тест проверяет поля в теле ответа при получении места поиска по id")
        public void testGetSearchPlaceWithAllParametersByIdCheckResponseBody(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri(faker.internet().url())
                    .username(faker.name().username())
                    .password(faker.internet().password())
                    .build();

            AddSearchPlaceRequestModel searchPlaceCreationRequestBody = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .excludes(List.of("C:\\", "D:\\"))
                    .parameters(parameters)
                    .build();

            CommonSearchPlaceResponseModel searchPlaceCreationResponseBody = ApiMethodsSearchPlace.addSearchPlace(searchPlaceCreationRequestBody).as(CommonSearchPlaceResponseModel.class);

            CommonSearchPlaceResponseModel searchPlaceBodyFromODataListResponseBody = ApiMethodsSearchPlace.getSearchPlaceODataByIdPath(searchPlaceCreationResponseBody.id).as(CommonSearchPlaceResponseModel.class);

            assertThat(searchPlaceBodyFromODataListResponseBody).isNotNull();

            assertAll(
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.id).isEqualTo(searchPlaceCreationResponseBody.id),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.name).isEqualTo(searchPlaceCreationResponseBody.name),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.categoryType).isEqualTo(searchPlaceCreationResponseBody.categoryType),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.type).isEqualTo(searchPlaceCreationResponseBody.type),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.parameters).usingRecursiveComparison().isEqualTo(searchPlaceCreationResponseBody.parameters),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.excludes).isEqualTo(searchPlaceCreationResponseBody.excludes),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(searchPlaceBodyFromODataListResponseBody.deletedUtc).isEqualTo(searchPlaceCreationResponseBody.deletedUtc)
            );

        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка odata параметров при получении списка мест поиска")
    class CheckGetSearchPlaceListOdataParameters{

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с фильтрацией по названию места поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по названию места поиска")
        public void testGetSearchPlaceListWithFilterName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("contains(name, '')")
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка фильтрации по названию места поиска при получении списка мест поиска")
        @Description("Тест проверяет фильтрацию по названию места поиска при получении списка мест поиска")
        public void testGetSearchPlaceListWithFilterNameCheckFiltering(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            String searchPlaceNameForFilter = getRandomName();

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.name = searchPlaceNameForFilter;

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(name, '%s')", searchPlaceNameForFilter))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceFromList = ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters ).jsonPath().getList("value", CommonSearchPlaceResponseModel.class).get(0);

            assertAll(
                    () -> assertThat(responseBodySearchPlaceFromList.id).isEqualTo(responseBodySearchPlaceCreation.id),
                    () -> assertThat(responseBodySearchPlaceFromList.name).isEqualTo(responseBodySearchPlaceCreation.name),
                    () -> assertThat(responseBodySearchPlaceFromList.categoryType).isEqualTo(responseBodySearchPlaceCreation.categoryType),
                    () -> assertThat(responseBodySearchPlaceFromList.type).isEqualTo(responseBodySearchPlaceCreation.type),
                    () -> assertThat(responseBodySearchPlaceFromList.parameters).usingRecursiveComparison().isEqualTo(responseBodySearchPlaceCreation.parameters),
                    () -> assertThat(responseBodySearchPlaceFromList.excludes).isEqualTo(responseBodySearchPlaceCreation.excludes),
                    () -> assertThat(responseBodySearchPlaceFromList.createdUtc).matches(dateTimeCommonPattern()),
                    () -> assertThat(responseBodySearchPlaceFromList.deletedUtc).isEqualTo(responseBodySearchPlaceCreation.deletedUtc)
            );

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с фильтрацией по категории места поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по категории места поиска")
        public void testGetSearchPlaceListWithFilterCategoryType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("categoryType eq '%s'", SearchPlaceCategoryType.FileShare.name()))
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с фильтрацией по типу места поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтрацией по типу места поиска")
        public void testGetSearchPlaceListWithFilterType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("type eq '%s'", SearchPlaceType.SMB.name()))
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с сортировкой по дате создания")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по дате создания")
        public void testGetSearchPlaceListWithSortingCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("createdUtc desc")
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с сортировкой по названию")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по названию")
        public void testGetSearchPlaceListWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("name asc")
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Проверка сортировки по названию при получении списка мест поиска")
        @Description("Тест проверяет сортировку по названию при получении списка мест поиска")
        public void testGetSearchPlaceListWithSortingNameCheckSorting(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            String searchPlaceNameForFilter = getRandomName(10);

            for (int i = 0; i < 3; i++) {
                AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
                requestBody.name = searchPlaceNameForFilter + i;
                ApiMethodsSearchPlace.addSearchPlace(requestBody);
            }

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(name, '%s')", searchPlaceNameForFilter))
                    .withOrderBy("name asc")
                    .build();

            List<CommonSearchPlaceResponseModel> responseBodyWithSorting =
                    ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

            assertThat(responseBodyWithSorting.size()).isEqualTo(3);
            assertThat(responseBodyWithSorting.get(0).name).isEqualTo(searchPlaceNameForFilter + 0);
            assertThat(responseBodyWithSorting.get(1).name).isEqualTo(searchPlaceNameForFilter + 1);
            assertThat(responseBodyWithSorting.get(2).name).isEqualTo(searchPlaceNameForFilter + 2);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с сортировкой по категории места поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по категории места поиска")
        public void testGetSearchPlaceListWithSortingCategoryType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("categoryType asc")
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с сортировкой по типу места поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с сортировкой по типу места поиска")
        public void testGetSearchPlaceListWithSortingType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("type asc")
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с подсчётом количества результатов")
        @Description("Тест проверяет возможность получения списка мест поиска с подсчётом количества результатов")
        @Test
        public void testGetSearchPlaceListWithCount(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withCount(true)
                    .build();

            Response response = ApiMethodsSearchPlace.getSearchPlaceListOData(parameters);

            OdataListResponseModel responseListWithCount = response.as(OdataListResponseModel.class);
            List<CommonSearchPlaceResponseModel> responseElements = response.jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

            assertThat(Integer.parseInt(responseListWithCount.odataCount)).isEqualTo(responseElements.size());
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с ограничением количества результатов = 100")
        @Description("Тест проверяет возможность получения списка мест поиска с ограничением количества результатов = 100")
        @Test
        public void testGetSearchPlaceListWithTop100(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withTop(100)
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с лимитированием количества объектов в результате")
        @Description("Тест проверяет получение списка мест поиска с лимитированием количества объектов в результате")
        @Test
        public void testGetSearchPlaceListODataWithLimit(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            String searchPlaceNameForFilter = getRandomName(10);

            for (int i = 0; i < 3; i++){
                AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
                requestBody.name = searchPlaceNameForFilter + i;
                ApiMethodsSearchPlace.addSearchPlace(requestBody);
            }

            Map<String, String> requestParameters = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(name, '%s')", searchPlaceNameForFilter))
                    .withTop(2)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBodyWithLimiting =
                    ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

            assertThat(responseBodyWithLimiting.size()).isEqualTo(2);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с пропуском первых 10 результатов")
        @Description("Тест проверяет возможность получения списка мест поиска с пропуском первых 10 результатов")
        @Test
        public void testGetSearchPlaceListWithSkip10(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withSkip(10)
                    .build();

            ApiMethodsSearchPlace.getSearchPlaceListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска по протоколу oData с пагинацией результата")
        @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData с пагинацией результата")
        @Test
        public void testGetSearchPlaceListODataWithPagination(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            String searchPlaceNameForFilter = getRandomName(10);

            for (int i = 0; i < 3; i++){
                AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
                requestBody.name = searchPlaceNameForFilter + i;

                ApiMethodsSearchPlace.addSearchPlace(requestBody);
            }

            Map<String, String> requestParameters = OdataParametersBuilder.builder()
                    .withFilter("contains(name, '" + searchPlaceNameForFilter + "')")
                    .withOrderBy("name")
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBodyWithPagination =
                    ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

            assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchPlaceNameForFilter + 0);
            assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchPlaceNameForFilter + 1);
            assertThat(responseBodyWithPagination.get(2).name).isEqualTo(searchPlaceNameForFilter + 2);


            requestParameters.put("$skip", "1");

            responseBodyWithPagination =
                    ApiMethodsSearchPlace.getSearchPlaceListOData(requestParameters)
                            .jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

            assertThat(responseBodyWithPagination.get(0).name).isEqualTo(searchPlaceNameForFilter + 1);
            assertThat(responseBodyWithPagination.get(1).name).isEqualTo(searchPlaceNameForFilter + 2);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с фильтром по удалённым местам поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтром по удалённым местам поиска")
        public void testGetSearchPlaceListWithIncludeDeleted(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel searchPlaceForDeletion = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchPlace.deleteSearchPlace(searchPlaceForDeletion.id);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            List<CommonSearchPlaceResponseModel> searchPlaceListWithoutDeleted = ApiMethodsSearchPlace.getSearchPlaceListOData().jsonPath().getList("value", CommonSearchPlaceResponseModel.class);
            List<CommonSearchPlaceResponseModel> searchPlaceListWithDeleted = ApiMethodsSearchPlace.getSearchPlaceListOData(parameters).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

            assertThat(searchPlaceListWithoutDeleted.stream().filter(searchPlace -> searchPlace.id.equals(searchPlaceForDeletion.id))).hasSize(0);
            assertThat(searchPlaceListWithDeleted.stream().filter(searchPlace -> searchPlace.id.equals(searchPlaceForDeletion.id))).hasSize(1);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка полей удалённого места поиска в списке")
    class CheckDeletedSearchPlaceInList{

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Проверка полей в теле ответа при получении списка мест поиска по удалённому месту поиска")
        @Description("Тест проверяет поля в теле ответа при получении списка мест поиска по удалённому месту поиска")
        public void testGetDeletedSearchPlaceWithOnlyRequiredParametersFromListOdataCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchPlace.deleteSearchPlace(responseBodySearchPlaceCreation.id);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class)
                    .stream().filter(searchPlace -> searchPlace.id.equals(responseBodySearchPlaceCreation.id))
                    .findFirst().orElse(null);

            assertThat(responseBody).isNotNull();

            assertAll(
                    () -> assertThat(isValidUUID(responseBody.id)).isTrue(),
                    () -> assertThat(responseBody.name).isEqualTo(responseBodySearchPlaceCreation.name),
                    () -> assertThat(responseBody.categoryType).isEqualTo(responseBodySearchPlaceCreation.categoryType),
                    () -> assertThat(responseBody.type).isEqualTo(responseBodySearchPlaceCreation.type),
                    () -> assertThat(responseBody.parameters).usingRecursiveComparison().isEqualTo(responseBodySearchPlaceCreation.parameters),
                    () -> assertThat(responseBody.excludes).containsExactlyInAnyOrderElementsOf(responseBodySearchPlaceCreation.excludes),
                    () -> assertThat(responseBody.createdUtc).matches(dateTimeISOPattern()),
                    () -> assertThat(responseBody.deletedUtc).matches(dateTimeISOPattern())
            );
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка создания мест поиска с различными параметрами")
    class CheckAddSearchPlaceWithDifferentFieldsValues{

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными наименованиями")
        @Description("Тест проверяет возможность создания места поиска c различными наименованиями")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceNames")
        public void testAddSearchPlaceWithDifferentValidNames(String name){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.name = name;

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.name).isEqualTo(name);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными categoryType")
        @Description("Тест проверяет возможность создания места поиска c различными c различными categoryType")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceCategoryTypes")
        public void testAddSearchPlaceWithDifferentValidCategoryTypes(SearchPlaceCategoryType categoryType){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.categoryType = categoryType.name();

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.categoryType).isEqualTo(categoryType.name());
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными type")
        @Description("Тест проверяет возможность создания места поиска c различными c type")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceTypes")
        public void testAddSearchPlaceWithDifferentValidTypes(SearchPlaceType type){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.type = type.name();

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.type).isEqualTo(type.name());
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными uri в параметрах подключения")
        @Description("Тест проверяет возможность создания места поиска c различными uri в параметрах подключения")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceURIInParameters")
        public void testAddSearchPlaceWithDifferentValidUriInParameters(String uri){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri(uri)
                    .username("userName")
                    .password("p@$$w0rd")
                    .build();

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.parameters = parameters;

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.parameters).usingRecursiveComparison().isEqualTo(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными username в параметрах подключения")
        @Description("Тест проверяет возможность создания места поиска c различными username в параметрах подключения")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceUsernamesInParameters")
        public void testAddSearchPlaceWithDifferentValidUsernamesInParameters(String username){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri("\\\\192.168.1.1\\share")
                    .username(username)
                    .password("p@$$w0rd")
                    .build();

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.parameters = parameters;

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.parameters).usingRecursiveComparison().isEqualTo(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными password в параметрах подключения")
        @Description("Тест проверяет возможность создания места поиска c различными password в параметрах подключения")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlacePasswordsInParameters")
        public void testAddSearchPlaceWithDifferentValidPasswordsInParameters(String password){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                    .uri("\\\\192.168.1.1\\share")
                    .username("userName")
                    .password(password)
                    .build();

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.parameters = parameters;

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.parameters).usingRecursiveComparison().isEqualTo(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различными исключениями директорий из поиска")
        @Description("Тест проверяет возможность создания места поиска c различными исключениями директорий из поиска")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceExclusions")
        public void testAddSearchPlaceWithDifferentExcludes(String exclusion){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            List<String> excludes = Collections.singletonList(exclusion);

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.excludes = excludes;

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.excludes).isEqualTo(excludes);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Создание места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание места поиска c различным количеством исключений директорий из поиска")
        @Description("Тест проверяет возможность создания места поиска c различным количеством исключений директорий из поиска")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceExclusionsWithDifferentCount")
        public void testAddSearchPlaceWithDifferentCountOfExcludes(List<String> excludes){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBody = DataGeneratorSearchPlace.getSearchPlaceModelWithOnlyRequiredParameters();
            requestBody.excludes = excludes;

            CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBody.excludes).isEqualTo(excludes);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка изменения мест поиска с различными параметрами")
    class CheckUpdateSearchPlaceWithDifferentFieldsValues{

        @Flaky
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение наименования места поиска с типом FileShare - SMB")
        @Description("Тест проверяет возможность изменения наименования места поиска с типом FileShare - SMB")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceNames")
        public void testUpdateSearchPlaceNameWithDifferentValidNames(String name){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.name = name;

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(name);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение categoryType в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения categoryType в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceCategoryTypes")
        public void testUpdateSearchPlaceCategoryTypeInFileShareWithoutDeal(SearchPlaceCategoryType categoryType){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.categoryType = categoryType.name();

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.categoryType).isEqualTo(categoryType.name());
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение type в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения type в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceTypes")
        public void testUpdateSearchPlaceTypeInFileShareWithoutDeal(SearchPlaceType type){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.type = type.name();

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.type).isEqualTo(type.name());
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение uri в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения uri в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceURIInParameters")
        public void testUpdateSearchPlaceUriParametersWithDifferentValidValues(String uri){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .parameters(new SearchPlaceParametersModel("ur1", "userName", "p@$$w0rd"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            SearchPlaceParametersModel parametersForUpdate = SearchPlaceParametersModel.builder()
                    .uri(uri)
                    .username("userName")
                    .password("p@$$w0rd")
                    .build();

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.parameters = parametersForUpdate;

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.parameters).usingRecursiveComparison().isEqualTo(parametersForUpdate);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение username в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения username в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceUsernamesInParameters")
        public void testUpdateSearchPlaceUsernameParametersWithDifferentValidValues(String username){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .parameters(new SearchPlaceParametersModel("ur1", "userName", "p@$$w0rd"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestBodySearchPlaceCreation.parameters = new SearchPlaceParametersModel("ur1", username, "p@$$w0rd");

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.parameters).usingRecursiveComparison().isEqualTo(requestSearchPlaceUpdate.parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение password в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения password в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlacePasswordsInParameters")
        public void testUpdateSearchPlacePasswordParametersWithDifferentValidValues(String password){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .parameters(new SearchPlaceParametersModel("ur1", "userName", "p@$$w0rd"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.parameters = new SearchPlaceParametersModel("ur1", "userName", password);

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.parameters).usingRecursiveComparison().isEqualTo(requestSearchPlaceUpdate.parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение excludes места поиска с типом ARM - Local, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения excludes в месте поиска с типом ARM - Local, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceExclusions")
        public void testUpdateSearchPlaceExcludesInARMLocalWithDifferentValidValues(String exclusion){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.Workspace.name())
                    .type(SearchPlaceType.LOCAL.name())
                    .excludes(Collections.singletonList("A:\\"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            List<String> excludes = Collections.singletonList(exclusion);

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.excludes = excludes;

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение excludes места поиска с типом ARM - Local на разное количество исключений, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения excludes в месте поиска с типом ARM - Local на разное количество исключений, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceExclusionsWithDifferentCount")
        public void testUpdateSearchPlaceExcludesInARMLocalWithDifferentCountOfExclusion(List<String> excludes){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.Workspace.name())
                    .type(SearchPlaceType.LOCAL.name())
                    .excludes(Collections.singletonList("A:\\"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.excludes = excludes;

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение excludes места поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения excludes в месте поиска с типом FileShare - SMB, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceExclusions")
        public void testUpdateSearchPlaceExcludesInFileShareSMBWithDifferentValidValues(String exclusion){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .excludes(Collections.singletonList("A:\\"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            List<String> excludes = Collections.singletonList(exclusion);

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.excludes = excludes;

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение excludes места поиска с типом FileShare - SMB на разное количество исключений, если место поиска не используется в деле")
        @Description("Тест проверяет возможность изменения excludes в месте поиска с типом FileShare - SMB на разное количество исключений, если место поиска не используется в деле")
        @ParameterizedTest
        @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchPlace#getValidSearchPlaceExclusionsWithDifferentCount")
        public void testUpdateSearchPlaceExcludesInFileShareSMBWithDifferentCountOfExclusion(List<String> excludes){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .excludes(Collections.singletonList("A:\\"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.excludes = excludes;

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка изменения места поиска, используемого в созданном деле")
    class CheckUpdateSearchPlaceInCreatedDeal{

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение наименования места поиска с типом FileShare - SMB, которое используется в созданном деле")
        @Description("Тест проверяет возможность изменения наименования места поиска с типом FileShare - SMB, которое используется в созданном деле")
        public void testUpdateSearchPlaceNameUsedInCreatedDeal(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();
            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.addDeal(DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                    responseBodySearchPlaceCreation.id,
                    responseBodySearchQueryCreation.id
            ));

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.name = getRandomName();

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(requestSearchPlaceUpdate.name);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение параметров места поиска с типом FileShare - SMB, которое используется в созданном деле")
        @Description("Тест проверяет возможность изменения параметров места поиска с типом FileShare - SMB, которое используется в созданном деле")
        public void testUpdateSearchPlaceParametersUsedInCreatedDeal(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .parameters(new SearchPlaceParametersModel("ur1", "userName", "p@$$w0rd"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.addDeal(DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                    responseBodySearchPlaceCreation.id,
                    responseBodySearchQueryCreation.id
            ));

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.parameters = new SearchPlaceParametersModel("ur12", "userName2", "p@$$w0rd2");

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.parameters).usingRecursiveComparison().isEqualTo(requestSearchPlaceUpdate.parameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение excludes места поиска с типом ARM - Local, которое используется в созданном деле")
        @Description("Тест проверяет возможность изменения excludes места поиска с типом ARM - Local, которое используется в созданном деле")
        public void testUpdateSearchPlaceExcludesInARMLocalInCreatedDeal(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestSearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.Workspace.name())
                    .type(SearchPlaceType.LOCAL.name())
                    .excludes(Collections.singletonList("A:\\"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestSearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.addDeal(DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                    responseBodySearchPlaceCreation.id,
                    responseBodySearchQueryCreation.id
            ));

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.excludes = Collections.singletonList("B:\\");

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(requestSearchPlaceUpdate.excludes);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Изменение места поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Изменение excludes места поиска с типом FileShare - SMB, которое используется в созданном деле")
        @Description("Тест проверяет возможность изменения excludes места поиска с типом FileShare - SMB, которое используется в созданном деле")
        public void testUpdateSearchPlaceExcludesInFileShareSMBWithDifferentValidValuesInCreatedDeal(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddSearchPlaceRequestModel requestSearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                    .name(getRandomName())
                    .categoryType(SearchPlaceCategoryType.FileShare.name())
                    .type(SearchPlaceType.SMB.name())
                    .excludes(Collections.singletonList("A:\\"))
                    .build();

            CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestSearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
            CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

            ApiMethodsDealManipulation.addDeal(DataGeneratorDealManipulation.getDealManipulationModelWithOnlyRequiredParameters(
                    responseBodySearchPlaceCreation.id,
                    responseBodySearchQueryCreation.id
            ));

            UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = new UpdateSearchPlaceRequestModel(responseBodySearchPlaceCreation);
            requestSearchPlaceUpdate.excludes = Collections.singletonList("B:\\");

            CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

            assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(requestSearchPlaceUpdate.excludes);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlace: Проверка работы методов, используемых на UI")
    class CheckSearchPlaceUIMethods{

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка мест поиска для отображения в списке мест поиска")
        @Description("Тест проверяет возможность получения списка мест для отображения в списке мест поиска")
        @Test
        public void testGetSearchPlaceListWEBUI(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Получение списка мест поиска для выпадающего списка мест поиска")
        @Description("Тест проверяет возможность получения списка мест для выпадающего списка мест поиска")
        @Test
        public void testGetSearchPlaceListWEBUIForFilterSearchPlace(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска для отображения в списке мест поиска с фильтром по Категории места поиска")
        @Description("Тест проверяет возможность получения списка мест для отображения в списке мест поиска с фильтром по Категории места поиска")
        @ParameterizedTest
        @MethodSource("eDiscovery.helpers.enums.SearchPlaceCategoryType#getValidSearchPlaceCategoryTypes")
        public void testGetSearchPlaceListWEBUIWithFilterSearchPlaceCategoryType(SearchPlaceCategoryType searchPlaceCategoryType){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(tolower(name),'') and categoryType eq '%s'", searchPlaceCategoryType.name()))
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска для отображения в списке мест поиска с фильтром по Типу места поиска")
        @Description("Тест проверяет возможность получения списка мест для отображения в списке мест поиска с фильтром по Типу места поиска")
        @ParameterizedTest
        @MethodSource("eDiscovery.helpers.enums.SearchPlaceType#getValidSearchPlaceTypes")
        public void testGetSearchPlaceListWEBUIWithFilterSearchPlaceType(SearchPlaceType searchPlaceType){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("contains(tolower(name),'') and type eq '%s'", searchPlaceType.name()))
                    .withOrderBy("createdUtc desc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска для отображения в списке мест поиска с сортировкой по Названию")
        @Description("Тест проверяет возможность получения списка мест для отображения в списке мест поиска с сортировкой по Названию")
        @Test
        public void testGetSearchPlaceListWEBUIWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withOrderBy("name asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска для отображения в списке мест поиска с сортировкой по Категории")
        @Description("Тест проверяет возможность получения списка мест для отображения в списке мест поиска с сортировкой по Категории")
        @Test
        public void testGetSearchPlaceListWEBUIWithSortingCategoryType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withOrderBy("categoryType asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

        @Epic("Сервис Deal")
        @Feature("Места поиска")
        @Story("Получение списка мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска для отображения в списке мест поиска с сортировкой по Типу")
        @Description("Тест проверяет возможность получения списка мест для отображения в списке мест поиска с сортировкой по Типу")
        @Test
        public void testGetSearchPlaceListWEBUIWithSortingType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("contains(tolower(name),'')")
                    .withOrderBy("type asc")
                    .withCount(true)
                    .withTop(10)
                    .withSkip(0)
                    .build();

            List<CommonSearchPlaceResponseModel> responseBody = ApiMethodsSearchPlace.getSearchPlaceListOData(params).jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        }

    }

}
