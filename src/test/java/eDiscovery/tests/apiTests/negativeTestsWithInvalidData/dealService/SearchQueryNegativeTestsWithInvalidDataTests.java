package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModelNotNull;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}
