package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsAgentActivity;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.data.dealService.DataGeneratorDealAgent;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.agentActivity.CommonAgentActivityResponseModel;
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

@DisplayName("Common positive tests: Deal - AgentActivity")
public class AgentActivityExtendedPositiveTests extends TestBase {

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
    @DisplayName("Проверка заполнения полей Deal - AgentActivity в теле ответа получении активности агента по id агента")
    class CheckAgentActivityFieldsByAgentId{

        @Test
        @Epic("Сервис Deal")
        @Feature("Активность агентов")
        @Story("Получение активности агента")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка активности агента возвращает id")
        @Description("Тест проверяет, что при получении списка активности агента возвращает id")
        public void testGetAgentActivityByAgentIdReturnsId() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("agentId eq %s", AGENT_BODY_TO_CHECK.id))
                    .build();

            CommonAgentActivityResponseModel responseBody = ApiMethodsAgentActivity.getAgentActivityListOdata(params).jsonPath().getList("value", CommonAgentActivityResponseModel.class).get(0);

            assertThat(isValidUUID(responseBody.id)).isTrue();
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Активность агентов")
        @Story("Получение активности агента")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка активности агента возвращает agentId")
        @Description("Тест проверяет, что при получении списка активности агента возвращает agentId")
        public void testGetAgentActivityByAgentIdReturnsAgentId() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("agentId eq %s", AGENT_BODY_TO_CHECK.id))
                    .build();

            CommonAgentActivityResponseModel responseBody = ApiMethodsAgentActivity.getAgentActivityListOdata(params).jsonPath().getList("value", CommonAgentActivityResponseModel.class).get(0);

            assertThat(responseBody.agentId).isEqualTo(AGENT_BODY_TO_CHECK.id);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Активность агентов")
        @Story("Получение активности агента")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка активности агента возвращает ipAddress")
        @Description("Тест проверяет, что при получении списка активности агента возвращает ipAddress")
        public void testGetAgentActivityByAgentIdReturnsIpAddress() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("agentId eq %s", AGENT_BODY_TO_CHECK.id))
                    .build();

            CommonAgentActivityResponseModel responseBody = ApiMethodsAgentActivity.getAgentActivityListOdata(params).jsonPath().getList("value", CommonAgentActivityResponseModel.class).get(0);

            assertThat(responseBody.ipAddress).isEqualTo(ACTIVE_TASK_REQUEST_BODY.ipAddress);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Активность агентов")
        @Story("Получение активности агента")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка активности агента возвращает requestDateTime")
        @Description("Тест проверяет, что при получении списка активности агента возвращает requestDateTime")
        public void testGetAgentActivityByAgentIdReturnsRequestDateTime() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withFilter(String.format("agentId eq %s", AGENT_BODY_TO_CHECK.id))
                    .build();

            CommonAgentActivityResponseModel responseBody = ApiMethodsAgentActivity.getAgentActivityListOdata(params).jsonPath().getList("value", CommonAgentActivityResponseModel.class).get(0);

            assertThat(responseBody.requestDateTime).matches(dateTimeISOPattern());
        }

    }

}
