package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsMarker;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Classifier - Marker")
public class MarkerExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение списка меток")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка меток для отображения в списке")
    @Description("Тест проверяет возможность получения списка меток по протоколу odata для отображения в списке")
    public void testGetMarkerListForMarkerListWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerListOData(params).jsonPath().getList("value", CommonMarkerResponseModel.class);

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
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка меток для отображения в списке")
    @Description("Тест проверяет возможность получения списка меток по протоколу odata для отображения в списке с сортировкой по Названию")
    public void testGetMarkerListForMarkerListWEBUIWithSortingName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withOrderBy("name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerListOData(params).jsonPath().getList("value", CommonMarkerResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение списка меток")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка меток для отображения в списке")
    @Description("Тест проверяет возможность получения списка меток по протоколу odata для отображения в списке с сортировкой по Описанию")
    public void testGetMarkerListForMarkerListWEBUIWithSortingDescription(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withOrderBy("description asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerListOData(params).jsonPath().getList("value", CommonMarkerResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение списка меток")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка меток для отображения в списке")
    @Description("Тест проверяет возможность получения списка меток по протоколу odata для отображения в списке с сортировкой по Уровню критичности")
    public void testGetMarkerListForMarkerListWEBUIWithSortingLevel(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withOrderBy("level asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerListOData(params).jsonPath().getList("value", CommonMarkerResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Метки")
    @Story("Получение списка меток")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка меток для отображения в списке")
    @Description("Тест проверяет возможность получения списка меток по протоколу odata для отображения в списке с сортировкой по Статусу")
    public void testGetMarkerListForMarkerListWEBUIWithSortingIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withOrderBy("isActive asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonMarkerResponseModel> responseBody = ApiMethodsMarker.getMarkerListOData(params).jsonPath().getList("value", CommonMarkerResponseModel.class);

    }
}
