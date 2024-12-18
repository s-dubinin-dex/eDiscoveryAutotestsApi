package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsClassifyDocument;
import eDiscovery.models.classifier.classifyDocument.ClassifyDocumentRequestBuilder;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

@DisplayName("Common positive tests: Classifier - ClassifyDocument")
public class ClassifyDocumentCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Категоризация документа docx через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ docx через внешнее API категоризации")
    public void testClassifyDocxDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                // ToDo: после слива с release/v0.22 заменить хардкод ProfileId на создание активного ProfileId
                .withProfileId("8e9b11ea-086d-49a4-8d93-538f4942cd38")
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/1. Microsoft Word.docx"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

}
