package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealProgress;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealProgress.DealProgressModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - DealProgress")
public class DealProgressExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по делу")
    @Story("Получение прогресса по делу")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение прогресса по делу для ручного обновления в карточке дела")
    @Description("Тест проверяет возможность получения прогресса по делу для ручного обновления в карточке дела")
    public void testGetDealProgressForUpdateInDealManipulationCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("id eq %s", responseDealCreation.id))
                .withExpand("progressInfo")
                .build();

        DealProgressModel responseBody = ApiMethodsDealProgress.getDealProgressListOData(parameters).jsonPath().getList("value", DealProgressModel.class).get(0);

        assertThat(responseBody.id).isEqualTo(responseDealCreation.id);
        assertThat(responseBody.progressInfo).isNull();
    }
}
