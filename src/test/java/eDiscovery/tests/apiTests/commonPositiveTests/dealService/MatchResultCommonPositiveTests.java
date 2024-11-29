package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsMatchResult;
import eDiscovery.models.deal.matchResult.MatchResultResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Common positive tests: Deal - MatchResult")
public class MatchResultCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Получение количества совпадений поисковых запросов")
    @Story("Получение количества совпадений поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение количества совпадений поисковых запросов")
    @Description("Тест проверяет возможность получения количества совпадений поисковых запросов")
    public void testGetMatchResultList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<MatchResultResponseModel> responseBody = ApiMethodsMatchResult.getMatchResultList().jsonPath().getList("", MatchResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Получение количества совпадений поисковых запросов")
    @Story("Получение количества совпадений поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение количества совпадений поисковых запросов")
    @Description("Тест проверяет возможность получения количества совпадений поисковых запросов по протоколу odata")
    public void testGetMatchResultListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<MatchResultResponseModel> responseBody = ApiMethodsMatchResult.getMatchResultList().jsonPath().getList("value", MatchResultResponseModel.class);

    }
}
