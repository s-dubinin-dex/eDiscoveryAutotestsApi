package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsFileExtension;
import eDiscovery.data.dealService.DataGeneratorFileExtension;
import eDiscovery.models.deal.fileExtensions.FileExtensionResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - FileExtension")
public class FileExtensionCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Расширения файлов")
    @Story("Получение списка расширений файлов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка расширений файлов")
    @Description("Тест проверяет возможность получения списка расширений файлов")
    public void testGetFileExtensionList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<FileExtensionResponseModel> responseBody = ApiMethodsFileExtension.getFileExtensionList().jsonPath().getList("", FileExtensionResponseModel.class);

        assertThat(responseBody).containsExactlyInAnyOrderElementsOf(DataGeneratorFileExtension.getEtalonFileExtensions());
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Расширения файлов")
    @Story("Получение списка расширений файлов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка расширений файлов по протоколу odata")
    @Description("Тест проверяет возможность получения списка расширений файлов по протоколу odata")
    public void testGetFileExtensionListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<FileExtensionResponseModel> responseBody = ApiMethodsFileExtension.getFileExtensionListListOData().jsonPath().getList("value", FileExtensionResponseModel.class);

        assertThat(responseBody).containsExactlyInAnyOrderElementsOf(DataGeneratorFileExtension.getEtalonFileExtensions());
    }
}
