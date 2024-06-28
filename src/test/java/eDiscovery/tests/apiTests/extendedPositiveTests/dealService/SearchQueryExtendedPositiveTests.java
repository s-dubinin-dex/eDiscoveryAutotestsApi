package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchQueryExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными наименованиями")
    @Description("Тест проверяет возможность создания поискового запроса c различными наименованиями")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryNames")
    public void testAddSearchQueryWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(name)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными type")
    @Description("Тест проверяет возможность создания поискового запроса c различными type")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryTypes")
    public void testAddSearchQueryWithDifferentValidTypes(SearchQueryType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .type(type)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.addSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создание поискового запроса c различными values")
    @Description("Тест проверяет возможность создания поискового запроса c различными values")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryValues")
    public void testAddSearchQueryWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .value(value)
                .build();
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования в поисковом запросе")
    @Description("Тест проверяет возможность изменения наименования в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryNames")
    public void testUpdateSearchQueryWithDifferentValidNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(name)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение наименования в поисковом запросе, который используется в деле")
    @Description("Тест проверяет возможность изменения наименования в поисковом запросе, который используется в деле")
    public void testUpdateSearchQueryNameInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreation.id),
                Collections.singletonList(responseSearchQueryCreation.id)
        );

        String nameForUpdate = DataGeneratorDealService.getRandomName();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(nameForUpdate)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
        assertThat(responseBody.name).isEqualTo(nameForUpdate);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение type в поисковом запросе")
    @Description("Тест проверяет возможность изменения type в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryTypes")
    public void testUpdateSearchQueryWithDifferentValidTypes(SearchQueryType type){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(type)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

        assertThat(responseBody.type).isEqualTo(type);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение type в поисковом запросе, который используется в деле")
    @Description("Тест проверяет возможность изменения type в поисковом запросе, который используется в деле")
    public void testUpdateSearchQueryTypeInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreation.id),
                Collections.singletonList(responseSearchQueryCreation.id)
        );

        SearchQueryType typeForUpdating = SearchQueryType.Text;

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .type(typeForUpdating)
                .value("abc")
                .build();

        CommonSearchQueryResponseModel responseBody = ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
        assertThat(responseBody.type).isEqualTo(typeForUpdating);
    }

    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение value в поисковом запросе")
    @Description("Тест проверяет возможность изменения value в поисковом запросе")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchQueryValues")
    public void testUpdateSearchQueryWithDifferentValidValues(String value){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .value(value)
                .build();

        ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Поисковый запрос")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение value в поисковом запросе, который используется в деле")
    @Description("Тест проверяет возможность изменения value в поисковом запросе, который используется в деле")
    public void testUpdateSearchQueryValueInCreatedDeal(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryResponseModel responseSearchQueryCreation = DataGeneratorDealService.createBasicSearchQuery();
        CommonSearchPlaceResponseModel responseSearchPlaceCreation = DataGeneratorDealService.createBasicSearchPlaceFileShareSMB();

        DataGeneratorDealService.createDealManipulationWithOnlyRequiredParameters(
                Collections.singletonList(responseSearchPlaceCreation.id),
                Collections.singletonList(responseSearchQueryCreation.id)
        );

        UpdateSearchQueryRequestModel requestBody = UpdateSearchQueryRequestModel.builder()
                .id(responseSearchQueryCreation.id)
                .name(responseSearchQueryCreation.name)
                .value(DataGeneratorDealService.getRandomName())
                .build();

        ApiMethodsSearchQuery.updateSearchQuery(requestBody).as(CommonSearchQueryResponseModel.class);
    }
}
