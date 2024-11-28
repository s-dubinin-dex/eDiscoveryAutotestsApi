package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealTask;
import eDiscovery.models.deal.dealTask.DealTaskModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests - DealTask")
public class DealTaskCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка задач")
    @Description("Тест проверяет возможность получения списка задач")
    public void testGetDealTaskList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskList().jsonPath().getList("", DealTaskModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка задач по протоколу odata")
    @Description("Тест проверяет возможность получения списка задач по протоколу odata")
    public void testGetDealTaskListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData().jsonPath().getList("value", DealTaskModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }
}
