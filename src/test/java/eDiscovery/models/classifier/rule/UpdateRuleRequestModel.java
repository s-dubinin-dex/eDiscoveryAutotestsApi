package eDiscovery.models.classifier.rule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public class UpdateRuleRequestModel {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String markerId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String policyId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean isActive;
    @JsonInclude()
    public List<String> searchQueries;
}
