package eDiscovery.tests.apiTests.negativeTestsWithInvalidData.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsClassifyDocument;
import eDiscovery.models.ErrorModel;
import eDiscovery.models.classifier.classifyDocument.ClassifyDocumentRequestBuilder;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static eDiscovery.helpers.ErrorDescription.*;

@DisplayName("Negative tests with invalid data: Classifier - ClassifyDocument")
public class ClassifyDocumentNegativeTestsWithInvalidDataTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность категоризации документа txt через внешнее API категоризации")
    @Description("Тест проверяет невозможность категоризировать документ txt через внешнее API категоризации")
    public void testClassifyTxtDocumentIsImpossible() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId("8e9b11ea-086d-49a4-8d93-538f4942cd38")
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/8. Текстовый документ.txt"))
                .build();

        ErrorModel errorBody = ApiMethodsClassifyDocument.classifyDocument(params).as(ErrorModel.class);

        assertThat(errorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(errorBody.errors.file.get(0).errorCode).isEqualTo(VALIDATIONS_UNSUPPORTABLE_DOCUMENT_FORMAT_FOR_CATEGORIZATION);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность категоризации документа csv через внешнее API категоризации")
    @Description("Тест проверяет невозможность категоризировать документ csv через внешнее API категоризации")
    public void testClassifyCsvDocumentIsImpossible() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId("8e9b11ea-086d-49a4-8d93-538f4942cd38")
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/9. CSV документ.csv"))
                .build();

        ErrorModel errorBody = ApiMethodsClassifyDocument.classifyDocument(params).as(ErrorModel.class);

        assertThat(errorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(errorBody.errors.file.get(0).errorCode).isEqualTo(VALIDATIONS_UNSUPPORTABLE_DOCUMENT_FORMAT_FOR_CATEGORIZATION);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Невозможность категоризации архива zip через внешнее API категоризации")
    @Description("Тест проверяет невозможность категоризировать архив zip через внешнее API категоризации")
    public void testClassifyZipArchiveIsImpossible() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                .withProfileId("8e9b11ea-086d-49a4-8d93-538f4942cd38")
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/10. ZIP архив.zip"))
                .build();

        ErrorModel errorBody = ApiMethodsClassifyDocument.classifyDocument(params).as(ErrorModel.class);

        assertThat(errorBody.title).isEqualTo(REQUEST_VALIDATION_ERROR);
        assertThat(errorBody.errors.file.get(0).errorCode).isEqualTo(VALIDATIONS_UNSUPPORTABLE_DOCUMENT_FORMAT_FOR_CATEGORIZATION);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Категоризация")
    @Story("Внешнее API категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Невозможность категоризации документа docx через внешнее API категоризации c неактивным профилем категоризациии")
    @Description("Тест проверяет невозможность категоризировать документ docx через внешнее API категоризации c неактивным профилем категоризациии")
    public void testClassifyDocumentWithNotActiveProfileIsImpossible() {
        SpecificationsServer.installRequestSpecification(RequestSpecifications.multipartRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpec400BadRequest());

        Map<String, Object> params = ClassifyDocumentRequestBuilder.builder()
                // ToDo: после слива с release/v0.22 заменить хардкод ProfileId на создание неактивного ProfileId
                .withProfileId("bc06fac7-343c-4656-9416-5987dc45f681")
                .withFile(new File("src/test/resources/testDocuments/categorization/differentFileExtensions/1. Microsoft Word.docx"))
                .build();

        ErrorModel errorBody = ApiMethodsClassifyDocument.classifyDocument(params).as(ErrorModel.class);

        assertThat(errorBody.type).isEqualTo(ERRORS_ARGUMENT_EXCEPTION);
        assertThat(errorBody.message).isEqualTo(IMPOSSIBLE_MARK_DOCUMENT_WITH_NOT_ACTIVE_PROFILE);
    }

}
