package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsEncryptionPermission;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Classifier - EncryptionPermission")
public class EncryptionPermissionCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Политики шифрования")
    @Story("Получение списка политик шифрования")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка политик шифрования")
    @Description("Тест проверяет возможность получения списка политик шифрования")
    public void testGetEncryptionPermissionList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonEncryptionPermissionResponseModel> responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionList().jsonPath().getList("", CommonEncryptionPermissionResponseModel.class);

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка политик шифрования")
    @Description("Тест проверяет возможность получения списка политик шифрования по протоколу odata")
    public void testGetEncryptionPermissionListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonEncryptionPermissionResponseModel> responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData().jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class);

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
    @Story("Получение политики шифрования по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение политики шифрования по id")
    @Description("Тест проверяет возможность получения политики шифрования по id")
    public void testGetEncryptionPermissionById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonEncryptionPermissionResponseModel encryptionPermissionToCheck = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData().jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class).get(0);

        CommonEncryptionPermissionResponseModel responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListById(encryptionPermissionToCheck.id).as(CommonEncryptionPermissionResponseModel.class);

        assertThat(responseBody.id).isEqualTo(encryptionPermissionToCheck.id);
        assertThat(responseBody.policyId).isEqualTo(encryptionPermissionToCheck.policyId);
        assertThat(responseBody.name).isEqualTo(encryptionPermissionToCheck.name);
        assertThat(responseBody.algorithm).isEqualTo(encryptionPermissionToCheck.algorithm);
        assertThat(responseBody.description).isEqualTo(encryptionPermissionToCheck.description);
        assertThat(responseBody.isActive).isEqualTo(encryptionPermissionToCheck.isActive);
        assertThat(responseBody.dssVersion).isEqualTo(encryptionPermissionToCheck.dssVersion);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Политики шифрования")
    @Story("Получение политики шифрования по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение политики шифрования по id в path param")
    @Description("Тест проверяет возможность получения политики шифрования по id в path param")
    public void testGetEncryptionPermissionByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonEncryptionPermissionResponseModel encryptionPermissionToCheck = ApiMethodsEncryptionPermission.getEncryptionPermissionListOData().jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class).get(0);

        CommonEncryptionPermissionResponseModel responseBody = ApiMethodsEncryptionPermission.getEncryptionPermissionListByIdPath(encryptionPermissionToCheck.id).as(CommonEncryptionPermissionResponseModel.class);

        assertThat(responseBody.id).isEqualTo(encryptionPermissionToCheck.id);
        assertThat(responseBody.policyId).isEqualTo(encryptionPermissionToCheck.policyId);
        assertThat(responseBody.name).isEqualTo(encryptionPermissionToCheck.name);
        assertThat(responseBody.algorithm).isEqualTo(encryptionPermissionToCheck.algorithm);
        assertThat(responseBody.description).isEqualTo(encryptionPermissionToCheck.description);
        assertThat(responseBody.isActive).isEqualTo(encryptionPermissionToCheck.isActive);
        assertThat(responseBody.dssVersion).isEqualTo(encryptionPermissionToCheck.dssVersion);
    }

}
