package eDiscovery.data.classifierService;

import eDiscovery.apiMethods.classifier.ApiMethodsRule;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;

public class DataGeneratorRule {

    public static CommonRuleResponseModel getFirstRule(){
        return ApiMethodsRule.getRuleListOData().jsonPath().getList("value", CommonRuleResponseModel.class).get(0);
    }
}
