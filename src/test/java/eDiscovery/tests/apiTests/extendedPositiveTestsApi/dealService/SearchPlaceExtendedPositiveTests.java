package eDiscovery.tests.apiTests.extendedPositiveTestsApi.dealService;
import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPlaceExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Место поиска")
    @Story("Создание места поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание места поиска c различными наименованиями")
    @Description("Тест проверяет возможность создания места поиска c различными наименованиями")
    @ParameterizedTest
    @MethodSource("eDiscovery.data.DataGeneratorDealService#getValidSearchPlaceNames")
    public void testAddSearchPlaceWithDifferentNames(String name){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceRequestModel requestBody = AddSearchPlaceRequestModel.builder()
                .name(name)
                .build();

        CommonSearchPlaceResponseModel responseBody = ApiMethodsSearchPlace.addSearchPlace(requestBody).as(CommonSearchPlaceResponseModel.class);

        assertThat(responseBody.name).isEqualTo(name);
    }
}
