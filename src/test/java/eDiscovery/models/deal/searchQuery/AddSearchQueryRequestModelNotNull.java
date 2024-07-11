package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import eDiscovery.helpers.enums.SearchQueryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSearchQueryRequestModelNotNull {
    public SearchQueryType type;
    public String name;
    public String value;
}
