package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsProfile;
import eDiscovery.data.classifierService.DataGeneratorEncryptionPermission;
import eDiscovery.data.classifierService.DataGeneratorMarker;
import eDiscovery.data.classifierService.DataGeneratorProfile;
import eDiscovery.data.classifierService.DataGeneratorRule;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.profile.AddProfileRequestModel;
import eDiscovery.models.classifier.profile.ClassifierRulesModel;
import eDiscovery.models.classifier.profile.CommonProfileResponseModel;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Classifier - Profile")
public class ProfileExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Проверка заполнения полей Classifier - Profile в теле ответа при создании")
    class CheckProfileCreationResponseFields{

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает валидный id")
        @Description("Тест проверяет, что при создании профиля классификации возвращается валидный id")
        public void testAddProfileReturnsId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(isValidUUID(responseBody.id)).isTrue();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает name, переданный при создании")
        @Description("Тест проверяет, что при создании профиля классификации возвращает name, переданный при создании")
        public void testAddProfileReturnsName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.name).isEqualTo(requestBody.name);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает canClassifyDocumentsWithMarker = false по умолчанию")
        @Description("Тест проверяет, что при создании профиля классификации возвращает canClassifyDocumentsWithMarker = false по умолчанию")
        public void testAddProfileReturnsDefaultCanClassifyDocumentsWithMarker(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.canClassifyDocumentsWithMarker).isFalse();
        }

        @ParameterizedTest
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает canClassifyDocumentsWithMarker, переданный при создании")
        @Description("Тест проверяет, что при создании профиля классификации возвращает canClassifyDocumentsWithMarker, переданный при создании")
        @ValueSource(booleans = {false, true})
        public void testAddProfileReturnsCanClassifyDocumentsWithMarker(boolean canClassifyDocumentsWithMarker){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();
            requestBody.canClassifyDocumentsWithMarker = canClassifyDocumentsWithMarker;

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.canClassifyDocumentsWithMarker).isEqualTo(canClassifyDocumentsWithMarker);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает considerTagsCriticality = false по умолчанию")
        @Description("Тест проверяет, что при создании профиля классификации возвращает considerTagsCriticality = false по умолчанию")
        public void testAddProfileReturnsDefaultConsiderTagsCriticality(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.considerTagsCriticality).isFalse();
        }

        @ParameterizedTest
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает considerTagsCriticality, переданный при создании")
        @Description("Тест проверяет, что при создании профиля классификации возвращает considerTagsCriticality, переданный при создании")
        @ValueSource(booleans = {false, true})
        public void testAddProfileReturnsConsiderTagsCriticalityWithDifferentValues(boolean considerTagsCriticality){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();
            requestBody.considerTagsCriticality = considerTagsCriticality;

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.considerTagsCriticality).isEqualTo(considerTagsCriticality);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает rules")
        @Description("Тест проверяет, что при создании профиля классификации возвращает rules")
        public void testAddProfileReturnsRules( ){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRuleResponseModel ruleBody = DataGeneratorRule.createRuleWithOnlyRequiredParameters();

            AddProfileRequestModel requestBody = AddProfileRequestModel.builder()
                    .name(getRandomName())
                    .classifierRules(Collections.singletonList(new ClassifierRulesModel(ruleBody.id, 1)))
                    .build();

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);

            assertThat(responseBody.rules).usingRecursiveComparison().comparingOnlyFields("id", "name", "order", "marker", "policy", "searchQuery", "isActive").isEqualTo(Collections.singletonList(ruleBody));
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает baseMarker")
        @Description("Тест проверяет, что при создании профиля классификации возвращает baseMarker")
        public void testAddProfileReturnsBaseMarker( ){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();
            requestBody.baseMarkerId = markerBody.id;

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);

            assertThat(responseBody.baseMarker).usingRecursiveComparison().isEqualTo(markerBody);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает basePolicy")
        @Description("Тест проверяет, что при создании профиля классификации возвращает basePolicy")
        public void testAddProfileReturnsBasePolicy( ){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEncryptionPermissionResponseModel policyBody = DataGeneratorEncryptionPermission.getFirstEncryptionPermission();

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();
            requestBody.basePolicyId = policyBody.id;

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);

            assertThat(responseBody.basePolicy).usingRecursiveComparison().isEqualTo(policyBody);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает isDefault")
        @Description("Тест проверяет, что при создании профиля классификации возвращается isDefault")
        public void testAddProfileReturnsIsDefault(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.isDefault).isFalse();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает isActive = false по умолчанию")
        @Description("Тест проверяет, что при создании профиля классификации возвращает isActive = false по умолчанию")
        public void testAddProfileReturnsDefaultIsActive(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.isActive).isFalse();
        }

        @ParameterizedTest
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает isActive, переданный при создании")
        @Description("Тест проверяет, что при создании профиля классификации возвращает isActive, переданный при создании")
        @ValueSource(booleans = {false, true})
        public void testAddProfileReturnsIsActiveWithDifferentValues(boolean isActive){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddProfileRequestModel requestBody = DataGeneratorProfile.getAddProfileRequestModelWithOnlyRequiredParameters();
            requestBody.isActive = isActive;

            CommonProfileResponseModel responseBody = ApiMethodsProfile.addProfile(requestBody).as(CommonProfileResponseModel.class);
            assertThat(responseBody.isActive).isEqualTo(isActive);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает markerInfos = null")
        @Description("Тест проверяет, что при создании профиля классификации возвращает markerInfos = null")
        public void testAddProfileReturnsMarkerInfos(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.markerInfos).isNull();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает createdUtc в корректном формате")
        @Description("Тест проверяет, что при создании профиля классификации возвращает createdUtc в корректном формате")
        public void testAddProfileReturnsCreatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает creatorUserId")
        @Description("Тест проверяет, что при создании профиля классификации возвращает creatorUserId")
        public void testAddProfileReturnsCreatorUserId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает creatorUserName")
        @Description("Тест проверяет, что при создании профиля классификации возвращает creatorUserName")
        public void testAddProfileReturnsCreatorUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.creatorUserName).isEqualTo("Администратор");
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает updatedUtc в корректном формате")
        @Description("Тест проверяет, что при создании профиля классификации возвращает updatedUtc в корректном формате")
        public void testAddProfileReturnsUpdatedUtc(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.updatedUtc).matches(dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает editorUserId")
        @Description("Тест проверяет, что при создании профиля классификации возвращает editorUserId")
        public void testAddProfileReturnsEditorUserId(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(isValidUUID(responseBody.editorUserId)).isTrue();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает editorUserName")
        @Description("Тест проверяет, что при создании профиля классификации возвращает editorUserName")
        public void testAddProfileReturnsEditorUserName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.editorUserName).isEqualTo("Администратор");
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Профиль категоризации")
        @Story("Создание профиля категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание профиля категоризации возвращает encryptionPermissionInfos = null")
        @Description("Тест проверяет, что при создании профиля классификации возвращает encryptionPermissionInfos = null")
        public void testAddProfileReturnsEncryptionPermissionInfos(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonProfileResponseModel responseBody = DataGeneratorProfile.createProfileWithOnlyRequiredParameters();
            assertThat(responseBody.encryptionPermissionInfos).isNull();
        }

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение профиля категоризации по id для карточки профиля")
    @Description("Тест проверяет возможность получения профиля категоризации по id для карточки профиля")
    public void testGetProfileByIdPathWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withExpand("baseMarker,rules($expand=searchQueries,marker,policy)")
                .build();

        CommonProfileResponseModel profileToCheck = ApiMethodsProfile.getProfileListOData().jsonPath().getList("value", CommonProfileResponseModel.class).get(0);

        CommonProfileResponseModel responseBody = ApiMethodsProfile.getProfileByIdPath(profileToCheck.id).as(CommonProfileResponseModel.class);

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке")
    public void testGetProfileListOdataWebUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("value", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с фильтром по статусу профиля")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с фильтром по статусу профиля")
    public void testGetProfileListOdataWebUIWithFilterIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and isActive eq true")
                .withExpand("baseMarker,rules")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("value", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с сортировкой по Названию")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с сортировкой по Названию")
    public void testGetProfileListOdataWebUIWithSortingName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("value", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с сортировкой по Статусу")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с сортировкой по Статусу")
    public void testGetProfileListOdataWebUIWithSortingIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("isActive asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("value", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с сортировкой по Дефолтности профиля")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с сортировкой по Дефолтности профиля")
    public void testGetProfileListOdataWebUIWithSortingIsDefault(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("isDefault asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("value", CommonProfileResponseModel.class);

    }

}
