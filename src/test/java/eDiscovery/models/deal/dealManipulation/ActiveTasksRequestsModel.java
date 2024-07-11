package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveTasksRequestsModel {
    public String agentId;
    public String idPlace;
    public String issueType;
}
