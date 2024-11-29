package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsMarkerResult;
import eDiscovery.models.classifier.markerResult.CommonMarkerResultResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Common positive tests - MarkerResult")
public class MarkerResultCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение результатов маркирования")
    @Description("Тест проверяет возможность получения результатов маркирования")
    public void testGetMarkerResultList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultList().jsonPath().getList("", CommonMarkerResultResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Результаты маркирования")
    @Story("Получение результатов маркирования")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение результатов маркирования по протоколу odata")
    @Description("Тест проверяет возможность получения результатов маркирования по протоколу odata")
    public void testGetMarkerResultListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonMarkerResultResponseModel> responseBody = ApiMethodsMarkerResult.getMarkerResultListOData().jsonPath().getList("value", CommonMarkerResultResponseModel.class);

    }
}
