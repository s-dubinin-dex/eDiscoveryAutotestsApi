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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateSearchPlaceRequestModelNotNull {
    public String name;
    public SearchPlaceCategoryType categoryType;
    public SearchPlaceType type;
    public SearchPlaceParametersModelNotNull parameters;
    public List<String> excludes;
    public String id;
}
