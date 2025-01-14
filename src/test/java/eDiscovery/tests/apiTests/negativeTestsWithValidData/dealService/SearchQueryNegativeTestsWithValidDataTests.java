package eDiscovery.tests.apiTests.negativeTestsWithValidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.ErrorDescription.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with valid data: Deal - SearchQuery")
public class SearchQueryNegativeTestsWithValidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковые запросы")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создать поисковый запрос с существующим наименованием")
    @Description("Тест проверяет невозможность создания поискового запроса с существующим наименованием")
    public void testAddSearchQueryWithExistingNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        String name = getRandomName();

        AddSearchQueryRequestModel requestBodySearchQueryCreation = AddSearchQueryRequestModel.builder()
                .name(name)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();

        ApiMethodsSearchQuery.addSearchQuery(requestBodySearchQueryCreation);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQuery.addSearchQuery(requestBodySearchQueryCreation).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_QUERY_NAME);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковые запросы")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить поисковый запрос с несуществующим ID")
    @Description("Тест проверяет невозможность изменить поисковый запрос с несуществующим ID")
    public void testUpdateSearchQueryWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        String uuid = faker.internet().uuid();

        ErrorModel responseErrorBody = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModel.builder()
                        .id(uuid)
                        .name(getRandomName())
                        .type(SearchQueryType.Regex.name())
                        .value("\\d{10}")
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(uuid);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_QUERY_INFO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковые запросы")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить name у поискового запроса на существующее")
    @Description("Тест проверяет невозможность изменить name у поискового запроса на существующее")
    public void testUpdateSearchQueryNameToExistNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchQueryResponseModel responseSearchQueryCreationForUpdatingExists = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();
        CommonSearchQueryResponseModel responseSearchQueryCreationForUpdating = DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters();

        UpdateSearchQueryRequestModel requestSearchQueryUpdateBody = UpdateSearchQueryRequestModel.builder()
                .name(responseSearchQueryCreationForUpdatingExists.name)
                .id(responseSearchQueryCreationForUpdating.id)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQuery.updateSearchQuery(requestSearchQueryUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_QUERY_NAME);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковые запросы")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить поисковый запрос с несуществующим ID")
    @Description("Тест проверяет невозможность удалить поисковый запрос с несуществующим ID")
    public void testDeleteSearchQueryWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        String uuid = faker.internet().uuid();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());
        ErrorModel responseErrorBody = ApiMethodsSearchQuery.deleteSearchQuery(uuid).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(uuid);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_QUERY_INFO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковые запросы")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить используемый поисковый запрос")
    @Description("Тест проверяет невозможность удалить используемый поисковый запрос")
    public void testDeleteSearchQueryUsedInDealIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = DataGeneratorSearchPlace.createBasicSearchPlaceFileShareSMB();
        CommonSearchQueryResponseModel responseSearchQueryCreationBody = DataGeneratorSearchQuery.createBasicSearchQuery();

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters(
                responseSearchPlaceCreationBody.id,
                responseSearchQueryCreationBody.id
        );

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQuery.deleteSearchQuery(responseSearchQueryCreationBody.id).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_IN_USE_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_IN_USE_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(responseSearchQueryCreationBody.id);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_QUERY_INFO);

    }

}
