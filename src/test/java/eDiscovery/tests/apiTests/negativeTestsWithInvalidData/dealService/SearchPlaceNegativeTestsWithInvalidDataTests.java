package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModelNotNull;
import eDiscovery.models.deal.searchPlace.SearchPlaceParametersModelNotNull;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.helpers.ErrorDescription.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPlaceNegativeTestsWithInvalidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска без указания тела")
    public void testAddSearchPlaceWithoutBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.addSearchPlace();
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
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска без указания параметра name")
    public void testAddSearchPlaceWithoutNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.addSearchPlace(AddSearchPlaceRequestModelNotNull.builder().build());
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с передачей только categoryType")
    public void testAddSearchPlaceWithCategoryTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .categoryType(SearchPlaceCategoryType.ARM)
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
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с передачей только type")
    public void testAddSearchPlaceWithTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .type(SearchPlaceType.SMB)
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
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с передачей только parameters")
    public void testAddSearchPlaceWithParametersOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .uri("uri")
                .username("username")
                .password("password" )
                .build();

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .parameters(parameters)
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
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с неполной передачей parameters, не передан uri")
    public void testAddSearchPlaceWithoutURIInParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .username("username")
                .password("password" )
                .build();

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .parameters(parameters)
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        List<ErrorModel.ErrorModelDetail> errorDetails = response.jsonPath().getList("errors['parameters.Uri']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorDetails.get(0).errorCode).isEqualTo(VALIDATIONS_THE_URI_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с неполной передачей parameters, не передан username")
    public void testAddSearchPlaceWithoutUsernameInParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .uri("uri")
                .password("password" )
                .build();

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .parameters(parameters)
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        List<ErrorModel.ErrorModelDetail> errorDetails = response.jsonPath().getList("errors['parameters.Username']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorDetails.get(0).errorCode).isEqualTo(VALIDATIONS_THE_USERNAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с неполной передачей parameters, не передан password")
    public void testAddSearchPlaceWithoutPasswordInParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .uri("uri")
                .username("username")
                .build();

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .parameters(parameters)
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        List<ErrorModel.ErrorModelDetail> errorDetails = response.jsonPath().getList("errors['parameters.Password']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorDetails.get(0).errorCode).isEqualTo(VALIDATIONS_THE_PASSWORD_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с передачей пустого объекта parameters, не передан ни один из параметров")
    public void testAddSearchPlaceWithEmptyParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .build();

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .parameters(parameters)
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        List<ErrorModel.ErrorModelDetail> errorDetailsUri       = response.jsonPath().getList("errors['parameters.Uri']", ErrorModel.ErrorModelDetail.class);
        List<ErrorModel.ErrorModelDetail> errorDetailsPassword  = response.jsonPath().getList("errors['parameters.Password']", ErrorModel.ErrorModelDetail.class);
        List<ErrorModel.ErrorModelDetail> errorDetailsUsername  = response.jsonPath().getList("errors['parameters.Username']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorDetailsUri.get(0).errorCode).isEqualTo(VALIDATIONS_THE_URI_FIELD_IS_REQUIRED);
        assertThat(errorDetailsPassword.get(0).errorCode).isEqualTo(VALIDATIONS_THE_PASSWORD_FIELD_IS_REQUIRED);
        assertThat(errorDetailsUsername.get(0).errorCode).isEqualTo(VALIDATIONS_THE_USERNAME_FIELD_IS_REQUIRED);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет невозможность создания места поиска с передачей только excludes")
    public void testAddSearchPlaceWithExcludesOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
                        .excludes(Collections.singletonList("C:\\"))
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);
    }

}
