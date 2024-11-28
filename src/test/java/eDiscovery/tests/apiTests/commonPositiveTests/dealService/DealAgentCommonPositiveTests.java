package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.helpers.enums.HostType;
import eDiscovery.models.deal.dealAgent.RegisterAgentRequestModel;
import eDiscovery.models.deal.dealAgent.RegisterAgentResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests - DealAgent")
public class DealAgentCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Регистрация / Авторизация поисковых агентов")
    @Story("Регистрация агентов")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация локального агента")
    @Description("Тест проверяет возможность первичной регистрации локального агента")
    public void testLocalAgentInitialRegistration(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithLocalAgentAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        RegisterAgentRequestModel requestBody = RegisterAgentRequestModel.builder()
                .userName(getRandomName())
                .machineName(getRandomName())
                .hostType(HostType.Local.name())
                .build();

        RegisterAgentResponseModel responseBody = ApiMethodsDealAgent.registerAgent(requestBody).as(RegisterAgentResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.userName).isEqualTo(requestBody.userName);
        assertThat(responseBody.machineName).isEqualTo(requestBody.machineName);
        assertThat(responseBody.osVersion).isNull();
        assertThat(isValidUUID(responseBody.searchPlaceId)).isTrue();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Регистрация / Авторизация поисковых агентов")
    @Story("Регистрация агентов")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация облачного агента")
    @Description("Тест проверяет возможность первичной регистрации облачного агента")
    public void testCloudAgentInitialRegistration(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithCloudAgentAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        RegisterAgentRequestModel requestBody = RegisterAgentRequestModel.builder()
                .userName(getRandomName())
                .machineName(getRandomName())
                .hostType(HostType.Cloud.name())
                .build();

        RegisterAgentResponseModel responseBody = ApiMethodsDealAgent.registerAgent(requestBody).as(RegisterAgentResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.userName).matches("Cloud_Agent _.*");
        assertThat(responseBody.machineName).isEqualTo("Cloud");
        assertThat(responseBody.osVersion).isNull();
        assertThat(responseBody.searchPlaceId).isNull();
    }
}
