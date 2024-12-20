package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsAgents;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.data.dealService.DataGeneratorDealAgent;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.agents.CommonAgentsResponseModel;
import eDiscovery.models.deal.dealAgent.activeTasks.ActiveTasksRequestsModel;
import eDiscovery.models.deal.dealAgent.activeTasks.ActiveTasksResponseModel;
import eDiscovery.models.deal.dealAgent.registerAgent.RegisterAgentResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - Agents")
public class AgentsExtendedPositiveTests extends TestBase {

    private static RegisterAgentResponseModel AGENT_BODY_TO_CHECK;
    private static ActiveTasksRequestsModel ACTIVE_TASK_REQUEST_BODY;

    @BeforeAll
    public static void setUp(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithLocalAgentAuthorization());
        AGENT_BODY_TO_CHECK = DataGeneratorDealAgent.createDealAgentWithOnlyRequiredParametersLocal();

        ACTIVE_TASK_REQUEST_BODY = ActiveTasksRequestsModel.builder()
                .agentId(AGENT_BODY_TO_CHECK.id)
                .ipAddress(faker.internet().ipV4Address())
                .build();

        ApiMethodsDealAgent.getActiveTasks(ACTIVE_TASK_REQUEST_BODY).as(ActiveTasksResponseModel.class);
    }

    @Nested
    @DisplayName("Проверка заполнения полей Deal - Agent в теле ответа при получении списка агентов")
    class CheckGetAgentListResponseFields{

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает id")
        @Description("Тест проверяет, что получение списка агентов возвращает id")
        public void testGetAgentListReturnsId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.id).isEqualTo(AGENT_BODY_TO_CHECK.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает userName")
        @Description("Тест проверяет, что получение списка агентов возвращает userName")
        public void testGetAgentListReturnsUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.userName).isEqualTo(AGENT_BODY_TO_CHECK.userName);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает machineName")
        @Description("Тест проверяет, что получение списка агентов возвращает machineName")
        public void testGetAgentListReturnsMachineName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.machineName).isEqualTo(AGENT_BODY_TO_CHECK.machineName);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает agentType")
        @Description("Тест проверяет, что получение списка агентов возвращает agentType")
        public void testGetAgentListReturnsAgentType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.agentType).isEqualTo(AGENT_BODY_TO_CHECK.agentType);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает osVersion")
        @Description("Тест проверяет, что получение списка агентов возвращает osVersion")
        public void testGetAgentListReturnsOsVersion(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.osVersion).isEqualTo(AGENT_BODY_TO_CHECK.osVersion);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает agentVersion")
        @Description("Тест проверяет, что получение списка агентов возвращает agentVersion")
        public void testGetAgentListReturnsAgentVersion(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.agentVersion).isEqualTo(AGENT_BODY_TO_CHECK.agentVersion);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает searchPlaceId")
        @Description("Тест проверяет, что получение списка агентов возвращает searchPlaceId")
        public void testGetAgentListReturnsSearchPlaceId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.searchPlaceId).isEqualTo(AGENT_BODY_TO_CHECK.searchPlaceId);
        }

    }

    @Nested
    @DisplayName("Проверка заполнения полей Deal - Agent в теле ответа при получении информации о агенте по id")
    class CheckGetAgentByIdResponseFields{

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает id")
        @Description("Тест проверяет, что получение агента по id возвращает id")
        public void testGetAgentByIdReturnsId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.id).isEqualTo(AGENT_BODY_TO_CHECK.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает userName")
        @Description("Тест проверяет, что получение агента по id возвращает userName")
        public void testGetAgentByIdReturnsUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.userName).isEqualTo(AGENT_BODY_TO_CHECK.userName);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает machineName")
        @Description("Тест проверяет, что получение агента по id возвращает machineName")
        public void testGetAgentByIdReturnsMachineName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.machineName).isEqualTo(AGENT_BODY_TO_CHECK.machineName);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает agentType")
        @Description("Тест проверяет, что получение агента по id возвращает agentType")
        public void testGetAgentByIdReturnsAgentType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.agentType).isEqualTo(AGENT_BODY_TO_CHECK.agentType);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает osVersion")
        @Description("Тест проверяет, что получение агента по id возвращает osVersion")
        public void testGetAgentByIdReturnsOsVersion(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.osVersion).isEqualTo(AGENT_BODY_TO_CHECK.osVersion);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает agentVersion")
        @Description("Тест проверяет, что получение агента по id возвращает agentVersion")
        public void testGetAgentByIdReturnsAgentVersion(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.agentVersion).isEqualTo(AGENT_BODY_TO_CHECK.agentVersion);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает searchPlaceId")
        @Description("Тест проверяет, что получение агента по id возвращает searchPlaceId")
        public void testGetAgentByIdReturnsSearchPlaceId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.searchPlaceId).isEqualTo(AGENT_BODY_TO_CHECK.searchPlaceId);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает lastActivity - id")
        @Description("Тест проверяет, что получение агента по id возвращает lastActivity - id")
        public void testGetAgentByIdReturnsLastActivityId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("lastActivity")
                    .build();

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id, params).as(CommonAgentsResponseModel.class);

            assertThat(isValidUUID(responseBody.lastActivity.id)).isTrue();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает lastActivity - agentId")
        @Description("Тест проверяет, что получение агента по id возвращает lastActivity - agentId")
        public void testGetAgentByIdReturnsLastActivityAgentId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("lastActivity")
                    .build();

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id, params).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.lastActivity.agentId).isEqualTo(AGENT_BODY_TO_CHECK.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает lastActivity - ipAddress")
        @Description("Тест проверяет, что получение агента по id возвращает lastActivity - ipAddress")
        public void testGetAgentByIdReturnsLastActivityIpAddress(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("lastActivity")
                    .build();

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id, params).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.lastActivity.ipAddress).isEqualTo(ACTIVE_TASK_REQUEST_BODY.ipAddress);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает lastActivity - requestDateTime")
        @Description("Тест проверяет, что получение агента по id возвращает lastActivity - requestDateTime")
        public void testGetAgentByIdReturnsLastActivityRequestDateTime(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("lastActivity")
                    .build();

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id, params).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.lastActivity.requestDateTime).matches(dateTimeISOPattern());
        }

    }

}
