package eDiscovery.models.deal.searchPlace;

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
    public String name;
    public SearchPlaceCategoryType categoryType;
    public SearchPlaceType type;
    public SearchPlaceParametersModel parameters;
    public List<String> excludes;
}
