package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsAgentActivity;
import eDiscovery.models.deal.agentActivity.CommonAgentActivityResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Common positive tests: Deal - AgentActivity")
public class AgentActivityCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Активность агентов")
    @Story("Получение списка активности агентов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка активности агентов")
    @Description("Тест проверяет возможность получения списка активности агентов")
    public void testGetAgentActivity(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsAgentActivity.getAgentActivityList().jsonPath().getList("", CommonAgentActivityResponseModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Активность агентов")
    @Story("Получение списка активности агентов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка активности агентов по протоколу oData")
    @Description("Тест проверяет возможность получения списка активности агентов по протоколу oData")
    public void testGetAgentActivityOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsAgentActivity.getAgentActivityListOdata().jsonPath().getList("value", CommonAgentActivityResponseModel.class);
    }

}
