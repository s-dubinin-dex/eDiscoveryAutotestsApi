package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;
import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.SearchPlaceParametersModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorDealService.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchPlaceExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными наименованиями")
    @Description("Тест проверяет возможность создания места поиска c различными наименованиями")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceNames")
    public void testAddSearchPlaceWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(name)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными categoryType")
    @Description("Тест проверяет возможность создания места поиска c различными c различными categoryType")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceCategoryTypes")
    public void testAddSearchPlaceWithDifferentValidCategoryTypes(SearchPlaceCategoryType categoryType){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .categoryType(categoryType)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.categoryType).isEqualTo(categoryType);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными type")
    @Description("Тест проверяет возможность создания места поиска c различными c type")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceTypes")
    public void testAddSearchPlaceWithDifferentValidTypes(SearchPlaceType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .type(type)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными uri в параметрах подключения")
    @Description("Тест проверяет возможность создания места поиска c различными uri в параметрах подключения")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceURIInParameters")
    public void testAddSearchPlaceWithDifferentValidUriInParameters(String uri){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                .uri(uri)
                .username("userName")
                .password("p@$$w0rd")
                .build();

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .parameters(parameters)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.parameters).isEqualTo(parameters);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными username в параметрах подключения")
    @Description("Тест проверяет возможность создания места поиска c различными username в параметрах подключения")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceUsernamesInParameters")
    public void testAddSearchPlaceWithDifferentValidUsernamesInParameters(String username){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                .uri("\\\\192.168.1.1\\share")
                .username(username)
                .password("p@$$w0rd")
                .build();

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .parameters(parameters)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.parameters).isEqualTo(parameters);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными password в параметрах подключения")
    @Description("Тест проверяет возможность создания места поиска c различными password в параметрах подключения")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlacePasswordsInParameters")
    public void testAddSearchPlaceWithDifferentValidPasswordsInParameters(String password){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                .uri("\\\\192.168.1.1\\share")
                .username("userName")
                .password(password)
                .build();

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .parameters(parameters)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.parameters).isEqualTo(parameters);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различными исключениями директорий из поиска")
    @Description("Тест проверяет возможность создания места поиска c различными исключениями директорий из поиска")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceExclusions")
    public void testAddSearchPlaceWithDifferentExcludes(String exclusion){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<String> excludes = Collections.singletonList(exclusion);

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .excludes(excludes)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.excludes).isEqualTo(excludes);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание места поиска c различным количеством исключений директорий из поиска")
    @Description("Тест проверяет возможность создания места поиска c различным количеством исключений директорий из поиска")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceExclusionsWithDifferentCount")
    public void testAddSearchPlaceWithDifferentCountOfExcludes(List<String> excludes){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(getRandomName())
                .excludes(excludes)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.excludes).isEqualTo(excludes);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования места поиска с типом FileShare - SMB")
    @Description("Тест проверяет возможность изменения наименования места поиска с типом FileShare - SMB")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceNames")
    public void testUpdateSearchPlaceNameWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .name(name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .id(responseBodySearchPlaceCreation.id)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(name);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования места поиска с типом FileShare - SMB, которое используется в созданном деле")
    @Description("Тест проверяет возможность изменения наименования места поиска с типом FileShare - SMB, которое используется в созданном деле")
    public void testUpdateSearchPlaceNameUsedInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        ApiMethodsDealManipulation.addDeal(DataGeneratorDealService.getBasicDealManipulationModel(
                Collections.singletonList(responseBodySearchPlaceCreation.id),
                Collections.singletonList(responseBodySearchQueryCreation.id)
        ));

        String newName = DataGeneratorDealService.getRandomName();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .name(newName)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .id(responseBodySearchPlaceCreation.id)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(newName);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение categoryType в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения categoryType в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
    @ParameterizedTest
    @EnumSource(SearchPlaceCategoryType.class)
    public void testUpdateSearchPlaceCategoryTypeInFileShareWithoutDeal(SearchPlaceCategoryType categoryType){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .id(responseBodySearchPlaceCreation.id)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.categoryType).isEqualTo(categoryType);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение type в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения type в месте поиска с categoryType = FileShare, если место поиска не используется в деле")
    @ParameterizedTest
    @EnumSource(SearchPlaceType.class)
    public void testUpdateSearchPlaceTypeInFileShareWithoutDeal(SearchPlaceType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .id(responseBodySearchPlaceCreation.id)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.type).isEqualTo(type);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение uri в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения uri в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceURIInParameters")
    public void testUpdateSearchPlaceUriParametersWithDifferentValidValues(String uri){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel initialParameters = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username("userName")
                .password("p@$$w0rd")
                .build();

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .parameters(initialParameters)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        SearchPlaceParametersModel parametersForUpdate = SearchPlaceParametersModel.builder()
                .uri(uri)
                .username("userName")
                .password("p@$$w0rd")
                .build();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(parametersForUpdate)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(parametersForUpdate);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение username в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения username в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceUsernamesInParameters")
    public void testUpdateSearchPlaceUsernameParametersWithDifferentValidValues(String username){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel initialParameters = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username("userName")
                .password("p@$$w0rd")
                .build();

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .parameters(initialParameters)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        SearchPlaceParametersModel parametersForUpdate = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username(username)
                .password("p@$$w0rd")
                .build();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(parametersForUpdate)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(parametersForUpdate);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение password в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения password в параметрах места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlacePasswordsInParameters")
    public void testUpdateSearchPlacePasswordParametersWithDifferentValidValues(String password){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel initialParameters = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username("userName")
                .password("p@$$w0rd")
                .build();

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .parameters(initialParameters)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        SearchPlaceParametersModel parametersForUpdate = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username("userName")
                .password(password)
                .build();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(parametersForUpdate)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(parametersForUpdate);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение параметров места поиска с типом FileShare - SMB, которое используется в созданном деле")
    @Description("Тест проверяет возможность изменения параметров места поиска с типом FileShare - SMB, которое используется в созданном деле")
    public void testUpdateSearchPlaceParametersUsedInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        SearchPlaceParametersModel initialParameters = SearchPlaceParametersModel.builder()
                .uri("ur1")
                .username("userName")
                .password("p@$$w0rd")
                .build();

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .parameters(initialParameters)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        ApiMethodsDealManipulation.addDeal(DataGeneratorDealService.getBasicDealManipulationModel(
                Collections.singletonList(responseBodySearchPlaceCreation.id),
                Collections.singletonList(responseBodySearchQueryCreation.id)
        ));

        SearchPlaceParametersModel parametersForUpdate = SearchPlaceParametersModel.builder()
                .uri("ur12")
                .username("userName2")
                .password("p@$$w0rd2")
                .build();

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(parametersForUpdate)
                .excludes(responseBodySearchPlaceCreation.excludes)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(parametersForUpdate);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение excludes места поиска с типом ARM - Local, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения excludes в месте поиска с типом ARM - Local, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceExclusions")
    public void testUpdateSearchPlaceExcludesInARMLocalWithDifferentValidValues(String exclusion){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .excludes(Collections.singletonList("A:\\"))
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        List<String> excludes = Collections.singletonList(exclusion);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение excludes места поиска с типом ARM - Local на разное количество исключений, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения excludes в месте поиска с типом ARM - Local на разное количество исключений, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceExclusionsWithDifferentCount")
    public void testUpdateSearchPlaceExcludesInARMLocalWithDifferentCountOfExclusion(List<String> excludes){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .excludes(Collections.singletonList("A:\\"))
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение excludes места поиска с типом ARM - Local, которое используется в созданном деле")
    @Description("Тест проверяет возможность изменения excludes места поиска с типом ARM - Local, которое используется в созданном деле")
    public void testUpdateSearchPlaceExcludesInARMLocalInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestSearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM)
                .type(SearchPlaceType.LOCAL)
                .excludes(Collections.singletonList("A:\\"))
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestSearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        ApiMethodsDealManipulation.addDeal(DataGeneratorDealService.getBasicDealManipulationModel(
                Collections.singletonList(responseBodySearchPlaceCreation.id),
                Collections.singletonList(responseBodySearchQueryCreation.id)
        ));

        List<String> excludesUpdate = Collections.singletonList("B:\\");

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .name(requestSearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(excludesUpdate)
                .id(responseBodySearchPlaceCreation.id)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludesUpdate);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение excludes места поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения excludes в месте поиска с типом FileShare - SMB, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceExclusions")
    public void testUpdateSearchPlaceExcludesInFileShareSMBWithDifferentValidValues(String exclusion){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .excludes(Collections.singletonList("A:\\"))
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        List<String> excludes = Collections.singletonList(exclusion);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение excludes места поиска с типом FileShare - SMB на разное количество исключений, если место поиска не используется в деле")
    @Description("Тест проверяет возможность изменения excludes в месте поиска с типом FileShare - SMB на разное количество исключений, если место поиска не используется в деле")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceExclusionsWithDifferentCount")
    public void testUpdateSearchPlaceExcludesInFileShareSMBWithDifferentCountOfExclusion(List<String> excludes){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .excludes(Collections.singletonList("A:\\"))
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(responseBodySearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(excludes)
                .build();


        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludes);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение excludes места поиска с типом FileShare - SMB, которое используется в созданном деле")
    @Description("Тест проверяет возможность изменения excludes места поиска с типом FileShare - SMB, которое используется в созданном деле")
    public void testUpdateSearchPlaceExcludesInFileShareSMBWithDifferentValidValuesInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestSearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .excludes(Collections.singletonList("A:\\"))
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestSearchPlaceCreation).as(CommonSearchPlaceResponseModel.class);
        CommonSearchQueryResponseModel responseBodySearchQueryCreation = DataGeneratorDealService.createSearchQueryWithOnlyRequiredParameters();

        ApiMethodsDealManipulation.addDeal(DataGeneratorDealService.getBasicDealManipulationModel(
                Collections.singletonList(responseBodySearchPlaceCreation.id),
                Collections.singletonList(responseBodySearchQueryCreation.id)
        ));

        List<String> excludesUpdate = Collections.singletonList("B:\\");

        UpdateSearchPlaceRequestModel requestSearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .name(requestSearchPlaceCreation.name)
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(responseBodySearchPlaceCreation.parameters)
                .excludes(excludesUpdate)
                .id(responseBodySearchPlaceCreation.id)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestSearchPlaceUpdate).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.excludes).isEqualTo(excludesUpdate);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Удаление места поиска FileShare - SMB")
    @Description("Тест проверяет возможность удаления места поиска FileShare - SMB")
    public void testDeleteSearchPlaceFileShareSMB(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .build();

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchPlace.deleteSearchPlace(responseBodySearchPlaceCreation.id);
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.MINOR)
    @Link("http://jira.lan:8080/browse/ED-203")
    @DisplayName("Получение списка мест поиска с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @Description("Тест проверяет возможность получения списка мест поиска с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    public void testGetSearchPlaceListWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchPlaceWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchPlace.getSearchPlaceListWithIncludeDeletedParameter(includeDeleted);

        List<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().getList("", CommonSearchPlaceResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.MINOR)
    @Link("http://jira.lan:8080/browse/ED-203")
    @DisplayName("Получение списка мест поиска по протоколу oData с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData с параметром includeDeleted (параметр includeDeleted не влияет на выдачу результата)")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    public void testGetSearchPlaceListODataWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchPlaceWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchPlace.getSearchPlaceListODataWithIncludeDeletedParameter(includeDeleted);

        List<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchPlaceResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска по протоколу oData с фильтрацией результата")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData с фильтрацией результата")
    @Test
    public void testGetSearchPlaceListODataWithFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchPlaceWithOnlyRequiredParameters();

        String searchPlaceNameForFilter = "testSearchPlaceListODataWithFilter" + DataGeneratorDealService.getRandomName();

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(searchPlaceNameForFilter)
                .build();
        ApiMethodsSearchPlace.addSearchPlace(requestBody);


        List<CommonSearchPlaceResponseModel> responseBodyWithoutFilter =
                ApiMethodsSearchPlace.getSearchPlaceListOData().jsonPath().getList("value", CommonSearchPlaceResponseModel.class);

        assertThat(responseBodyWithoutFilter.size()).isGreaterThan(1);
        assertThat(responseBodyWithoutFilter.get(0)).isNotNull();

        List<CommonSearchPlaceResponseModel> responseBodyWithFilter =
                ApiMethodsSearchPlace.getSearchPlaceListODataWithOneParameter("$filter", "contains(name, '" + searchPlaceNameForFilter + "')")
                        .jsonPath().getList("value", CommonSearchPlaceResponseModel.class);
        assertThat(responseBodyWithFilter.size()).isEqualTo(1);
        assertThat(responseBodyWithFilter.get(0)).isNotNull();
    }


}
