package eDiscovery.tests.apiTests.commonPositiveTests.identityService;

import eDiscovery.TestBase;

import eDiscovery.apiMethods.identity.ApiMethodsIdentity;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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

        ApiMethodsIdentity.connectUserInfo();
    }
}
