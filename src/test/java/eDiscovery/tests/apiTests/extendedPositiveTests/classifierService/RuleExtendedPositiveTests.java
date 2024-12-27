package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsRule;
import eDiscovery.data.classifierService.DataGeneratorEncryptionPermission;
import eDiscovery.data.classifierService.DataGeneratorMarker;
import eDiscovery.data.classifierService.DataGeneratorRule;
import eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.rule.AddRuleRequestModel;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;
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

@DisplayName("Extended positive tests: Classifier - Rule")
public class RuleExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Проверка заполнения полей Classifier - Rule в теле ответа при создании")
    class CheckRuleCreationResponseFields{

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает id")
        @Description("Тест проверяет, что при создании правила возвращается корректный id")
        public void testAddRuleReturnsId () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRuleRequestModel requestBody = DataGeneratorRule.getRuleModelWithOnlyRequiredParameters();

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(isValidUUID(responseBody.id)).isTrue();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает name")
        @Description("Тест проверяет, что при создании правила возвращается name, переданный при создании")
        public void testAddRuleReturnsName () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRuleRequestModel requestBody = DataGeneratorRule.getRuleModelWithOnlyRequiredParameters();

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(responseBody.name).isEqualTo(requestBody.name);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает marker")
        @Description("Тест проверяет, что при создании правила возвращается marker, переданный при создании")
        public void testAddRuleReturnsMarker () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();
            CommonSearchQueryClassifierResponseModel searchQueryBody = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

            AddRuleRequestModel requestBody = AddRuleRequestModel.builder()
                    .name(getRandomName())
                    .markerId(markerBody.id)
                    .searchQueries(Collections.singletonList(searchQueryBody.id))
                    .build();

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(responseBody.marker).usingRecursiveComparison().isEqualTo(markerBody);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает policy")
        @Description("Тест проверяет, что при создании правила возвращается policy, переданный при создании")
        public void testAddRuleReturnsPolicy () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonEncryptionPermissionResponseModel policyBody = DataGeneratorEncryptionPermission.getFirstEncryptionPermission();

            AddRuleRequestModel requestBody = DataGeneratorRule.getRuleModelWithOnlyRequiredParameters();
            requestBody.policyId = policyBody.id;

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(responseBody.policy).usingRecursiveComparison().isEqualTo(policyBody);
        }

        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает isActive = false по умолчанию")
        @Description("Тест проверяет, что при создании правила возвращается isActive = false по умолчанию")
        @Test
        public void testAddRuleReturnsDefaultIsActive () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRuleRequestModel requestBody = DataGeneratorRule.getRuleModelWithOnlyRequiredParameters();

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(responseBody.isActive).isFalse();
        }

        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает isActive, переданный при создании")
        @Description("Тест проверяет, что при создании правила возвращается isActive, переданный при создании")
        @ParameterizedTest
        @ValueSource(booleans = {true, false})
        public void testAddRuleReturnsIsActive (boolean isActive) {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            AddRuleRequestModel requestBody = DataGeneratorRule.getRuleModelWithOnlyRequiredParameters();
            requestBody.isActive = isActive;

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(responseBody.isActive).isEqualTo(isActive);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает searchQueries")
        @Description("Тест проверяет, что при создании правила возвращает searchQueries")
        public void testAddRuleReturnsSearchQueries () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();
            CommonSearchQueryClassifierResponseModel searchQueryBody = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

            AddRuleRequestModel requestBody = AddRuleRequestModel.builder()
                    .name(getRandomName())
                    .markerId(markerBody.id)
                    .searchQueries(Collections.singletonList(searchQueryBody.id))
                    .build();

            CommonRuleResponseModel responseBody = ApiMethodsRule.addRule(requestBody).as(CommonRuleResponseModel.class);

            assertThat(responseBody.searchQueries).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(Collections.singletonList(searchQueryBody));
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает пустой profiles для неиспользуемого правила")
        @Description("Тест проверяет, что при создании правила возвращается пустой profiles для неиспользуемого правила")
        public void testAddRuleReturnsEmptyProfilesForUnusedRule () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRuleResponseModel responseBody = DataGeneratorRule.createRuleWithOnlyRequiredParameters();

            assertThat(responseBody.profiles).hasSize(0);
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Создание правила категоризации возвращает валидный creatorUserId")
        @Description("Тест проверяет, что при создании правила возвращается валидный creatorUserId")
        public void testAddRuleReturnsCreatorUserId () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRuleResponseModel responseBody = DataGeneratorRule.createRuleWithOnlyRequiredParameters();

            assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Создание правила категоризации возвращает creatorUserName")
        @Description("Тест проверяет, что при создании правила возвращается creatorUserName")
        public void testAddRuleReturnsCreatorUserName () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRuleResponseModel responseBody = DataGeneratorRule.createRuleWithOnlyRequiredParameters();

            assertThat(responseBody.creatorUserName).isEqualTo("Администратор");
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Создание правила категоризации возвращает createdUtc в корректном формате")
        @Description("Тест проверяет, что при создании правила возвращается createdUtc в корректном формате")
        public void testAddRuleReturnsCreatedUtc () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRuleResponseModel responseBody = DataGeneratorRule.createRuleWithOnlyRequiredParameters();

            assertThat(responseBody.createdUtc).matches(dateTimeCommonPattern());
        }

        @Test
        @Epic("Сервис Classifier")
        @Feature("Правило категоризации")
        @Story("Создание правила категоризации")
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Создание правила категоризации возвращает deletedUtc = null")
        @Description("Тест проверяет, что при создании правила возвращается deletedUtc = null")
        public void testAddRuleReturnsDeletedUtc () {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonRuleResponseModel responseBody = DataGeneratorRule.createRuleWithOnlyRequiredParameters();

            assertThat(responseBody.deletedUtc).isNull();
        }

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение правила категоризации по id")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение правила категоризации по id для карточки правила")
    @Description("Тест проверяет возможность получения правила категоризации по id для карточки правила")
    public void testGetRuleByIdWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withExpand("searchQueries,marker,policy")
                .build();

        CommonRuleResponseModel ruleToCheck = ApiMethodsRule.getRuleListOData().jsonPath().getList("value", CommonRuleResponseModel.class).get(0);

        CommonRuleResponseModel responseBody = ApiMethodsRule.getRuleByIdPath(ruleToCheck.id, params).as(CommonRuleResponseModel.class);

        assertThat(responseBody.id).isEqualTo(ruleToCheck.id);
        assertThat(responseBody.name).isEqualTo(ruleToCheck.name);
        assertThat(responseBody.isActive).isEqualTo(ruleToCheck.isActive);
        assertThat(responseBody.creatorUserId).isEqualTo(ruleToCheck.creatorUserId);
        assertThat(responseBody.creatorUserName).isEqualTo(ruleToCheck.creatorUserName);
        assertThat(responseBody.createdUtc).isEqualTo(ruleToCheck.createdUtc);
        assertThat(responseBody.deletedUtc).isEqualTo(ruleToCheck.deletedUtc);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в выпадающем списке")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в выпадающем списке")
    public void testGetRuleListWEBUIForFilter(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchQueries,marker")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке")
    public void testGetRuleListWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с фильтром по поисковому запросу")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с фильтром по поисковому запросу")
    public void testGetRuleListWEBUIWithFilterSearchQuery(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and searchQueries/any(searchqueries:searchqueries/id eq 1a6450a3-b6dd-4a7c-b66b-ce657a91f747)")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с фильтром по метке")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с фильтром по метке")
    public void testGetRuleListWEBUIWithFilterMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and Marker/id eq 0000e83a-0e36-49aa-a3dd-791439391336")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с фильтром по статусу")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с фильтром по статусу")
    public void testGetRuleListWEBUIWithFilterIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and isActive eq true")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с сортировкой по Названию")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с сортировкой по Названию")
    public void testGetRuleListWEBUIWithSortingName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с сортировкой по Метке")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с сортировкой по Метке")
    public void testGetRuleListWEBUIWithSortingMarker(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("marker/name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с сортировкой по Политике шифрования")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с сортировкой по Политике шифрования")
    public void testGetRuleListWEBUIWithSortingPolicyName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("policy/name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации для отображения в списке с сортировкой по Статусу")
    @Description("Тест проверяет возможность получения списка правил категоризации для отображения в списке с сортировкой по Статусу")
    public void testGetRuleListWEBUIWithSortingIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchQueries,marker,policy")
                .withOrderBy("isActive")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData(params).jsonPath().getList("value", CommonRuleResponseModel.class);
    }
}
