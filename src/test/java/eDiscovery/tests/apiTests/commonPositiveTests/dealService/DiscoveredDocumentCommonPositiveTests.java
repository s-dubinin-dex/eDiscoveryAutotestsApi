package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDiscoveredDocument;
import eDiscovery.models.deal.discoveredDocument.DiscoveredDocumentModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Common positive tests - DiscoveredDocument")
public class DiscoveredDocumentCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка обнаруженных документов")
    @Description("Тест проверяет возможность получения списка обнаруженных документов")
    public void testGetDiscoveredDocumentList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocument().jsonPath().getList("", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка обнаруженных документов по протоколу odata")
    @Description("Тест проверяет возможность получения списка обнаруженных документов по протоколу odata")
    public void testGetDiscoveredDocumentListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData().jsonPath().getList("value", DiscoveredDocumentModel.class);
    }
}
