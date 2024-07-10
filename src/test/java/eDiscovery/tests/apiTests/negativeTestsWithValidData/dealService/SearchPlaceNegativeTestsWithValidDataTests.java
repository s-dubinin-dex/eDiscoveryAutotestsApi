package eDiscovery.tests.apiTests.negativeTestsWithValidData.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import static eDiscovery.helpers.ErrorDescription.*;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.SearchPlaceParametersModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Negative tests with valid data - SearchPlace ")
public class SearchPlaceNegativeTestsWithValidDataTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность создать место поиска с существующим наименованием")
    @Description("Тест проверяет невозможность создания места поиска с существующим наименованием")
    public void testAddSearchPlaceWithExistingNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        String name = DataGeneratorDealService.getRandomName();

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(name)
                .build();

        ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_PLACE_NAME);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить место поиска с несуществующим ID")
    @Description("Тест проверяет невозможность изменить место поиска с несуществующим ID")
    public void testUpdateSearchPlaceWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());

        String uuid = faker.internet().uuid();

        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(
                UpdateSearchPlaceRequestModel.builder()
                        .id(uuid)
                        .name(DataGeneratorDealService.getRandomName())
                        .build()
        ).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(uuid);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_PLACE_INFO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить name у места поиска с типом ARM")
    @Description("Тест проверяет невозможность изменить name у места поиска с типом ARM")
    public void testUpdateSearchPlaceNameWithARMLocalIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .build();

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .id(responseSearchPlaceCreationBody.id)
                .categoryType(responseSearchPlaceCreationBody.categoryType)
                .type(responseSearchPlaceCreationBody.type)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(FOR_LOCAL_AGENT_ALLOWED_ONLY_EXCEPTIONS_UPDATE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить name у места поиска с типом FileShare - SMB на существующее")
    @Description("Тест проверяет невозможность изменить name у места поиска с типом FileShare - SMB на существующее")
    public void testUpdateSearchPlaceNameWithFileShareToExistNameIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreationForUpdatingExists = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();
        CommonSearchPlaceResponseModel responseSearchPlaceCreationForUpdating = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(responseSearchPlaceCreationForUpdatingExists.name)
                .id(responseSearchPlaceCreationForUpdating.id)
                .categoryType(responseSearchPlaceCreationForUpdating.categoryType)
                .type(responseSearchPlaceCreationForUpdating.type)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_ALREADY_EXISTS_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DUPLICATE_KEY_SEARCH_PLACE_NAME);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить categoryType у места поиска с типом ARM")
    @Description("Тест проверяет невозможность изменить categoryType у места поиска с типом ARM")
    public void testUpdateSearchPlaceCategoryTypeWithARMLocalIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .build();

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(responseSearchPlaceCreationBody.name)
                .id(responseSearchPlaceCreationBody.id)
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(responseSearchPlaceCreationBody.type)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(FOR_LOCAL_AGENT_ALLOWED_ONLY_EXCEPTIONS_UPDATE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить categoryType у места поиска с типом FileShare - SMB, если место поиска используется в деле")
    @Description("Тест проверяет невозможность изменить categoryType у места поиска с типом FileShare - SMB, если место поиска используется в деле")
    public void testUpdateSearchPlaceCategoryTypeWithFileShareSMBUsedInDealIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseBodySearchPlaceCreation.id),
                Collections.singletonList(responseBodySearchQueryCreation.id)
        );

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(responseBodySearchPlaceCreation.name)
                .id(responseBodySearchPlaceCreation.id)
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(responseBodySearchPlaceCreation.type)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(FOR_FILE_SHARE_CATEGORY_USED_IN_DEAL_ALLOWED_ONLY_NAME_EXCLUDES_PARAMETERS_UPDATE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить type у места поиска с типом ARM")
    @Description("Тест проверяет невозможность изменить type у места поиска с типом ARM")
    public void testUpdateSearchPlaceTypeWithARMLocalIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .build();

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(responseSearchPlaceCreationBody.name)
                .id(responseSearchPlaceCreationBody.id)
                .categoryType(responseSearchPlaceCreationBody.categoryType)
                .type(SearchPlaceType.SMB)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(FOR_LOCAL_AGENT_ALLOWED_ONLY_EXCEPTIONS_UPDATE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить type у места поиска с типом FileShare - SMB, если место поиска используется в деле")
    @Description("Тест проверяет невозможность изменить type у места поиска с типом FileShare - SMB, если место поиска используется в деле")
    public void testUpdateSearchPlaceTypeWithFileShareSMBUsedInDealIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseBodySearchPlaceCreation.id),
                Collections.singletonList(responseBodySearchQueryCreation.id)
        );

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(responseBodySearchPlaceCreation.name)
                .id(responseBodySearchPlaceCreation.id)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(SearchPlaceType.NFS)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(FOR_FILE_SHARE_CATEGORY_USED_IN_DEAL_ALLOWED_ONLY_NAME_EXCLUDES_PARAMETERS_UPDATE);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность изменить parameters у места поиска с типом ARM")
    @Description("Тест проверяет невозможность изменить parameters у места поиска с типом ARM")
    public void testUpdateSearchPlaceParametersWithARMLocalIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .build();

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username("userName")
                .password("p@$$w0rd")
                .build();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdateBody = UpdateSearchPlaceRequestModel.builder()
                .name(responseSearchPlaceCreationBody.name)
                .id(responseSearchPlaceCreationBody.id)
                .categoryType(responseSearchPlaceCreationBody.categoryType)
                .type(responseSearchPlaceCreationBody.type)
                .parameters(parameters)
                .build();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdateBody).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(FOR_LOCAL_AGENT_ALLOWED_ONLY_EXCEPTIONS_UPDATE);

    }


    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить место поиска с несуществующим ID")
    @Description("Тест проверяет невозможность удалить место поиска с несуществующим ID")
    public void testDeleteSearchPlaceWithNotExistsIDIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        String uuid = faker.internet().uuid();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec404NotFound());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.deleteSearchPlace(uuid).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_NOT_FOUND_EXCEPTION_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_NOT_FOUND_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(uuid);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_PLACE_INFO);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить используемое место поиска FileShare - SMB")
    @Description("Тест проверяет невозможность удалить используемое место поиска FileShare - SMB")
    public void testDeleteSearchPlaceFileShareSMBUsedInDealIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();
        CommonSearchQueryResponseModel responseSearchQueryCreationBody = DataGeneratorDealService.createBasicSearchQuery();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreationBody.id),
                Collections.singletonList(responseSearchQueryCreationBody.id)
        );


        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec409Conflict());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.deleteSearchPlace(responseSearchPlaceCreationBody.id).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ENTITY_IN_USE_EXCEPTION_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.message).isEqualTo(EXCEPTION_ENTITY_IN_USE_EXCEPTION_SEARCH_PLACE_INFO);
        assertThat(responseErrorBody.data.key).isEqualTo(responseSearchPlaceCreationBody.id);
        assertThat(responseErrorBody.data.type).isEqualTo(SEARCH_PLACE_INFO);

    }


    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность удалить место поиска ARM - Local")
    @Description("Тест проверяет невозможность удалить место поиска ARM - Local")
    public void testDeleteSearchPlaceARMLocalIsImpossible(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseSearchPlaceCreationBody = DataGeneratorDealService.createBasicSearchPlaceArmLocal();


        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());
        ErrorModel responseErrorBody = ApiMethodsSearchPlace.deleteSearchPlace(responseSearchPlaceCreationBody.id).as(ErrorModel.class);

        assertThat(responseErrorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(responseErrorBody.message).isEqualTo(DELETING_SEARCH_PLACES_FOR_LOCAL_AGENT_IS_FORBIDDEN);

    }

}
