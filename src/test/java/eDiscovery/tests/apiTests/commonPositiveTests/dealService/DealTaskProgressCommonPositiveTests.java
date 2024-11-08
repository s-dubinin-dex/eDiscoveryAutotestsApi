package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealTaskProgress;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Common positive tests - DealTaskProgress")
public class DealTaskProgressCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по задачам")
    @Story("Получение списка прогрессов по задачам")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка прогрессов по задачам")
    @Description("Тест проверяет возможность получения списка прогрессов по задачам")
    public void testGetDealTaskProgressList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DealTaskProgressModel> responseBody = ApiMethodsDealTaskProgress.getDealTaskProgressList().jsonPath().getList("", DealTaskProgressModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по задачам")
    @Story("Получение списка прогрессов по задачам")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка прогрессов по задачам по протоколу odata")
    @Description("Тест проверяет возможность получения списка прогрессов по задачам по протоколу odata")
    public void testGetDealTaskProgressListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DealTaskProgressModel> responseBody = ApiMethodsDealTaskProgress.getDealTaskProgressListOData().jsonPath().getList("value", DealTaskProgressModel.class);

    }
}
