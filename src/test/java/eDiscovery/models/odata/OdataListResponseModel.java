package eDiscovery.models.odata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OdataListResponseModel {
    @JsonProperty("@odata.context")
    public String odataContext;
    @JsonProperty("@odata.count")
    public String odataCount;
}
