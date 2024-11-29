package eDiscovery.models.deal.searchPlaceGroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonSearchPlaceGroupResponseModel {
    public String id;
    public String name;
    public List<CommonSearchPlaceResponseModel> searchPlaces;
    public String description;
    public String deletedUtc;
}
