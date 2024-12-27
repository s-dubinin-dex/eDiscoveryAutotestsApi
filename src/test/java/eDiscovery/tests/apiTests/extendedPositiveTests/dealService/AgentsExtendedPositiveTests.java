package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsAgents;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.data.dealService.DataGeneratorDealAgent;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.agents.CommonAgentsResponseModel;
import eDiscovery.models.deal.dealAgent.activeTasks.ActiveTasksRequestsModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - Agents")
public class AgentsExtendedPositiveTests extends TestBase {

    private static CommonAgentsResponseModel AGENT_BODY_TO_CHECK;
    private static ActiveTasksRequestsModel ACTIVE_TASK_REQUEST_BODY;

    private static String BEGIN_PERIOD = LocalDate.now().toString();
    private static String END_PERIOD = LocalDate.now().plusDays(2).toString();


    @BeforeAll
    public static void setUp(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithLocalAgentAuthorization());
        AGENT_BODY_TO_CHECK = DataGeneratorDealAgent.createDealAgentWithOnlyRequiredParametersLocal();

        ACTIVE_TASK_REQUEST_BODY = ActiveTasksRequestsModel.builder()
                .agentId(AGENT_BODY_TO_CHECK.id)
                .ipAddress(faker.internet().ipV4Address())
                .build();

        ApiMethodsDealAgent.getActiveTasks(ACTIVE_TASK_REQUEST_BODY);
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
        @DisplayName("Получение списка агентов возвращает agentVersionString")
        @Description("Тест проверяет, что получение списка агентов возвращает agentVersionString")
        public void testGetAgentListReturnsAgentVersionString(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.agentVersionString).isEqualTo(AGENT_BODY_TO_CHECK.agentVersionString);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает searchPlace")
        @Description("Тест проверяет, что получение списка агентов возвращает searchPlace")
        public void testGetAgentListReturnsSearchPlace(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("lastActivity,searchPlace")
                    .build();

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.searchPlace).isNotNull();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов возвращает createdUtc")
        @Description("Тест проверяет, что получение списка агентов возвращает createdUtc")
        public void testGetAgentListReturnsCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentsListOdata().jsonPath().getList("value", CommonAgentsResponseModel.class)
                    .stream()
                    .filter(e -> e.id.equals(AGENT_BODY_TO_CHECK.id))
                    .findFirst().orElse(null);

            assertThat(responseBody.createdUtc).isNotBlank();
            assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
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
        @DisplayName("Получение агента по id возвращает agentVersionString")
        @Description("Тест проверяет, что получение агента по id возвращает agentVersionString")
        public void testGetAgentByIdReturnsAgentVersionString(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.agentVersionString).isEqualTo(AGENT_BODY_TO_CHECK.agentVersionString);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает searchPlace")
        @Description("Тест проверяет, что получение агента по id возвращает searchPlace")
        public void testGetAgentByIdReturnsSearchPlace(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withExpand("lastActivity,searchPlace")
                    .build();

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id, params).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.searchPlace).isNotNull();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение агента по id возвращает createdUtc")
        @Description("Тест проверяет, что получение агента по id возвращает createdUtc")
        public void testGetAgentByIdReturnsCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseBody = ApiMethodsAgents.getAgentByIdPath(AGENT_BODY_TO_CHECK.id).as(CommonAgentsResponseModel.class);

            assertThat(responseBody.createdUtc).isNotBlank();
            assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
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

    @Nested
    @DisplayName("Проверка работы методов с UI для страницы списка агентов")
    class CheckGetAgentListUIMethods{

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке")
        public void testGetAgentListForWebUi(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Issue("ED-919")
        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с фильтром по периоду регистрации")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с фильтром по периоду регистрации")
        public void testGetAgentListForWebUiWithFilterCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("((contains(tolower(userName),'')) or (contains(tolower(machineName),''))) and createdUtc ge %s and createdUtc le %s", BEGIN_PERIOD, END_PERIOD))
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с фильтром по периоду последней активности")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с фильтром по периоду последней активности")
        public void testGetAgentListForWebUiWithFilterRequestDateTime(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("((contains(tolower(userName),'')) or (contains(tolower(machineName),''))) and lastActivity/requestDateTime ge %s and lastActivity/requestDateTime le %s", BEGIN_PERIOD, END_PERIOD))
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с фильтром по типу агента")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с фильтром по типу агента")
        public void testGetAgentListForWebUiWithFilterAgentType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),''))) and agentType eq 'Local'")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Issue("ED-919")
        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с сортировкой по дате регистрации")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с сортировкой по дате регистрации")
        public void testGetAgentListForWebUiWithSortingCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withOrderBy("createdUtc ASC")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с сортировкой по имени машины")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с сортировкой по имени машины")
        public void testGetAgentListForWebUiWithSortingMachineName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withOrderBy("machineName ASC")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с сортировкой по последней активности")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с сортировкой по последней активности")
        public void testGetAgentListForWebUiWithSortingRequestDateTime(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withOrderBy("lastActivity/requestDateTime ASC")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с сортировкой по операционной системе")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с сортировкой по операционной системе")
        public void testGetAgentListForWebUiWithSortingOsVersion(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withOrderBy("osVersion ASC")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с сортировкой по типу агента")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с сортировкой по типу агента")
        public void testGetAgentListForWebUiWithSortingАgentType(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withOrderBy("agentType ASC")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

        @Issue("ED-919")
        @Test
        @Epic("Сервис Deal")
        @Feature("Агенты")
        @Story("Получение списка агентов")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка агентов для отображения в списке с сортировкой по версии агента")
        @Description("Тест проверяет возможность получения списка агентов для отображения в списке с сортировкой по версии агента")
        public void testGetAgentListForWebUiWithSortingAgentVersion(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter("((contains(tolower(userName),'')) or (contains(tolower(machineName),'')))")
                    .withOrderBy("agentVersion ASC")
                    .withExpand("lastActivity")
                    .build();

            List<CommonAgentsResponseModel> responseBody = ApiMethodsAgents.getAgentsListOdata(params).jsonPath().getList("value" ,CommonAgentsResponseModel.class);
        }

    }

}
