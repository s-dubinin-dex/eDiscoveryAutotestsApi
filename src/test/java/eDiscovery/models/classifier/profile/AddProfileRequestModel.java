package eDiscovery.models.classifier.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public class AddProfileRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean canClassifyDocumentsWithMarker;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean considerTagsCriticality;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ClassifierRulesModel> classifierRules;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String baseMarkerId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String basePolicyId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean isDefault;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean isActive;
}
