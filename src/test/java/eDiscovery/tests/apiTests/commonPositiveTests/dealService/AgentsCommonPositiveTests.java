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

@DisplayName("Common positive tests - Agents")
public class AgentsCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Агенты")
    @Story("Получение списка агентов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка агентов")
    @Description("Тест проверяет возможность получения списка агентов")
    public void testGetAgents(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsList().jsonPath().getList("", CommonAgentsResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }
}
