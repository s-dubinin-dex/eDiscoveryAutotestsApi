package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.SearchPlaceParametersModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - SearchPlace")
public class SearchPlaceCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет возможность создания места поиска")
    public void testAddSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение места поиска")
    @Description("Тест проверяет возможность изменения места поиска")
    public void testUpdateSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = DataGeneratorSearchPlace.getBasicSearchPlaceModelFileShareSMB();
        Response response = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation);
        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = response.as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestBodySearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(getRandomName())
                .categoryType(SearchPlaceCategoryType.ARM.name())
                .type(SearchPlaceType.LOCAL.name())
                .build();

        Response responseSearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestBodySearchPlaceUpdate);
        CommonSearchPlaceResponseModel responseBodySearchPlaceUpdate = responseSearchPlaceUpdate.as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodySearchPlaceUpdate.categoryType).isEqualTo(SearchPlaceCategoryType.ARM.name());
        assertThat(responseBodySearchPlaceUpdate.type).isEqualTo(SearchPlaceType.LOCAL.name());
        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(null);
        assertThat(responseBodySearchPlaceUpdate.excludes).isEmpty();
        assertThat(responseBodySearchPlaceUpdate.excludes).isInstanceOf(ArrayList.class);
        assertThat(isValidUUID(responseBodySearchPlaceUpdate.id)).isTrue();
        assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(requestBodySearchPlaceUpdate.name);
        assertThat(responseBodySearchPlaceUpdate.createdUtc).matches(dateTimeUTCPattern());
        assertThat(responseBodySearchPlaceUpdate.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Удаление места поиска")
    @Description("Тест проверяет возможность удаления места поиска")
    public void testDeleteSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchPlace.deleteSearchPlace(responseBodySearchPlaceCreation.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.getSearchPlaceODataByIdPath(responseBodySearchPlaceCreation.id).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.categoryType).isEqualTo(SearchPlaceCategoryType.FileShare.name());
        assertThat(responseBody.type).isEqualTo(SearchPlaceType.SMB.name());
        assertThat(responseBody.parameters).isEqualTo(null);
        assertThat(responseBody.excludes).isEmpty();
        assertThat(responseBody.excludes).isInstanceOf(ArrayList.class);
        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(responseBodySearchPlaceCreation.name);
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).matches(dateTimeISOPattern());
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска")
    @Description("Тест проверяет возможность получения списка мест поиска")
    public void testGetSearchPlaceList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchPlace.getSearchPlaceList();

        List<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().getList("", CommonSearchPlaceResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска по протоколу oData")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData")
    public void testGetSearchPlaceListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchPlace.getSearchPlaceListOData(new HashMap<>());

        List<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchPlaceResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение места поиска по протоколу oData по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение места поиска по протоколу oData по id")
    @Description("Тест проверяет возможность получения места поиска по протоколу oData по id в скобках")
    public void testGetSearchPlaceODataById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        String searchPlaceNameForFilter = "testSearchPlaceODataByIdInRoundBrackets" + getRandomName();

        SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                .uri(faker.internet().url())
                .username(faker.name().username())
                .password(faker.internet().password())
                .build();

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(searchPlaceNameForFilter)
                .categoryType(SearchPlaceCategoryType.FileShare.name())
                .type(SearchPlaceType.SMB.name())
                .excludes(List.of("C:\\", "D:\\"))
                .parameters(parameters)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        CommonSearchPlaceResponseModel responseBodyODataById = ApiMethodsSearchPlace.getSearchPlaceODataById(responseBody.id).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodyODataById.id).isEqualTo(responseBody.id);
        assertThat(responseBodyODataById.name).isEqualTo(responseBody.name);
        assertThat(responseBodyODataById.type).isEqualTo(responseBody.type);
        assertThat(responseBodyODataById.categoryType).isEqualTo(responseBody.categoryType);
        assertThat(responseBodyODataById.parameters).usingRecursiveComparison().isEqualTo(responseBody.parameters);
        assertThat(responseBodyODataById.excludes).isEqualTo(responseBody.excludes);
        assertThat(responseBodyODataById.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyODataById.deletedUtc).isNull();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Получение места поиска по протоколу oData по id")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение места поиска по протоколу oData по id")
    @Description("Тест проверяет возможность получения места поиска по протоколу oData по id в path param")
    public void testGetSearchPlaceODataByIdInPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        String searchPlaceNameForFilter = "testSearchPlaceODataByIdInRoundBrackets" + getRandomName();

        SearchPlaceParametersModel parameters = SearchPlaceParametersModel.builder()
                .uri(faker.internet().url())
                .username(faker.name().username())
                .password(faker.internet().password())
                .build();

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(searchPlaceNameForFilter)
                .categoryType(SearchPlaceCategoryType.FileShare.name())
                .type(SearchPlaceType.SMB.name())
                .excludes(List.of("C:\\", "D:\\"))
                .parameters(parameters)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        CommonSearchPlaceResponseModel responseBodyODataById = ApiMethodsSearchPlace.getSearchPlaceODataByIdPath(responseBody.id).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBodyODataById.id).isEqualTo(responseBody.id);
        assertThat(responseBodyODataById.name).isEqualTo(responseBody.name);
        assertThat(responseBodyODataById.type).isEqualTo(responseBody.type);
        assertThat(responseBodyODataById.categoryType).isEqualTo(responseBody.categoryType);
        assertThat(responseBodyODataById.parameters).usingRecursiveComparison().isEqualTo(responseBody.parameters);
        assertThat(responseBodyODataById.excludes).isEqualTo(responseBody.excludes);
        assertThat(responseBodyODataById.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyODataById.deletedUtc).isNull();

    }

}
