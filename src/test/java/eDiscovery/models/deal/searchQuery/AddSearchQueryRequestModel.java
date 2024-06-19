package eDiscovery.models.deal.searchQuery;

import eDiscovery.helpers.enums.SearchQueryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSearchQueryRequestModel {
    public String name;
    public SearchQueryType type;
    public String value;
}
