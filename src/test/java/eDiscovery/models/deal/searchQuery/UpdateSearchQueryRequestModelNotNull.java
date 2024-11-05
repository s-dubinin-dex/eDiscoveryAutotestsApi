package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateSearchQueryRequestModelNotNull {
    public String name;
    public String type;
    public String value;
    public String id;
}
