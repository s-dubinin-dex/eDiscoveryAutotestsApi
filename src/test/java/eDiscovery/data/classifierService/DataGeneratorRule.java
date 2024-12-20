package eDiscovery.data.classifierService;

import eDiscovery.apiMethods.classifier.ApiMethodsRule;
import eDiscovery.models.classifier.rule.AddRuleRequestModel;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;

import java.util.Collections;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorRule {

    public static CommonRuleResponseModel getFirstRule(){
        return ApiMethodsRule.getRuleListOData().jsonPath().getList("value", CommonRuleResponseModel.class).get(0);
    }

    public static AddRuleRequestModel getRuleModelWithOnlyRequiredParameters(){
        return AddRuleRequestModel.builder()
                .name(getRandomName())
                .searchQueries(Collections.singletonList(DataGeneratorSearchQueryClassifier.createBasicSearchQuery().id))
                .markerId(DataGeneratorMarker.getFirstMarker().id)
                .build();
    }

    public static CommonRuleResponseModel createRuleWithOnlyRequiredParameters(){
        return ApiMethodsRule.addRule(getRuleModelWithOnlyRequiredParameters()).as(CommonRuleResponseModel.class);
    }
}
