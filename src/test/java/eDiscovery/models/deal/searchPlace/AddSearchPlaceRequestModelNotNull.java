package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSearchPlaceRequestModelNotNull {
    public String name;
    public SearchPlaceCategoryType categoryType;
    public SearchPlaceType type;
    public SearchPlaceParametersModelNotNull parameters;
    public List<String> excludes;
}
