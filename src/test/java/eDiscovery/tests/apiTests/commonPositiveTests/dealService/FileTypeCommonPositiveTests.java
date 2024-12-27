package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsFileType;
import eDiscovery.data.dealService.DataGeneratorFileType;
import eDiscovery.models.deal.fileType.FileTypeResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - FileTypes")
public class FileTypeCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Типы файлов")
    @Story("Получение списка типов файлов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка типов файлов")
    @Description("Тест проверяет возможность получения списка типов файлов")
    public void testGetFileTypesList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<FileTypeResponseModel> responseBody = ApiMethodsFileType.getFileTypeList().jsonPath().getList("", FileTypeResponseModel.class);

        assertThat(responseBody).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(DataGeneratorFileType.getEtalonFileTypes());

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Типы файлов")
    @Story("Получение списка типов файлов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка типов файлов по протоколу oData")
    @Description("Тест проверяет возможность получения списка типов файлов по протоколу oData")
    public void testGetFileTypesListOdata(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<FileTypeResponseModel> responseBody = ApiMethodsFileType.getFileTypeListOData().jsonPath().getList("value", FileTypeResponseModel.class);

        assertThat(responseBody).usingRecursiveComparison().ignoringFields("fileExtensions").isEqualTo(DataGeneratorFileType.getEtalonFileTypes());

    }

}
