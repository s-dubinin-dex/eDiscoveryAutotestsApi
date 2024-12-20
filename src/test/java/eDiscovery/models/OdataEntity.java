package eDiscovery.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class OdataEntity {
    @JsonProperty("@odata.context")
    public String odataContext;
}
