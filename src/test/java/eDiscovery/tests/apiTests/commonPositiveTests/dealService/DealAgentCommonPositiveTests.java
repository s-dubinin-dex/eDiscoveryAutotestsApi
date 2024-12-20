package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.data.dealService.DataGeneratorDealAgent;
import eDiscovery.helpers.enums.HostType;
import eDiscovery.models.deal.dealAgent.RegisterAgentRequestModel;
import eDiscovery.models.deal.dealAgent.RegisterAgentResponseModel;
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

            RegisterAgentResponseModel responseBody = ApiMethodsDealAgent.registerAgent(requestBody).as(RegisterAgentResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
            assertThat(responseBody.userName).isEqualTo(requestBody.userName);
            assertThat(responseBody.machineName).isEqualTo(requestBody.machineName);
            assertThat(responseBody.agentType).isEqualTo(requestBody.hostType);
            assertThat(responseBody.osVersion).isEqualTo(requestBody.osVersion);
            assertThat(responseBody.agentVersion).isEqualTo(requestBody.agentVersion);
            assertThat(isValidUUID(responseBody.searchPlaceId)).isTrue();
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

            RegisterAgentResponseModel responseBody = ApiMethodsDealAgent.registerAgent(requestBody).as(RegisterAgentResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
            assertThat(responseBody.userName).matches("Cloud_Agent _.*");
            assertThat(responseBody.machineName).isEqualTo(requestBody.machineName);
            assertThat(responseBody.agentType).isEqualTo(requestBody.hostType);
            assertThat(responseBody.osVersion).isEqualTo(requestBody.osVersion);
            assertThat(responseBody.agentVersion).isEqualTo(requestBody.agentVersion);
            assertThat(responseBody.searchPlaceId).isNull();
            assertThat(responseBody.lastActivity).isNull();
        }

    }

}
