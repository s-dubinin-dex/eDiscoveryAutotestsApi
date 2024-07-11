package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSearchPlaceRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String categoryType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SearchPlaceParametersModel parameters;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> excludes;
    @JsonInclude()
    public String id;
}
