package eDiscovery.tests.apiTests.commonPositiveTestsApi.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPlaceCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет возможность создания места поиска")
    public void testAddSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = DataGeneratorDealService.getSearchPlaceModelWithOnlyRequiredParameters();

        Response response = ApiMethodsSearchPlace.addSearchPlace(requestBody);
        CommonSearchPlaceResponseModel responseBody = response.as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.categoryType).isEqualTo(SearchPlaceCategoryType.Undefined);
        assertThat(responseBody.type).isEqualTo(SearchPlaceType.Undefined);
        assertThat(responseBody.parameters).isEqualTo(null);
        assertThat(responseBody.excludes).isEmpty();
        assertThat(responseBody.excludes).isInstanceOf(ArrayList.class);
        assertThat(responseBody.id).isNotBlank();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Изменение места поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение места поиска")
    @Description("Тест проверяет возможность изменения места поиска")
    public void testUpdateSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = DataGeneratorDealService.getBasicSearchPlaceModelFileShareSMB();
        Response response = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation);
        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = response.as(CommonSearchPlaceResponseModel.class);

        UpdateSearchPlaceRequestModel requestBodySearchPlaceUpdate = UpdateSearchPlaceRequestModel.builder()
                .id(responseBodySearchPlaceCreation.id)
                .name(faker.letterify("???????????????????"))
                .build();

        Response responseSearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestBodySearchPlaceUpdate);
        UpdateSearchPlaceRequestModel responseBodySearchPlaceUpdate = responseSearchPlaceUpdate.as(UpdateSearchPlaceRequestModel.class);

        assertThat(responseBodySearchPlaceUpdate.categoryType).isEqualTo(SearchPlaceCategoryType.Undefined);
        assertThat(responseBodySearchPlaceUpdate.type).isEqualTo(SearchPlaceType.Undefined);
        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(null);
        assertThat(responseBodySearchPlaceUpdate.excludes).isEmpty();
        assertThat(responseBodySearchPlaceUpdate.excludes).isInstanceOf(ArrayList.class);
        assertThat(responseBodySearchPlaceUpdate.id).isNotBlank();
        assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(requestBodySearchPlaceUpdate.name);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Удаление места поиска")
    @Description("Тест проверяет возможность удаления места поиска")
    public void testDeleteSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = DataGeneratorDealService.createSearchPlaceWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchPlace.deleteSearchPlace(responseBodySearchPlaceCreation.id);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска")
    @Description("Тест проверяет возможность получения списка мест поиска")
    public void testGetSearchPlaceList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchPlaceWithOnlyRequiredParameters();
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
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealService.createSearchPlaceWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchPlace.getSearchPlaceListOData();

        List<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchPlaceResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

}
