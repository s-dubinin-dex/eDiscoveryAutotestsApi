package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSearchQueryRequestModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String type;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String value;
}
