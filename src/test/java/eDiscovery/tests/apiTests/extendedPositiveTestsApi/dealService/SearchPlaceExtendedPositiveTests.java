package eDiscovery.tests.apiTests.extendedPositiveTestsApi.dealService;
import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.SearchPlaceParametersModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

}
