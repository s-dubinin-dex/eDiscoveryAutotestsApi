package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ClassifierDealData {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean needClassify;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String classifierProfileId;
}
