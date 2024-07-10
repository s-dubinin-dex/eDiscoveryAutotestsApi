package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchPlace.*;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static eDiscovery.helpers.ErrorDescription.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with invalid data - SearchPlace")
public class SearchPlaceNegativeTestsWithInvalidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создания места поиска без указания тела")
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
    @DisplayName("Невозможность создания места поиска без указания параметра name (без указания параметров)")
    @Description("Тест проверяет невозможность создания места поиска без указания параметра name (без указания параметров)")
    public void testAddSearchPlaceWithoutNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.addSearchPlace(
                AddSearchPlaceRequestModelNotNull.builder()
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
    @DisplayName("Невозможность создания места поиска с передачей только categoryType")
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
    @DisplayName("Невозможность создания места поиска с передачей только type")
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
    @DisplayName("Невозможность создания места поиска с передачей только parameters")
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
    @DisplayName("Невозможность создания места поиска с неполной передачей parameters, не передан uri")
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
    @DisplayName("Невозможность создания места поиска с неполной передачей parameters, не передан username")
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
    @DisplayName("Невозможность создания места поиска с неполной передачей parameters, не передан password")
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
    @DisplayName("Невозможность создания места поиска с передачей пустого объекта parameters, не передан ни один из параметров")
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
    @DisplayName("Невозможность создания места поиска с передачей только excludes")
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

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска без передачи тела запроса")
    @Description("Тест проверяет невозможность изменения места поиска без передачи тела запроса")
    public void testUpdateSearchPlaceWithoutBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.updateSearchPlace();
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
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса без параметров")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса без параметров")
    public void testUpdateSearchPlaceWithEmptyBodyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса с передачей только параметра id")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса с передачей только параметра id")
    public void testUpdateSearchPlaceWithIDOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .id(responseSearchPlaceCreation.id)
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса с передачей только name, без передачи параметра id")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса с передачей только name, без передачи параметра id")
    public void testUpdateSearchPlaceWithoutIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(new UUID(0L,0L).toString());
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_PLACE_INFO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса c id, не соответствующим маске UUID")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса c id, не соответствующим маске UUID")
    public void testUpdateSearchPlaceWithIDIsNotUUIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Response response = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .name(DataGeneratorDealService.getRandomName())
                        .id(DataGeneratorDealService.getRandomName(16))
                        .build()
        );
        ErrorModel responseErrorBody = response.as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.editEntity.get(0).errorCode).isEqualTo(VALIDATIONS_THE_EDIT_ENTITY_FIELD_IS_REQUIRED);

        List<ErrorModel.ErrorModelDetail> errorDetails = response.jsonPath().getList("errors['$.id']", ErrorModel.ErrorModelDetail.class);

        assertThat(errorDetails.get(0).errorCode).matches(VALIDATIONS_THE_JSON_VALUE_NOT_BE_CONVERTED_TO_GUID);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса с передачей только параметра categoryType")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса с передачей только параметра categoryType")
    public void testUpdateSearchPlaceWithCategoryTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .categoryType(SearchPlaceCategoryType.FileShare)
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса с передачей только параметра type")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса с передачей только параметра type")
    public void testUpdateSearchPlaceWithTypeOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .type(SearchPlaceType.SMB)
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса с передачей только параметра parameters")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса с передачей только параметра parameters")
    public void testUpdateSearchPlaceWithParametersOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .uri("uri")
                .username("username")
                .password("password" )
                .build();

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .parameters(parameters)
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска с неполной передачей parameters, не передан uri")
    @Description("Тест проверяет невозможность изменения места поиска с неполной передачей parameters, не передан uri")
    public void testUpdateSearchPlaceWithoutURIInParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .username("username")
                .password("password" )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        Response response = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .id(responseSearchPlaceCreation.id)
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
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска с неполной передачей parameters, не передан username")
    @Description("Тест проверяет невозможность изменения места поиска с неполной передачей parameters, не передан username")
    public void testUpdateSearchPlaceWithoutUsernameInParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .uri("uri")
                .password("password" )
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        Response response = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .id(responseSearchPlaceCreation.id)
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
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска с неполной передачей parameters, не передан password")
    @Description("Тест проверяет невозможность изменения места поиска с неполной передачей parameters, не передан password")
    public void testUpdateSearchPlaceWithoutPasswordInParametersIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .uri("uri")
                .username("username")
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        Response response = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .id(responseSearchPlaceCreation.id)
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
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска с передачей пустого объекта parameters, не передан ни один из параметров")
    @Description("Тест проверяет невозможность изменения места поиска с передачей пустого объекта parameters, не передан ни один из параметров")
    public void testAddSearchPlaceWithEmptyParametersIsImpossible1(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        SearchPlaceParametersModelNotNull parameters = SearchPlaceParametersModelNotNull.builder()
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        Response response = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .id(responseSearchPlaceCreation.id)
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
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменения места поиска c телом запроса с передачей только параметра exclude")
    @Description("Тест проверяет невозможность изменения места поиска c телом запроса с передачей только параметра exclude")
    public void testUpdateSearchPlaceWithExcludesOnlyIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModelNotNull.builder()
                        .excludes(Collections.singletonList("C:\\"))
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);

        assertThat(responseErrorBody.errors.name.get(0).errorCode).isEqualTo(VALIDATIONS_THE_NAME_FIELD_IS_REQUIRED);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удаления места поиска без передачи id")
    @Description("Тест проверяет невозможность удаления места поиска без передачи id")
    public void testDeleteSearchPlaceWithoutIdIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.deleteSearchPlace().as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(ID_IS_EMPTY_PARAMETER_ID);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удаления места поиска с id, не соответствующим маске uuid")
    @Description("Тест проверяет невозможность удаления места поиска с id, не соответствующим маске uuid")
    public void testDeleteSearchPlaceWithIdIsNotUUIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.deleteSearchPlace(
                DataGeneratorDealService.getRandomName(16)
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(responseErrorBody.status).isEqualTo(400);
        assertThat(responseErrorBody.detail).isEqualTo(SEE_ERRORS_FOR_DETAILS);
        assertThat(responseErrorBody.errors.id.get(0).errorCode).matches(VALIDATIONS_THE_VALUE___IS_NOT_VALID);

    }

}
