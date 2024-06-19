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
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Создание места поиска")
    @Description("Тест проверяет возможность создания места поиска")
    public void testAddSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = DataGeneratorDealService.getBasicSearchPlaceModelArmLocal();

        Response response = ApiMethodsSearchPlace.addSearchPlace(requestBody);
        CommonSearchPlaceResponseModel responseBody = response.as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.categoryType).isEqualTo(requestBody.categoryType);
        assertThat(responseBody.type).isEqualTo(requestBody.type);
        assertThat(responseBody.parameters).isEqualTo(requestBody.parameters);
        assertThat(responseBody.excludes).isEmpty();
        assertThat(responseBody.excludes).isInstanceOf(ArrayList.class);
        assertThat(responseBody.id).isNotBlank();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
    }

    @Test
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
                .categoryType(responseBodySearchPlaceCreation.categoryType)
                .type(responseBodySearchPlaceCreation.type)
                .parameters(null)
                .excludes(null)
                .id(responseBodySearchPlaceCreation.id)
                .name(faker.letterify("???????????????????"))
                .build();

        Response responseSearchPlaceUpdate = ApiMethodsSearchPlace.updateSearchPlace(requestBodySearchPlaceUpdate);
        UpdateSearchPlaceRequestModel responseBodySearchPlaceUpdate = responseSearchPlaceUpdate.as(UpdateSearchPlaceRequestModel.class);

        assertThat(responseBodySearchPlaceUpdate.categoryType).isEqualTo(requestBodySearchPlaceUpdate.categoryType);
        assertThat(responseBodySearchPlaceUpdate.type).isEqualTo(requestBodySearchPlaceUpdate.type);
        assertThat(responseBodySearchPlaceUpdate.parameters).isEqualTo(requestBodySearchPlaceUpdate.parameters);
        assertThat(responseBodySearchPlaceUpdate.excludes).isEmpty();
        assertThat(responseBodySearchPlaceUpdate.excludes).isInstanceOf(ArrayList.class);
        assertThat(responseBodySearchPlaceUpdate.id).isNotBlank();
        assertThat(responseBodySearchPlaceUpdate.name).isEqualTo(requestBodySearchPlaceUpdate.name);
    }

    @Test
    @Feature("Место поиска")
    @Story("Удаление места поиска")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Удаление места поиска")
    @Description("Тест проверяет возможность удаления места поиска")
    public void testDeleteSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());

        AddSearchPlaceRequestModel requestBodySearchPlaceCreation = AddSearchPlaceRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .categoryType(SearchPlaceCategoryType.FileShare)
                .type(SearchPlaceType.SMB)
                .parameters(null)
                .excludes(null)
                .build();
        Response responseSearchPlaceCreation = ApiMethodsSearchPlace.addSearchPlace(requestBodySearchPlaceCreation);
        CommonSearchPlaceResponseModel responseBodySearchPlaceCreation = responseSearchPlaceCreation.as(CommonSearchPlaceResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchPlace.deleteSearchPlace(responseBodySearchPlaceCreation.id);
    }

    @Test
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска")
    @Description("Тест проверяет возможность получения списка мест поиска")
    public void testGetSearchPlaceList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsSearchPlace.addSearchPlace(DataGeneratorDealService.getBasicSearchPlaceModelArmLocal());
        Response response = ApiMethodsSearchPlace.getSearchPlaceList();

        List<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().getList("");
        assertThat(responseBody).isNotEmpty();
    }

    @Test
    @Feature("Место поиска")
    @Story("Получение списка мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка мест поиска по протоколу oData")
    @Description("Тест проверяет возможность получения списка мест поиска по протоколу oData")
    public void testGetSearchPlaceListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsSearchPlace.addSearchPlace(DataGeneratorDealService.getBasicSearchPlaceModelArmLocal());
        Response response = ApiMethodsSearchPlace.getSearchPlaceListOData();

        ArrayList<CommonSearchPlaceResponseModel> responseBody = response.jsonPath().get("value");
        assertThat(responseBody).isNotEmpty();
    }

}
