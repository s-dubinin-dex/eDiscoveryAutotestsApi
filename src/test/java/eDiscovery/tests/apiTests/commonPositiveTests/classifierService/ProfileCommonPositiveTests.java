package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsProfile;
import eDiscovery.data.classifierService.DataGeneratorMarker;
import eDiscovery.data.classifierService.DataGeneratorRule;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.profile.AddProfileRequestModel;
import eDiscovery.models.classifier.profile.ClassifierRulesModel;
import eDiscovery.models.classifier.profile.CommonProfileResponseModel;
import eDiscovery.models.classifier.profile.UpdateProfileRequestModel;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.dateTimeYYYYMMDDHHmmssPattern;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Classifier - Profile")
public class ProfileCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Создание профиля категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание профиля категоризации")
    @Description("Тест проверяет возможность создания профиля категоризации с указанным правила категоризации (без базовой метки)")
    public void testAddProfileWithRuleOnly(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRuleResponseModel ruleBody = DataGeneratorRule.getFirstRule();

        AddProfileRequestModel requestBody = AddProfileRequestModel.builder()
                .name(getRandomName())
                .classifierRules(Collections.singletonList(new ClassifierRulesModel(ruleBody.id, 1)))
                .build();

        CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.canClassifyDocumentsWithMarker).isFalse();
        assertThat(responseBody.considerTagsCriticality).isFalse();
        assertThat(responseBody.baseMarker).isNull();
        assertThat(responseBody.basePolicy).isNull();
        assertThat(responseBody.isDefault).isFalse();
        assertThat(responseBody.isActive).isFalse();
        assertThat(responseBody.markerInfos).isNull();

//        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        assertThat(responseBody.creatorUserName).isEqualTo("Администратор");

//        assertThat(responseBody.updatedUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(isValidUUID(responseBody.editorUserId)).isTrue();
        assertThat(responseBody.editorUserName).isEqualTo("Администратор");

        assertThat(responseBody.encryptionPermissionInfos).isNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Создание профиля категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание профиля категоризации")
    @Description("Тест проверяет возможность создания профиля категоризации с указанным базовой метки (без правил категоризации)")
    public void testAddProfileWithBaseMarkerOnly(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();

        AddProfileRequestModel requestBody = AddProfileRequestModel.builder()
                .name(getRandomName())
                .baseMarkerId(markerBody.id)
                .build();

        CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.canClassifyDocumentsWithMarker).isFalse();
        assertThat(responseBody.considerTagsCriticality).isFalse();
        assertThat(responseBody.baseMarker).usingRecursiveComparison().isEqualTo(markerBody);
        assertThat(responseBody.basePolicy).isNull();
        assertThat(responseBody.isDefault).isFalse();
        assertThat(responseBody.isActive).isFalse();
        assertThat(responseBody.markerInfos).isNull();

//        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        assertThat(responseBody.creatorUserName).isEqualTo("Администратор");

//        assertThat(responseBody.updatedUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(isValidUUID(responseBody.editorUserId)).isTrue();
        assertThat(responseBody.editorUserName).isEqualTo("Администратор");

        assertThat(responseBody.encryptionPermissionInfos).isNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Изменение профиля категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение профиля категоризации")
    @Description("Тест проверяет возможность изменения профиля категоризации")
    public void testUpdateProfile(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRuleResponseModel ruleBody = DataGeneratorRule.getFirstRule();
        CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();

        AddProfileRequestModel requestBodyCreation = AddProfileRequestModel.builder()
                .name(getRandomName())
                .classifierRules(Collections.singletonList(new ClassifierRulesModel(ruleBody.id, 1)))
                .build();

        CommonProfileResponseModel responseBodyCreation = ApiMethodsProfile.addProfile(requestBodyCreation).as(CommonProfileResponseModel.class);

        UpdateProfileRequestModel requestBody = UpdateProfileRequestModel.builder()
                .id(responseBodyCreation.id)
                .name(getRandomName())
                .classifierRules(Collections.singletonList(new ClassifierRulesModel(ruleBody.id, 1)))
                .baseMarkerId(markerBody.id)
                .build();

        CommonProfileResponseModel responseBody = ApiMethodsProfile.updateProfile(requestBody).as(CommonProfileResponseModel.class);


        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.canClassifyDocumentsWithMarker).isFalse();
        assertThat(responseBody.considerTagsCriticality).isFalse();
        assertThat(responseBody.baseMarker).usingRecursiveComparison().isEqualTo(markerBody);
        assertThat(responseBody.basePolicy).isNull();
        assertThat(responseBody.isDefault).isFalse();
        assertThat(responseBody.isActive).isFalse();
        assertThat(responseBody.markerInfos).isNull();

//        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        assertThat(responseBody.creatorUserName).isEqualTo("Администратор");

//        assertThat(responseBody.updatedUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(isValidUUID(responseBody.editorUserId)).isTrue();
        assertThat(responseBody.editorUserName).isEqualTo("Администратор");

        assertThat(responseBody.encryptionPermissionInfos).isNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации")
    @Description("Тест проверяет возможность получения списка профилей категоризации")
    public void testGetProfileList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileList().jsonPath().getList("", CommonProfileResponseModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка профилей категоризации")
    @Description("Тест проверяет возможность получения списка профилей категоризации по протоколу odata")
    public void testGetProfileListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData().jsonPath().getList("value", CommonProfileResponseModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение профиля категоризации по id")
    @Description("Тест проверяет возможность получения профиля категоризации по id")
    public void testGetProfileById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonProfileResponseModel profileToCheck = ApiMethodsProfile.getProfileListOData().jsonPath().getList("value", CommonProfileResponseModel.class).get(0);

        CommonProfileResponseModel responseBody = ApiMethodsProfile.getProfileById(profileToCheck.id).as(CommonProfileResponseModel.class);

        assertThat(responseBody.id).isEqualTo(profileToCheck.id);
        assertThat(responseBody.name).isEqualTo(profileToCheck.name);
        assertThat(responseBody.canClassifyDocumentsWithMarker).isEqualTo(profileToCheck.canClassifyDocumentsWithMarker);
        assertThat(responseBody.considerTagsCriticality).isEqualTo(profileToCheck.considerTagsCriticality);
        assertThat(responseBody.isActive).isEqualTo(profileToCheck.isActive);
        assertThat(responseBody.isDefault).isEqualTo(profileToCheck.isDefault);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение профиля категоризации по id")
    @Description("Тест проверяет возможность получения профиля категоризации по id в path param")
    public void testGetProfileByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonProfileResponseModel profileToCheck = ApiMethodsProfile.getProfileListOData().jsonPath().getList("value", CommonProfileResponseModel.class).get(0);

        CommonProfileResponseModel responseBody = ApiMethodsProfile.getProfileByIdPath(profileToCheck.id).as(CommonProfileResponseModel.class);

        assertThat(responseBody.id).isEqualTo(profileToCheck.id);
        assertThat(responseBody.name).isEqualTo(profileToCheck.name);
        assertThat(responseBody.canClassifyDocumentsWithMarker).isEqualTo(profileToCheck.canClassifyDocumentsWithMarker);
        assertThat(responseBody.considerTagsCriticality).isEqualTo(profileToCheck.considerTagsCriticality);
        assertThat(responseBody.isActive).isEqualTo(profileToCheck.isActive);
        assertThat(responseBody.isDefault).isEqualTo(profileToCheck.isDefault);

    }

}
