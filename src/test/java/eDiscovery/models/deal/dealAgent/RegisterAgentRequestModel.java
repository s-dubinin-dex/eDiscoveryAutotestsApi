package eDiscovery.models.deal.dealAgent;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String osVersion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String categoryType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String type;
    @JsonInclude()
    public String hostType;
}
