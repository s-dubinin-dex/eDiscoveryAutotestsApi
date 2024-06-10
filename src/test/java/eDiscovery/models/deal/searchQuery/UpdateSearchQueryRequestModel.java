package eDiscovery.models.deal.searchQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSearchQueryRequestModel {
    public String name;
    public String type;
    public String value;
    public String id;
}
