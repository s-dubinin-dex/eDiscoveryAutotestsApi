package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsFileTypes;
import eDiscovery.models.deal.fileExtensions.CommonFileTypesResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTypesExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Типы файлов")
    @Story("Получение списка типов файлов")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка типов файлов по протоколу oData с расширениями")
    @Description("Тест проверяет возможность получения списка типов файлов по протоколу oData с расширениями")
    public void testGetFileTypesListOdata(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> parameters= new HashMap<>();
        parameters.put("$expand", "fileExtensions");

        List<CommonFileTypesResponseModel> responseBody = ApiMethodsFileTypes.getFileTypesListOData(parameters).jsonPath().getList("value", CommonFileTypesResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        assertThat(responseBody.get(0).name).isEqualTo("Presentation");
        assertThat(responseBody.get(0).description).isEqualTo("Презентации");
        assertThat(responseBody.get(0).fileExtensions.get(0).extension).isEqualTo(".pptx");
        assertThat(responseBody.get(0).fileExtensions.get(1).extension).isEqualTo(".odp");
    }
}