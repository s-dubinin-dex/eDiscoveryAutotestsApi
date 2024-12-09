package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsRule;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Classifier - Rule")
public class RuleExtendedPositiveTests extends TestBase {

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
