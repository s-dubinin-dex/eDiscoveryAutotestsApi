package eDiscovery.models.deal.agents;

import eDiscovery.models.deal.agentActivity.CommonAgentActivityResponseModel;
import eDiscovery.models.odata.OdataEntityResponseModel;

public class CommonAgentsResponseModel extends OdataEntityResponseModel {
    public String id;
    public String userName;
    public String machineName;
    public String agentType;
    public String osVersion;
    public int agentVersion;
    public String agentVersionString;
    public SearchPlaceAgentModel searchPlace;
    public CommonAgentActivityResponseModel lastActivity;
    public String createdUtc;
}
