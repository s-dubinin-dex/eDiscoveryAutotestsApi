package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorDealService;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

        AddSearchQueryRequestModel requestBody = AddSearchQueryRequestModel.builder()
                .name(DataGeneratorDealService.getRandomName())
                .value(value)
                .build();
    }
}
