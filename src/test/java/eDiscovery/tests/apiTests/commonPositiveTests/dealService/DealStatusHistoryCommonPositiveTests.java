package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealStatusHistory;
import eDiscovery.models.deal.dealStatusHistory.DealStatusHistoryModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - DealStatusHistory")
public class DealStatusHistoryCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка изменений статусов")
    @Description("Тест проверяет возможность получения списка изменений статусов")
    public void testGetStatusHistoryList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryList().jsonPath().getList("", DealStatusHistoryModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка изменений статусов по протоколу odata")
    @Description("Тест проверяет возможность получения списка изменений статусов по протоколу odata")
    public void testGetDealStatusHistoryListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryListOData().jsonPath().getList("value", DealStatusHistoryModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

}
