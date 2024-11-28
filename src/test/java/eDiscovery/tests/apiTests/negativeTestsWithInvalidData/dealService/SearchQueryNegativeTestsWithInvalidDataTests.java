package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.dealService.DataGeneratorSearchQuery;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchQuery.*;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.UUID;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .type(SearchQueryType.Regex.name())
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
    @DisplayName("Невозможность создания поискового запроса с name = null")
    @Description("Тест проверяет невозможность создания поискового запроса с name = null")
    public void testAddSearchQueryWithNullNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModel.builder()
                        .type(SearchQueryType.Regex.name())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с пустым name")
    @Description("Тест проверяет невозможность создания поискового запроса с пустым name")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.dealService.DataGeneratorSearchQuery#getEmptySearchQueryNames")
    public void testAddSearchQueryWithInvalidNameIsImpossible(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(name)
                        .type(SearchQueryType.Regex.name())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(INCORRECT_NAME);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с длиной name, превышающим допустимую длину")
    @Description("Тест проверяет невозможность создания поискового запроса с длиной name, превышающим допустимую длину")
    public void testAddSearchQueryWithExceedingNameLengthIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(DataGeneratorSearchQuery.getSearchQueryNameWithExceedingLength())
                        .type(SearchQueryType.Regex.name())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_FIELD_NAME_MAXIMUM_LENGTH_IS_256);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с передачей только type")
    @Description("Тест проверяет невозможность создания поискового запроса с передачей только type")
    public void testAddSearchQueryWithTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
    @DisplayName("Невозможность создания поискового запроса без передачи type")
    @Description("Тест проверяет невозможность создания поискового запроса без передачи type")
    public void testAddSearchQueryWithoutTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(TYPE_FIELD_IS_INCORRECT_UNSUPPORTABLE_UNDEFINED_VALUE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с type = null")
    @Description("Тест проверяет невозможность создания поискового запроса с type = null")
    public void testAddSearchQueryWithNullTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModel.builder()
                        .name(getRandomName())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.newEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NEW_ENTITY_FIELD_IS_REQUIRED);

        List<ErrorModel.ErrorModelDetail> errorTypeBody = response.jsonPath()
                .getList("errors['$.type']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorTypeBody.get(0).errorCode).matches(VALIDATIONS_THE_JSON_VALUE_NOT_BE_CONVERTED_TO_SEARCH_QUERY_TYPE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового с несуществующим type")
    @Description("Тест проверяет невозможность создания поискового запроса с несуществующим type")
    public void testAddSearchQueryWithNotExistingTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
                        .type(getRandomName())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.newEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NEW_ENTITY_FIELD_IS_REQUIRED);

        List<ErrorModel.ErrorModelDetail> errorTypeBody = response.jsonPath()
                .getList("errors['$.type']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorTypeBody.get(0).errorCode).matches(VALIDATIONS_THE_JSON_VALUE_NOT_BE_CONVERTED_TO_SEARCH_QUERY_TYPE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса c type = Undefined")
    @Description("Тест проверяет невозможность создания поискового запроса c type = Undefined")
    public void testAddSearchQueryWithUndefinedTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
                        .type(SearchQueryType.Undefined.name())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(TYPE_FIELD_IS_INCORRECT_UNSUPPORTABLE_UNDEFINED_VALUE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с передачей только value")
    @Description("Тест проверяет невозможность создания поискового с передачей только value")
    public void testAddSearchQueryWithValueOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
    public void testAddSearchQueryWithoutValueIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
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
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с value = null")
    @Description("Тест проверяет невозможность создания поискового запроса с value = null")
    public void testAddSearchQueryWithNullValueIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModel.builder()
                        .name(getRandomName())
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
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания поискового запроса с длиной value, превышающим допустимую длину")
    @Description("Тест проверяет невозможность создания поискового запроса с длиной value, превышающим допустимую длину")
    public void testAddSearchQueryWithExceedingValueLengthIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.addSearchQuery(
                AddSearchQueryRequestModel.builder()
                        .name(getRandomName())
                        .type(SearchQueryType.Regex.name())
                        .value(DataGeneratorSearchQuery.getSearchQueryValueWithExceedingLength())
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.value.get(0).errorCode).isEqualTo(VALIDATIONS_THE_FIELD_VALUE_MAXIMUM_LENGTH_IS_2000);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса без указания тела")
    @Description("Тест проверяет невозможность изменения поискового запроса без указания тела")
    public void testUpdateSearchQueryWithoutBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
    @DisplayName("Невозможность изменения поискового запроса с пустым телом")
    @Description("Тест проверяет невозможность изменения поискового запроса с пустым телом")
    public void testUpdateSearchQueryWithEmptyBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder().build()
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
    @DisplayName("Невозможность изменения поискового запроса с передачей только id")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только id")
    public void testUpdateSearchQueryWithIdOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
                        .type(SearchQueryType.Regex.name())
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
    @DisplayName("Невозможность изменения поискового запроса c id = null")
    @Description("Тест проверяет невозможность изменения поискового запроса c id = null")
    public void testUpdateSearchQueryWithNullIdIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModel.builder()
                        .name(getRandomName())
                        .type(SearchQueryType.Regex.name())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.editEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_EDIT_ENTITY_FIELD_IS_REQUIRED);

        List<ErrorModel.ErrorModelDetail> errorsId = response.jsonPath().getList("errors['$.id']", ErrorModel.ErrorModelDetail.class);
        assertThat(errorsId.get(0).errorCode).matches(VALIDATIONS_THE_JSON_VALUE_NOT_BE_CONVERTED_TO_GUID);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с передачей только name")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только name")
    public void testUpdateSearchQueryWithNameOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .name(getRandomName())
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchQueryResponseModel responseCreation = DataGeneratorSearchQuery.createBasicSearchQuery();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(responseCreation.id)
                        .type(SearchQueryType.Regex.name())
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
    @DisplayName("Невозможность изменения поискового запроса без передачи type")
    @Description("Тест проверяет невозможность изменения поискового запроса без передачи type")
    public void testUpdateSearchQueryWithoutTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(faker.internet().uuid())
                        .name(getRandomName())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(TYPE_FIELD_IS_INCORRECT_UNSUPPORTABLE_UNDEFINED_VALUE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с несуществующим type")
    @Description("Тест проверяет невозможность изменения поискового запроса с несуществующим type")
    public void testUpdateSearchQueryWithNotExistingTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(faker.internet().uuid())
                        .name(getRandomName())
                        .type(getRandomName())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.editEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_EDIT_ENTITY_FIELD_IS_REQUIRED);

        List<ErrorModel.ErrorModelDetail> errorTypeBody = response.jsonPath()
                .getList("errors['$.type']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorTypeBody.get(0).errorCode).matches(VALIDATIONS_THE_JSON_VALUE_NOT_BE_CONVERTED_TO_SEARCH_QUERY_TYPE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с type = Undefined")
    @Description("Тест проверяет невозможность изменения поискового запроса с type = Undefined")
    public void testUpdateSearchQueryWithUndefinedTypeIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(faker.internet().uuid())
                        .name(getRandomName())
                        .type(SearchQueryType.Undefined.name())
                        .value("\\d{10}")
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(TYPE_FIELD_IS_INCORRECT_UNSUPPORTABLE_UNDEFINED_VALUE);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения поискового запроса с передачей только value")
    @Description("Тест проверяет невозможность изменения поискового запроса с передачей только value")
    public void testUpdateSearchQueryWithValueOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.updateSearchQuery(
                UpdateSearchQueryRequestModelNotNull.builder()
                        .id(faker.internet().uuid())
                        .name(getRandomName())
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchQuery.deleteSearchQuery(getRandomName(16));
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.id.get(0).errorCode).matches(VALIDATIONS_THE_VALUE___IS_NOT_VALID);

    }

}
