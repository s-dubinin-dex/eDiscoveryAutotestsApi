package eDiscovery.models.deal.metadataFilter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonMetadataFilterResponseBody {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String dataSearchType;
    @JsonInclude()
    public List<MetadataFilterAttributeValue> attributeValues;
}
