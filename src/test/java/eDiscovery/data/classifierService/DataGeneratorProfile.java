package eDiscovery.data.classifierService;

import eDiscovery.apiMethods.classifier.ApiMethodsProfile;
import eDiscovery.models.classifier.profile.AddProfileRequestModel;
import eDiscovery.models.classifier.profile.ClassifierRulesModel;
import eDiscovery.models.classifier.profile.CommonProfileResponseModel;

import java.util.Collections;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorProfile {

    public static AddProfileRequestModel getAddProfileRequestModelWithOnlyRequiredParameters(){
        return AddProfileRequestModel.builder()
                .name(getRandomName())
                .classifierRules(Collections.singletonList(new ClassifierRulesModel(DataGeneratorRule.createRuleWithOnlyRequiredParameters().id, 1)))
                .baseMarkerId(DataGeneratorMarker.getFirstMarker().id)
                .build();
    }

    public static CommonProfileResponseModel createProfileWithOnlyRequiredParameters(){
        return ApiMethodsProfile.addProfile(getAddProfileRequestModelWithOnlyRequiredParameters()).as(CommonProfileResponseModel.class);
    }
}
