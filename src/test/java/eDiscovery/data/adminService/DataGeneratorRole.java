package eDiscovery.data.adminService;

import eDiscovery.apiMethods.admin.ApiMethodsRole;
import eDiscovery.models.admin.role.AddRoleRequestModel;
import eDiscovery.models.admin.role.CommonRoleResponseModel;
import eDiscovery.models.admin.role.PolicyResponseModel;

import java.util.Collections;
import java.util.List;
import eDiscovery.data.DataGeneratorCommon;

public class DataGeneratorRole {

    /**
    * Role Models
    * */

    public static AddRoleRequestModel getRoleModelWithOnlyRequiredParameters(){
        return AddRoleRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .policies(Collections.singletonList("ed.deal.read"))
                .build();
    }

    /**
     * Role Generators
     * */

    public static CommonRoleResponseModel createRoleWithOnlyRequiredParameters(){
        return ApiMethodsRole.addRole(getRoleModelWithOnlyRequiredParameters()).as(CommonRoleResponseModel.class);
    }

    /**
    * EtalonData
    * */

    public static List<PolicyResponseModel> getEtalonPolicies(){

        return List.of(

                new PolicyResponseModel(
                        "ed.deal",
                        List.of(
                            new PolicyResponseModel.Policy("ed.deal.read"),
                            new PolicyResponseModel.Policy("ed.deal.write")
                        )
                ),

                new PolicyResponseModel(
                        "ed.classifier",
                        List.of(
                                new PolicyResponseModel.Policy("ed.classifier.read"),
                                new PolicyResponseModel.Policy("ed.classifier.write")
                        )
                ),

                new PolicyResponseModel(
                        "ed.admin",
                        List.of(
                                new PolicyResponseModel.Policy("ed.admin.read"),
                                new PolicyResponseModel.Policy("ed.admin.write")
                        )
                ),
                new PolicyResponseModel(
                        "ed.settings",
                        List.of(
                                new PolicyResponseModel.Policy("ed.settings.read"),
                                new PolicyResponseModel.Policy("ed.settings.write")
                        )
                )
        );
    }
}
