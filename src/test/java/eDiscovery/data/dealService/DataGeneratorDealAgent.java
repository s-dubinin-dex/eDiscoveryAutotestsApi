package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.helpers.enums.HostType;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.deal.dealAgent.RegisterAgentRequestModel;
import eDiscovery.models.deal.dealAgent.RegisterAgentResponseModel;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorDealAgent {

    public static RegisterAgentResponseModel createDealAgentWithOnlyRequiredParametersLocal(){
        return ApiMethodsDealAgent.registerAgent(getDealAgentModelWithOnlyRequiredParametersLocal()).as(RegisterAgentResponseModel.class);
    }

    public static RegisterAgentRequestModel getDealAgentModelWithOnlyRequiredParametersLocal(){
        RegisterAgentRequestModel requestModel = getBasicDealAgentModelWithOnlyRequiredParametersWithoutHostType();
        requestModel.hostType = HostType.Local.name();
        requestModel.categoryType = SearchPlaceCategoryType.Workspace.name();

        return requestModel;
    }

    public static RegisterAgentResponseModel createDealAgentWithOnlyRequiredParametersCloud(){
        return ApiMethodsDealAgent.registerAgent(getDealAgentModelWithOnlyRequiredParametersCloud()).as(RegisterAgentResponseModel.class);
    }

    public static RegisterAgentRequestModel getDealAgentModelWithOnlyRequiredParametersCloud(){
        RegisterAgentRequestModel requestModel = getBasicDealAgentModelWithOnlyRequiredParametersWithoutHostType();
        requestModel.hostType = HostType.Cloud.name();
        requestModel.categoryType = SearchPlaceCategoryType.FileShare.name();
        requestModel.type = SearchPlaceType.SMB.name();

        return requestModel;
    }

    public static RegisterAgentRequestModel getBasicDealAgentModelWithOnlyRequiredParametersWithoutHostType(){
        return RegisterAgentRequestModel.builder()
                .userName(getRandomName())
                .machineName(getRandomName())
                .osVersion("Win 11 test")
                .agentVersion("0.3")
                .build();
    }
}
