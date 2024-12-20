package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsAgents;
import eDiscovery.models.deal.agents.CommonAgentsResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - Agents")
public class AgentsCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Агенты")
    @Story("Получение списка агентов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка агентов")
    @Description("Тест проверяет возможность получения списка агентов")
    public void testGetAgentsList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsList().jsonPath().getList("", CommonAgentsResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Агенты")
    @Story("Получение списка агентов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка агентов по протоколу oData")
    @Description("Тест проверяет возможность получения списка агентов по протоколу oData")
    public void testGetAgentsListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

}
