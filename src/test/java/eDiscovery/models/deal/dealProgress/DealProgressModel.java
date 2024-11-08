package eDiscovery.models.deal.dealProgress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DealProgressModel {
    public String id;
    public DealTaskProgressModel progressInfo;
}
