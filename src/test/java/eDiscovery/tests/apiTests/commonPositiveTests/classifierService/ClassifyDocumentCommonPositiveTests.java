package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

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

@DisplayName("Common positive tests: Classifier - ClassifyDocument")
public class ClassifyDocumentCommonPositiveTests extends TestBase {

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Категоризация документа docx через внешнее API категоризации")
    @Description("Тест проверяет возможность категоризировать документ docx через внешнее API категоризации")
    public void testClassifyDocxDocument() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200BinaryBody());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId(ACTIVE_PROFILE.id)
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/1. Microsoft Word.docx"))
                .build();

        ApiMethodsClassifyDocument.classifyDocument(params);
    }

}
