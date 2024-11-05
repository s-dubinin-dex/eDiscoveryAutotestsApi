package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveTasksRequestsModel {
    public String agentId;
    public String idPlace;
    public String issueType;
}
