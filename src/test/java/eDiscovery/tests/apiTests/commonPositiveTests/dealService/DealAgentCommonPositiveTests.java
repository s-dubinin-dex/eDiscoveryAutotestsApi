package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.data.dealService.DataGeneratorDealAgent;
import eDiscovery.models.deal.agents.CommonAgentsResponseModel;
import eDiscovery.models.deal.dealAgent.activeTasks.ActiveTasksRequestsModel;
import eDiscovery.models.deal.dealAgent.activeTasks.ActiveTasksResponseModel;
import eDiscovery.models.deal.dealAgent.registerAgent.RegisterAgentRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - DealAgent")
public class DealAgentCommonPositiveTests extends TestBase {

    @Nested
    @DisplayName("Проверка получения задач агентом")
    class CheckDealAgentGetActiveTasks{

        @Test
        @Epic("Сервис Deal")
        @Feature("Получение задач агентом")
        @Story("Получение задач агентом")
        @Severity(SeverityLevel.BLOCKER)
        @DisplayName("Получение задач агентом")
        @Description("Тест проверяет возможность получения задач агентом")
        public void testDealAgentGetsActiveTasks(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithLocalAgentAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonAgentsResponseModel responseAgentCreationBody = DataGeneratorDealAgent.createDealAgentWithOnlyRequiredParametersLocal();

            ActiveTasksRequestsModel activeTasksRequestBody = ActiveTasksRequestsModel.builder()
                    .agentId(responseAgentCreationBody.id)
                    .idPlace(responseAgentCreationBody.searchPlace.id)
                    .build();

            ActiveTasksResponseModel activeTasksResponseBody = ApiMethodsDealAgent.getActiveTasks(activeTasksRequestBody).as(ActiveTasksResponseModel.class);

            assertThat(activeTasksResponseBody.tasksSettings.maxCpuUsagePercentage).isEqualTo(33);
            assertThat(activeTasksResponseBody.tasksSettings.maxArchiveDepth).isEqualTo(3);

            assertThat(activeTasksResponseBody.tasks).isEmpty();
        }

    }

    @Nested
    @DisplayName("Проверка первичной регистрации агентов")
    class CheckDealAgentInitialRegistration{

        @Test
        @Epic("Сервис Deal")
        @Feature("Регистрация агента")
        @Story("Регистрация агента")
        @Severity(SeverityLevel.BLOCKER)
        @DisplayName("Первичная регистрация локального агента")
        @Description("Тест проверяет возможность первичной регистрации локального агента")
        public void testLocalAgentInitialRegistration(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithLocalAgentAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            RegisterAgentRequestModel requestBody = DataGeneratorDealAgent.getDealAgentModelWithOnlyRequiredParametersLocal();

            CommonAgentsResponseModel responseBody = ApiMethodsDealAgent.registerAgent(requestBody).as(CommonAgentsResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
            assertThat(responseBody.userName).isEqualTo(requestBody.userName);
            assertThat(responseBody.machineName).isEqualTo(requestBody.machineName);
            assertThat(responseBody.agentType).isEqualTo(requestBody.hostType);
            assertThat(responseBody.osVersion).isEqualTo(requestBody.osVersion);
            assertThat(responseBody.agentVersion).isEqualTo(300);
            assertThat(responseBody.agentVersionString).isEqualTo(requestBody.agentVersion);
            assertThat(isValidUUID(responseBody.searchPlace.id)).isTrue();
            assertThat(responseBody.lastActivity).isNull();
        }


        @Test
        @Epic("Сервис Deal")
        @Feature("Регистрация агента")
        @Story("Регистрация агента")
        @Severity(SeverityLevel.BLOCKER)
        @DisplayName("Первичная регистрация облачного агента")
        @Description("Тест проверяет возможность первичной регистрации облачного агента")
        public void testCloudAgentInitialRegistration(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithCloudAgentAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            RegisterAgentRequestModel requestBody = DataGeneratorDealAgent.getDealAgentModelWithOnlyRequiredParametersCloud();

            CommonAgentsResponseModel responseBody = ApiMethodsDealAgent.registerAgent(requestBody).as(CommonAgentsResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
            assertThat(responseBody.userName).matches("Cloud_Agent _.*");
            assertThat(responseBody.machineName).isEqualTo(requestBody.machineName);
            assertThat(responseBody.agentType).isEqualTo(requestBody.hostType);
            assertThat(responseBody.osVersion).isEqualTo(requestBody.osVersion);
            assertThat(responseBody.agentVersion).isEqualTo(300);
            assertThat(responseBody.agentVersionString).isEqualTo(requestBody.agentVersion);
            assertThat(responseBody.searchPlace).isNull();
            assertThat(responseBody.lastActivity).isNull();
        }

    }

}
