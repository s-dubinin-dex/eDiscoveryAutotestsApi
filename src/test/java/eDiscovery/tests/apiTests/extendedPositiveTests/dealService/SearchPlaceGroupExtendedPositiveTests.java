package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlaceGroup;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@DisplayName("Extended positive tests: Deal - SearchPlaceGroup")
public class SearchPlaceGroupExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка групп мест поиска для отображения в списке групп мест поиска")
    @Description("Тест проверяет возможность получения списка групп мест для отображения в списке групп мест поиска")
    @Test
    public void testGetSearchPlaceGroupListWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchPlaces")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .withIncludeDeleted(true)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }
}
