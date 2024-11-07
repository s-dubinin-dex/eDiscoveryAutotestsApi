package eDiscovery.models.deal.dealProgress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DealProgressModel {
    public String id;
    public ProgressInfoModel progressInfo;
}
