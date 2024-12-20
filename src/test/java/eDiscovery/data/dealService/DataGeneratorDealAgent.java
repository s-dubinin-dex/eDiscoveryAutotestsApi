package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsAgents;
import eDiscovery.apiMethods.deal.ApiMethodsDealAgent;
import eDiscovery.helpers.enums.HostType;
import eDiscovery.models.deal.dealAgent.RegisterAgentRequestModel;
import eDiscovery.models.deal.dealAgent.RegisterAgentResponseModel;
import io.restassured.response.Response;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorDealAgent {
    public static RegisterAgentResponseModel createDealAgentWithOnlyRequiredParameters(){
        return ApiMethodsDealAgent.registerAgent(getDealAgentModelWithOnlyRequiredParameters()).as(RegisterAgentResponseModel.class);
    }

    public static RegisterAgentRequestModel getDealAgentModelWithOnlyRequiredParameters(){
        return RegisterAgentRequestModel.builder()
                .userName(getRandomName())
                .machineName(getRandomName())
                .hostType(HostType.Local.name())
                .build();
    }
}
