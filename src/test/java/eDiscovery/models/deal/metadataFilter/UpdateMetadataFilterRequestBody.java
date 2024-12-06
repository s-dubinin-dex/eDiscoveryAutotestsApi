package eDiscovery.models.deal.metadataFilter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public class UpdateMetadataFilterRequestBody {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String dataSearchType;
    @JsonInclude()
    public List<MetadataFilterAttributeValue> filterAttributeValues;
}
