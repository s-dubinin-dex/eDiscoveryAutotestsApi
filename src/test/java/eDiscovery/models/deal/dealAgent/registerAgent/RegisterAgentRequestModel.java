package eDiscovery.models.deal.dealAgent.registerAgent;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class RegisterAgentRequestModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String agentId;
    @JsonInclude()
    public String userName;
    @JsonInclude()
    public String machineName;
    @JsonInclude()
    public String osVersion;
    @JsonInclude()
    public String agentVersion;
    @JsonInclude()
    public String categoryType;
    @JsonInclude()
    public String type;
    @JsonInclude()
    public String hostType;
}
