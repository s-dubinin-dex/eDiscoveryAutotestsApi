package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSearchPlaceRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SearchPlaceCategoryType categoryType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SearchPlaceType type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SearchPlaceParametersModel parameters;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> excludes;
}
