package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsMarker;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests - Marker")
public class MarkerCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение списка меток")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка меток")
    @Description("Тест проверяет возможность получения списка меток")
    public void testGetMarkerList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerList().jsonPath().getList("", CommonMarkerResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).name).isNotBlank();
        assertThat(responseBody.get(0).level).isNotBlank();
        assertThat(responseBody.get(0).color).isNotBlank();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение списка меток")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка меток")
    @Description("Тест проверяет возможность получения списка меток по протоколу odata")
    public void testGetMarkerListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerListOData().jsonPath().getList("value", CommonMarkerResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).name).isNotBlank();
        assertThat(responseBody.get(0).level).isNotBlank();
        assertThat(responseBody.get(0).color).isNotBlank();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение метки по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение метки по id")
    @Description("Тест проверяет возможность получения метки по id")
    public void testGetMarkerById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonMarkerResponseModel markerToCheck = ApiMethodsMarker.getMarkerListOData().jsonPath().getList("value", CommonMarkerResponseModel.class).get(0);

        CommonMarkerResponseModel responseBody = ApiMethodsMarker.getMarkerListById(markerToCheck.id).as(CommonMarkerResponseModel.class);

        assertThat(responseBody.id).isEqualTo(markerToCheck.id);
        assertThat(responseBody.name).isEqualTo(markerToCheck.name);
        assertThat(responseBody.description).isEqualTo(markerToCheck.description);
        assertThat(responseBody.isActive).isEqualTo(markerToCheck.isActive);
        assertThat(responseBody.level).isEqualTo(markerToCheck.level);
        assertThat(responseBody.color).isEqualTo(markerToCheck.color);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение метки по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение метки по id в path param")
    @Description("Тест проверяет возможность получения метки по id в path param")
    public void testGetMarkerByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonMarkerResponseModel markerToCheck = ApiMethodsMarker.getMarkerListOData().jsonPath().getList("value", CommonMarkerResponseModel.class).get(0);

        CommonMarkerResponseModel responseBody = ApiMethodsMarker.getMarkerListByIdPath(markerToCheck.id).as(CommonMarkerResponseModel.class);

        assertThat(responseBody.id).isEqualTo(markerToCheck.id);
        assertThat(responseBody.name).isEqualTo(markerToCheck.name);
        assertThat(responseBody.description).isEqualTo(markerToCheck.description);
        assertThat(responseBody.isActive).isEqualTo(markerToCheck.isActive);
        assertThat(responseBody.level).isEqualTo(markerToCheck.level);
        assertThat(responseBody.color).isEqualTo(markerToCheck.color);
    }

}
