package eDiscovery.tests.apiTests.extendedPositiveTests.adminService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@DisplayName("Extended positive tests: Admin - Employee")
public class EmployeeExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Admin")
    @Feature("Пользователь")
    @Story("Получение списка пользователей")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка сотрудников для фильтра по сотрудникам")
    @Description("Тест проверяет возможность получения списка сотрудников для фильтра по сотрудникам")
    public void testGetDealListODataForDealManipulationList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonDealManipulationResponseModel> responseBody = ApiMethodsEmployee.getEmployeeListOData(parameters).jsonPath().getList("value", CommonDealManipulationResponseModel.class);
    }

}
