package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsFileTypes;
import eDiscovery.models.deal.fileExtensions.CommonFileTypesResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests - FileTypes")
public class FileTypesCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Типы файлов")
    @Story("Получение списка типов файлов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка типов файлов")
    @Description("Тест проверяет возможность получения списка типов файлов")
    public void testGetFileTypesList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonFileTypesResponseModel> responseBody = ApiMethodsFileTypes.getFileTypesList().jsonPath().getList("", CommonFileTypesResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        assertThat(responseBody.get(0).name).isEqualTo("Presentation");
        assertThat(responseBody.get(0).description).isEqualTo("Презентации");
        assertThat(responseBody.get(0).fileExtensions.get(0).extension).isEqualTo(".pptx");
        assertThat(responseBody.get(0).fileExtensions.get(1).extension).isEqualTo(".odp");
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Типы файлов")
    @Story("Получение списка типов файлов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка типов файлов по протоколу oData")
    @Description("Тест проверяет возможность получения списка типов файлов по протоколу oData")
    public void testGetFileTypesListOdata(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonFileTypesResponseModel> responseBody = ApiMethodsFileTypes.getFileTypesListOData().jsonPath().getList("value", CommonFileTypesResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        assertThat(responseBody.get(0).name).isEqualTo("Presentation");
        assertThat(responseBody.get(0).description).isEqualTo("Презентации");

    }

}
