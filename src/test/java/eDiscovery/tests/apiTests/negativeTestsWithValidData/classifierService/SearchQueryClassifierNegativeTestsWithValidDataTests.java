package eDiscovery.tests.apiTests.negativeTestsWithValidData.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsSearchQueryClassifier;
import eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.classifier.searchQuery.AddSearchQueryClassifierRequestModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;
import eDiscovery.models.classifier.searchQuery.UpdateSearchQueryClassifierRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.ErrorDescription.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with valid data: Classifier - SearchQuery")
public class SearchQueryClassifierNegativeTestsWithValidDataTests extends TestBase {
    
    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создать поисковый запрос с существующим наименованием")
    @Description("Тест проверяет невозможность создания поискового запроса с существующим наименованием")
    public void testAddSearchQueryClassifierWithExistingNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        String name = getRandomName();

        AddSearchQueryClassifierRequestModel requestBodySearchQueryCreation = AddSearchQueryClassifierRequestModel.builder()
                .name(name)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();

        ApiMethodsSearchQueryClassifier.addSearchQuery(requestBodySearchQueryCreation);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBodySearchQueryCreation).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_QUERY_NAME);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить поисковый запрос с несуществующим ID")
    @Description("Тест проверяет невозможность изменить поисковый запрос с несуществующим ID")
    public void testUpdateSearchQueryClassifierWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        String uuid = faker.internet().uuid();

        ErrorModel responseErrorBody = ApiMethodsSearchQueryClassifier.updateSearchQuery(
                UpdateSearchQueryClassifierRequestModel.builder()
                        .id(uuid)
                        .name(getRandomName())
                        .type(SearchQueryType.Regex.name())
                        .value("\\d{10}")
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_CLASSIFIER_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_CLASSIFIER_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(uuid);
        assertThat(responseErrorBody.data.type).isEqualTo(CLASSIFIER_SEARCH_QUERY_INFO);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить name у поискового запроса на существующее")
    @Description("Тест проверяет невозможность изменить name у поискового запроса на существующее")
    public void testUpdateSearchQueryClassifierNameToExistNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchQueryClassifierResponseModel responseSearchQueryCreationForUpdatingExists = DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();
        CommonSearchQueryClassifierResponseModel responseSearchQueryCreationForUpdating = DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        UpdateSearchQueryClassifierRequestModel requestSearchQueryUpdateBody = UpdateSearchQueryClassifierRequestModel.builder()
                .name(responseSearchQueryCreationForUpdatingExists.name)
                .id(responseSearchQueryCreationForUpdating.id)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchQueryClassifier.updateSearchQuery(requestSearchQueryUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_QUERY_NAME);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить поисковый запрос с несуществующим ID")
    @Description("Тест проверяет невозможность удалить поисковый запрос с несуществующим ID")
    public void testDeleteSearchQueryClassifierWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        String uuid = faker.internet().uuid();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());
        ErrorModel responseErrorBody = ApiMethodsSearchQueryClassifier.deleteSearchQuery(uuid).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_CLASSIFIER_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_CLASSIFIER_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(uuid);
        assertThat(responseErrorBody.data.type).isEqualTo(CLASSIFIER_SEARCH_QUERY_INFO);

    }


}
