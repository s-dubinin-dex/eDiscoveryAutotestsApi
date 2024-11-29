package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsDealStatusHistory;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealStatusHistory.DealStatusHistoryModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - DealStatusHistory")
public class DealStatusHistoryExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка изменений статусов по протоколу odata для карточки дела")
    @Description("Тест проверяет возможность получения списка изменений статусов по протоколу odata для карточки дела")
    public void testGetDealStatusHistoryListForDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$filter", String.format("dealId eq %s", responseBodyDealCreation.id));
        parameters.put("$orderby", "changedStatusUtc desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryListOData(parameters).jsonPath().getList("value", DealStatusHistoryModel.class);

        assertThat(responseBody).hasSize(1);
        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).dealId).isEqualTo(responseBodyDealCreation.id);
        assertThat(responseBody.get(0).dealStatus).isEqualTo(DealStatus.Running.name());
        assertThat(isValidUUID(responseBody.get(0).changedStatusUserId)).isTrue();
        assertThat(responseBody.get(0).changedStatusUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.get(0).changedStatusUserName).isEqualTo("Администратор");
    }
}
