package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsEncryptionPermission;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;
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

@DisplayName("Extended positive tests: Classifier - EncryptionPermission")
public class EncryptionPermissionExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Политики шифрования")
    @Story("Получение списка политик шифрования")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка политик шифрования для отображения в списке")
    @Description("Тест проверяет возможность получения списка политик шифрования для отображения в списке")
    public void testGetEncryptionPermissionListODataWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonEncryptionPermissionResponseModel> responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData(params).jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).policyId).isGreaterThan(0);
        assertThat(responseBody.get(0).name).isNotBlank();
        assertThat(responseBody.get(0).algorithm).isNotBlank();
        assertThat(responseBody.get(0).dssVersion).isNotBlank();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Политики шифрования")
    @Story("Получение списка политик шифрования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка политик шифрования для отображения в списке с сортировкой по имени")
    @Description("Тест проверяет возможность получения списка политик шифрования для отображения в списке с сортировкой по имени")
    public void testGetEncryptionPermissionListODataWEBUIWithSortingName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withOrderBy("name asc")
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonEncryptionPermissionResponseModel> responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData(params).jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Политики шифрования")
    @Story("Получение списка политик шифрования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка политик шифрования для отображения в списке с сортировкой по алгоритму")
    @Description("Тест проверяет возможность получения списка политик шифрования для отображения в списке с сортировкой по алгоритму")
    public void testGetEncryptionPermissionListODataWEBUIWithSortingAlgorithm(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withOrderBy("algorithm asc")
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonEncryptionPermissionResponseModel> responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData(params).jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Политики шифрования")
    @Story("Получение списка политик шифрования")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка политик шифрования для отображения в списке с сортировкой по статусу")
    @Description("Тест проверяет возможность получения списка политик шифрования для отображения в списке с сортировкой по статусу")
    public void testGetEncryptionPermissionListODataWEBUIWithSortingIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withOrderBy("isActive asc")
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonEncryptionPermissionResponseModel> responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData(params).jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class);

    }
}
