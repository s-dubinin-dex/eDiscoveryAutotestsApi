package eDiscovery.models.deal.dealAgent.activeTasks;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class ActiveTasksRequestsModel {
    @JsonInclude()
    public String agentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String idPlace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String issueType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String ipAddress;
}
