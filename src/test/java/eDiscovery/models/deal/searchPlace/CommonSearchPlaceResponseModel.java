package eDiscovery.models.deal.searchPlace;

import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;

import java.util.List;

public class CommonSearchPlaceResponseModel {
    public String name;
    public SearchPlaceCategoryType categoryType;
    public SearchPlaceType type;
    public SearchPlaceParametersModel parameters;
    public List<String> excludes;;
    public String id;
}
