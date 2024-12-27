package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsAgents;
import eDiscovery.data.dealService.DataGeneratorDealAgent;
import eDiscovery.models.deal.agents.CommonAgentsResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Common positive tests: Deal - Agents")
public class AgentsCommonPositiveTests extends TestBase {

    private static CommonAgentsResponseModel AGENT_BODY_TO_CHECK;

    @BeforeAll
    public static void setUp(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithLocalAgentAuthorization());

        AGENT_BODY_TO_CHECK = DataGeneratorDealAgent.createDealAgentWithOnlyRequiredParametersLocal();
    }

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

        ApiMethodsAgents.getAgentsList();
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

        ApiMethodsAgents.getAgentsListOdata();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Агенты")
    @Story("Получение списка агентов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение агента по id")
    @Description("Тест проверяет возможность получения агента по id")
    public void testGetAgentById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsAgents.getAgentById(AGENT_BODY_TO_CHECK.id);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Агенты")
    @Story("Получение списка агентов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение агента по id")
    @Description("Тест проверяет возможность получения агента по id в pathParam")
    public void testGetAgentByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id);
    }

}
