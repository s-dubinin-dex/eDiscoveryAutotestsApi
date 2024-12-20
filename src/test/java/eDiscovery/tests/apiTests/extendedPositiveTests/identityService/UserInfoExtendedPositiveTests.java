package eDiscovery.tests.apiTests.extendedPositiveTests.identityService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.identity.ApiMethodsIdentity;
import eDiscovery.models.identity.UserInfo;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Identity - UserInfo")
public class UserInfoExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Проверка заполнения полей Identity - UserInfo в теле ответа при получении информации о текущем пользователе")
    class CheckUserInfoResponseFields {

        @Test
        @Epic("Сервис Identity")
        @Feature("Connect")
        @Story("Информация о пользователе")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение имени текущего пользователе")
        @Description("Тест проверяет возможность получения имени текущего пользователе")
        public void testGetUserInfoReturnsName() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            UserInfo responseBody = ApiMethodsIdentity.connectUserInfo().as(UserInfo.class);

            assertThat(responseBody.name).isEqualTo("Администратор");
        }

        @Test
        @Epic("Сервис Identity")
        @Feature("Connect")
        @Story("Информация о пользователе")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение прав текущего пользователе")
        @Description("Тест проверяет возможность получения прав текущего пользователе")
        public void testGetUserInfoReturnsPolicies() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            UserInfo responseBody = ApiMethodsIdentity.connectUserInfo().as(UserInfo.class);

            assertThat(responseBody.policy).isEqualTo("ed.fullAccess");
        }

        @Test
        @Epic("Сервис Identity")
        @Feature("Connect")
        @Story("Информация о пользователе")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение id текущего пользователе")
        @Description("Тест проверяет возможность получения id текущего пользователе")
        public void testGetUserInfoReturnsId() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            UserInfo responseBody = ApiMethodsIdentity.connectUserInfo().as(UserInfo.class);

            assertThat(responseBody.sub).isEqualTo("8dd105b2-7e41-40c4-8700-022403dfcdc6");
        }

    }
}
