package eDiscovery.tests.apiTests.negativeTestsWithValidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
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

import java.util.Collections;

import static eDiscovery.helpers.ErrorDescription.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with valid data - SearchQuery")
public class SearchQueryNegativeTestsWithValidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создать поискового запроса с существующим наименованием")
    @Description("Тест проверяет невозможность создания поискового запроса с существующим наименованием")
    public void testAddSearchQueryWithExistingNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        String name = DataGeneratorDealService.getRandomName();

        AddSearchQueryRequestModel requestBodySearchQueryCreation = AddSearchQueryRequestModel.builder()
                .name(name)
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
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить поисковый запрос с несуществующим ID")
    @Description("Тест проверяет невозможность изменить поисковый запрос с несуществующим ID")
    public void testUpdateSearchQueryWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        String uuid = faker.internet().uuid();

        ErrorModel responseErrorBody = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModel.builder()
                        .id(uuid)
                        .name(DataGeneratorDealService.getRandomName())
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
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить name у поискового запроса на существующее")
    @Description("Тест проверяет невозможность изменить name у поискового запроса на существующее")
    public void testUpdateSearchQueryNameToExistNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchQueryResponseModel responseSearchQueryCreationForUpdatingExists = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();
        CommonSearchQueryResponseModel responseSearchQueryCreationForUpdating = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        UpdateSearchQueryRequestModel requestSearchQueryUpdateBody = UpdateSearchQueryRequestModel.builder()
                .name(responseSearchQueryCreationForUpdatingExists.name)
                .id(responseSearchQueryCreationForUpdating.id)
                .value("\\d{10}")
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQuery.updateSearchQuery(requestSearchQueryUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_QUERY_NAME);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить поисковый запрос с несуществующим ID")
    @Description("Тест проверяет невозможность удалить поисковый запрос с несуществующим ID")
    public void testDeleteSearchQueryWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

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
    @Feature("Поисковый запрос")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить используемый поисковый запрос")
    @Description("Тест проверяет невозможность удалить используемый поисковый запрос")
    public void testDeleteSearchQueryFileShareSMBUsedInDealIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();
        CommonSearchQueryResponseModel responseSearchQueryCreationBody = DataGeneratorDealService.createBasicSearchQuery();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreationBody.id),
                Collections.singletonList(responseSearchQueryCreationBody.id)
        );

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQuery.deleteSearchQuery(responseSearchQueryCreationBody.id).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_IN_USE_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_IN_USE_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(responseSearchQueryCreationBody.id);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_QUERY_INFO);

    }

}