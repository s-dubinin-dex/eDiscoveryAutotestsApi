package eDiscovery.tests.apiTests.commonPositiveTests.identityService;

import eDiscovery.TestBase;

import eDiscovery.apiMethods.identity.ApiMethodsIdentity;
import eDiscovery.models.identity.UserInfo;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Identity - UserInfo")
public class UserInfoCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Identity")
    @Feature("Connect")
    @Story("Информация о пользователе")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение информации о текущем пользователе")
    @Description("Тест проверяет возможность получения информации о текущем пользователе")
    public void testGetUserInfo(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        UserInfo responseBody = ApiMethodsIdentity.connectUserInfo().as(UserInfo.class);

        assertThat(responseBody.name).isEqualTo("Администратор");
        assertThat(responseBody.policy).isEqualTo("ed.fullAccess");
        assertThat(responseBody.sub).isEqualTo("8dd105b2-7e41-40c4-8700-022403dfcdc6");
    }
}
