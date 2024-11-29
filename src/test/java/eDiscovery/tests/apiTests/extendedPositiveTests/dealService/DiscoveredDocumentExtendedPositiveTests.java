package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDiscoveredDocument;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.discoveredDocument.DiscoveredDocumentModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DisplayName("Extended positive tests: Deal - DiscoveredDocument")
public class DiscoveredDocumentExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Документы")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Документы")
    public void testGetDiscoveredDocumentForDealCardDocumentsTab(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$filter", String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason eq null", responseBodyDealManipulation.id));
        parameters.put("$expand", "SearchPlace");
        parameters.put("$orderby", "findedUtc desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Обнаруженные документы")
    @Story("Получение списка обнаруженных документов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка обнаруженных документов для карточки дела для карточки дела - список Необработанные")
    @Description("Тест проверяет возможность получения списка обнаруженных документов  - список Необработанные")
    public void testGetDiscoveredDocumentForDealCardUnprocessedTab(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealManipulation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$filter", String.format("((contains(tolower(fileName),'')) or (contains(tolower(filePath),'')) or (contains(tolower(innerFileName),'')) or (contains(tolower(innerFilePath),''))) and dealId eq %s and searchProblemReason ne null", responseBodyDealManipulation.id));
        parameters.put("$expand", "SearchPlace");
        parameters.put("$orderby", "findedUtc desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");

        List<DiscoveredDocumentModel> responseBody = ApiMethodsDiscoveredDocument.getDiscoveredDocumentOData(parameters).jsonPath().getList("value", DiscoveredDocumentModel.class);
    }
}
