package eDiscovery.models.deal.metadataFilter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CommonMetadataFilterResponseBody {
    @JsonProperty("@odata.context")
    public String odataContext;
    public String id;
    public String name;
    public String dataSearchType;
    public List<MetadataFilterAttributeValue> attributeValues;
    public String deletedUtc;
}
