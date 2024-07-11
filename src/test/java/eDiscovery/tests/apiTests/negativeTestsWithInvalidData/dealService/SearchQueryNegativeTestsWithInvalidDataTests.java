package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModelNotNull;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModelNotNull;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static eDiscovery.helpers.ErrorDescription.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with invalid data - SearchQuery")
public class SearchQueryNegativeTestsWithInvalidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса без указания тела")
    @Description("Тест проверяет невозможность создания поискового запроса без указания тела")
    public void testAddSearchQueryWithoutBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery();
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        List<ErrorModel.ErrorModelDetail> errorsWithEmptyKey = response.jsonPath().getList("errors['']", ErrorModel.ErrorModelDetail.class);
        assertThat(errorsWithEmptyKey.get(0).errorCode).isEqualTo(VALIDATIONS_A_NON_EMPTY_REQUEST_BODY_IS_REQUIRED);

        assertThat(responseErrorBody.errors.newEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NEW_ENTITY_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с пустым телом")
    @Description("Тест проверяет невозможность создания поискового запроса с пустым телом")
    public void testAddSearchQueryWithEmptyBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с передачей только name")
    @Description("Тест проверяет невозможность создания поискового запроса с передачей только name")
    public void testAddSearchQueryWithNameOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса без передачи name")
    @Description("Тест проверяет невозможность создания поискового запроса без передачи name")
    public void testAddSearchQueryWithoutNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с передачей только type")
    @Description("Тест проверяет невозможность создания поискового запроса с передачей только type")
    public void testAddSearchQueryWithTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .type(SearchQueryType.Text.name())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с передачей только value")
    @Description("Тест проверяет невозможность создания поискового с передачей только value")
    public void testAddSearchQueryWithValueOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса без передачи value")
    @Description("Тест проверяет невозможность создания поискового запроса без передачи value")
    public void testAddSearchQueryWithoutValueOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса без указания тела")
    @Description("Тест проверяет невозможность изменения поискового запроса без указания тела")
    public void testUpdateSearchQueryWithoutBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery();
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        List<ErrorModel.ErrorModelDetail> errorsWithEmptyKey = response.jsonPath().getList("errors['']", ErrorModel.ErrorModelDetail.class);
        assertThat(errorsWithEmptyKey.get(0).errorCode).isEqualTo(VALIDATIONS_A_NON_EMPTY_REQUEST_BODY_IS_REQUIRED);

        assertThat(responseErrorBody.errors.editEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_EDIT_ENTITY_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с передачей только id")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только id")
    public void testUpdateSearchQueryWithIdOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(faker.internet().uuid())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса без передачи id")
    @Description("Тест проверяет невозможность изменения поискового запроса без передачи id")
    public void testUpdateSearchQueryWithoutIdIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_SEARCH_QUERY_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(new UUID(0,0).toString());
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_QUERY_INFO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с передачей только name")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только name")
    public void testUpdateSearchQueryWithNameOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса без передачи name")
    @Description("Тест проверяет невозможность изменения поискового запроса без передачи name")
    public void testUpdateSearchQueryWithoutNameImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchQueryResponseModel responseCreation = DataGeneratorDealService.createBasicSearchQuery();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(responseCreation.id)
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с передачей только type")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только type")
    public void testUpdateSearchQueryWithTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .type(SearchQueryType.Regex.name())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с передачей только value")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только value")
    public void testUpdateSearchQueryWithValueOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса без передачи value")
    @Description("Тест проверяет невозможность изменения поискового запроса без передачи value")
    public void testUpdateSearchQueryWithoutValueIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(faker.internet().uuid())
                        .name(DataGeneratorDealService.getRandomName())
                        .type(SearchQueryType.Regex.name())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_VALUE_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удаления поискового запроса без передачи id")
    @Description("Тест проверяет невозможность удаления поискового запроса без передачи id")
    public void testDeleteSearchQueryWithoutIdIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.deleteSearchQuery();
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(ID_IS_EMPTY_PARAMETER_ID);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удаления поискового запроса с передачей id, не соответствующем маске uuid")
    @Description("Тест проверяет невозможность удаления поискового запроса c передачей id, не соответствующем маске uuid")
    public void testDeleteSearchQueryWithIdIsNotUUIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.deleteSearchQuery(DataGeneratorDealService.getRandomName(16));
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.id.get(0).errorCode).matches(VALIDATIONS_THE_VALUE___IS_NOT_VALID);

    }

}
