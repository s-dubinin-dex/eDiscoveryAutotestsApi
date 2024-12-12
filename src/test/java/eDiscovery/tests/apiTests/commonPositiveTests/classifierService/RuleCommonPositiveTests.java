package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsRule;
import eDiscovery.data.classifierService.DataGeneratorMarker;
import eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.rule.AddRuleRequestModel;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;
import eDiscovery.models.classifier.rule.UpdateRuleRequestModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Classifier - Rule")
public class RuleCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Создание правила категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание правила категоризации")
    @Description("Тест проверяет возможность создания правила категоризации")
    public void testAddRule(){
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

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.marker).usingRecursiveComparison().isEqualTo(markerBody);
        assertThat(responseBody.policy).isNull();
        assertThat(responseBody.isActive).isFalse();
        assertThat(responseBody.searchQueries).hasSize(1);
        assertThat(responseBody.searchQueries.get(0)).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(searchQueryBody);
        assertThat(responseBody.profiles).hasSize(0);
        assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        assertThat(responseBody.creatorUserName).isNull();
//        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(responseBody.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Изменение правила категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение правила категоризации")
    @Description("Тест проверяет возможность изменения правила категоризации")
    public void testUpdateRule(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();
        CommonSearchQueryClassifierResponseModel searchQueryBody1 = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();
        CommonSearchQueryClassifierResponseModel searchQueryBody2 = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

        AddRuleRequestModel requestBodyCreation = AddRuleRequestModel.builder()
                .name(getRandomName())
                .markerId(markerBody.id)
                .searchQueries(Collections.singletonList(searchQueryBody1.id))
                .build();

        CommonRuleResponseModel responseBodyCreation = ApiMethodsRule.addRule(requestBodyCreation).as(CommonRuleResponseModel.class);

        UpdateRuleRequestModel requestBody = UpdateRuleRequestModel.builder()
                .id(responseBodyCreation.id)
                .name(getRandomName())
                .markerId(responseBodyCreation.marker.id)
                .searchQueries(
                        List.of(
                                searchQueryBody1.id,
                                searchQueryBody2.id
                        )
                )
                .build();

        CommonRuleResponseModel responseBody = ApiMethodsRule.updateRule(requestBody).as(CommonRuleResponseModel.class);

        assertThat(responseBody.id).isEqualTo(requestBody.id);
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.marker).usingRecursiveComparison().isEqualTo(markerBody);
        assertThat(responseBody.policy).isNull();
        assertThat(responseBody.isActive).isFalse();
        assertThat(responseBody.searchQueries).hasSize(2);
        assertThat(responseBody.searchQueries).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("createdUtc").isEqualTo(
                List.of(
                        searchQueryBody1,
                        searchQueryBody2
                )
        );
        assertThat(responseBody.profiles).hasSize(0);
        assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        assertThat(responseBody.creatorUserName).isNull();
//        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(responseBody.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Удаление правила категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление правила категоризации")
    @Description("Тест проверяет возможность удаления правила категоризации")
    public void testDeleteRule(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonMarkerResponseModel markerBody = DataGeneratorMarker.getFirstMarker();
        CommonSearchQueryClassifierResponseModel searchQueryBody = DataGeneratorSearchQueryClassifier.createBasicSearchQuery();

        AddRuleRequestModel requestBodyCreation = AddRuleRequestModel.builder()
                .name(getRandomName())
                .markerId(markerBody.id)
                .searchQueries(Collections.singletonList(searchQueryBody.id))
                .build();

        CommonRuleResponseModel responseBodyCreation = ApiMethodsRule.addRule(requestBodyCreation).as(CommonRuleResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsRule.deleteRule(responseBodyCreation.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRuleResponseModel responseBody = ApiMethodsRule.getRuleByIdPath(responseBodyCreation.id).as(CommonRuleResponseModel.class);


        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBodyCreation.name);
        assertThat(responseBody.policy).isNull();
        assertThat(responseBody.isActive).isFalse();
        assertThat(isValidUUID(responseBody.creatorUserId)).isTrue();
        assertThat(responseBody.creatorUserName).isEqualTo("Администратор");
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).matches(dateTimeISOPattern());
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации")
    @Description("Тест проверяет возможность получения списка правил категоризации")
    public void testGetRuleList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleList().jsonPath().getList("", CommonRuleResponseModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение списка правил категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка правил категоризации по протоколу odata")
    @Description("Тест проверяет возможность получения списка правил категоризации по протоколу odata")
    public void testGetRuleListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<CommonRuleResponseModel> responseBody = ApiMethodsRule.getRuleListOData().jsonPath().getList("value", CommonRuleResponseModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Правило категоризации")
    @Story("Получение правила категоризации по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение правила категоризации по id")
    @Description("Тест проверяет возможность получения правила категоризации по id")
    public void testGetRuleById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRuleResponseModel ruleToCheck = ApiMethodsRule.getRuleListOData().jsonPath().getList("value", CommonRuleResponseModel.class).get(0);

        CommonRuleResponseModel responseBody = ApiMethodsRule.getRuleById(ruleToCheck.id).as(CommonRuleResponseModel.class);

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
    @Story("Получение правила категоризации по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение правила категоризации по id в path param")
    @Description("Тест проверяет возможность получения правила категоризации по id в path param")
    public void testGetRuleByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonRuleResponseModel ruleToCheck = ApiMethodsRule.getRuleListOData().jsonPath().getList("value", CommonRuleResponseModel.class).get(0);

        CommonRuleResponseModel responseBody = ApiMethodsRule.getRuleByIdPath(ruleToCheck.id).as(CommonRuleResponseModel.class);

        assertThat(responseBody.id).isEqualTo(ruleToCheck.id);
        assertThat(responseBody.name).isEqualTo(ruleToCheck.name);
        assertThat(responseBody.isActive).isEqualTo(ruleToCheck.isActive);
        assertThat(responseBody.creatorUserId).isEqualTo(ruleToCheck.creatorUserId);
        assertThat(responseBody.creatorUserName).isEqualTo(ruleToCheck.creatorUserName);
        assertThat(responseBody.createdUtc).isEqualTo(ruleToCheck.createdUtc);
        assertThat(responseBody.deletedUtc).isEqualTo(ruleToCheck.deletedUtc);

    }


}
