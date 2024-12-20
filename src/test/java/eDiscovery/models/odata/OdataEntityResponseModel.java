package eDiscovery.models.odata;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class OdataEntityResponseModel {
    @JsonProperty("@odata.context")
    public String odataContext;
}
