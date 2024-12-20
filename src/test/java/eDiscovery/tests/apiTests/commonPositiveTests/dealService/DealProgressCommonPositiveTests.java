package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealProgress;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealProgress.DealProgressModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - DealProgress")
public class DealProgressCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по делу")
    @Story("Получение списка прогрессов по делу")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка прогрессов по делу")
    @Description("Тест проверяет возможность получения списка прогрессов по делу")
    public void testGetDealProgressList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        List<DealProgressModel> responseBody = ApiMethodsDealProgress.getDealProgressList().jsonPath().getList("", DealProgressModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по делу")
    @Story("Получение списка прогрессов по делу")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка прогрессов по делу по протоколу odata")
    @Description("Тест проверяет возможность получения списка прогрессов по делу по протоколу odata")
    public void testGetDealProgressListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        List<DealProgressModel> responseBody = ApiMethodsDealProgress.getDealProgressListOData().jsonPath().getList("value", DealProgressModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по делу")
    @Story("Получение прогресса по id дела")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение прогресса по id дела")
    @Description("Тест проверяет возможность полученить прогресс по id дела path param")
    public void testGetDealProgressByID(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        DealProgressModel responseBody = ApiMethodsDealProgress.getDealProgressByID(responseDealCreation.id).as(DealProgressModel.class);

        assertThat(responseBody.id).isEqualTo(responseDealCreation.id);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Прогресс по делу")
    @Story("Получение прогресса по id дела")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение прогресса по id дела")
    @Description("Тест проверяет возможность полученить прогресс по id дела path param")
    public void testGetDealProgressByIDPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        DealProgressModel responseBody = ApiMethodsDealProgress.getDealProgressByIDPath(responseDealCreation.id).as(DealProgressModel.class);

        assertThat(responseBody.id).isEqualTo(responseDealCreation.id);
    }

}
