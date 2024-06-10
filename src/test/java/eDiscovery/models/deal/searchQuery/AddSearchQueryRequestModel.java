package eDiscovery.models.deal.searchQuery;

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
    public String type;
    public String value;
}
