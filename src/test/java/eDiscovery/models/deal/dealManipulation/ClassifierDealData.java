package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ClassifierDealData {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean needClassify;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String classifierProfileId;
}
