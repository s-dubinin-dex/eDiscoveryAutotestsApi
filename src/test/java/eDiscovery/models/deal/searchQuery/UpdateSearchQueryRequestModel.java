package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class UpdateSearchQueryRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String type;
    @JsonInclude()
    public String value;
    @JsonInclude()
    public String id;
}
