package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsClassifyDocument;
import eDiscovery.data.classifierService.DataGeneratorProfile;
import eDiscovery.models.classifier.classifyDocument.ClassifyDocumentRequestBuilder;
import eDiscovery.models.classifier.profile.CommonProfileResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

@DisplayName("Extended positive tests: Classifier - ClassifyDocument")
public class ClassifyDocumentExtendedPositiveTests extends TestBase {

    private static CommonProfileResponseModel ACTIVE_PROFILE;

    @BeforeAll
    public static void setUp(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        ACTIVE_PROFILE = DataGeneratorProfile.createActiveProfileWithOnlyRequiredParameters();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа xlsx через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ xlsx через внешнее API категоризации")
    public void testClassifyXlsxDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/2. Microsoft Excel.xlsx"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа pptx через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ pptx через внешнее API категоризации")
    public void testClassifyPPtxDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/3. Презентация Microsoft PowerPoint.pptx"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа odt через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ odt через внешнее API категоризации")
    public void testClassifyOdtDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/4. Текстовый документ OpenDocument.odt"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа ods через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ ods через внешнее API категоризации")
    public void testClassifyOdsDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/5. Электронная таблица OpenDocument.ods"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа odp через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ odp через внешнее API категоризации")
    public void testClassifyOdpDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/6. Презентация OpenDocument.odp"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа pdf через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ pdf через внешнее API категоризации")
    public void testClassifyPdfDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/7. PDF.pdf"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Категоризация документа через внешнее API категоризации без передачи профиля категоризации (должен использоваться профиль по умолчанию)")
    @Description("Тест проверяет возможность категоризировать документ через внешнее API категоризации без передачи профиля категоризации (должен использоваться профиль по умолчанию)")
    public void testClassifyDocxDocumentWithDefaultProfile() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/1. Microsoft Word.docx"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }
}
