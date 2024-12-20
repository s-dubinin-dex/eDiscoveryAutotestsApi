package eDiscovery.models.deal.searchPlaceGroup;

import eDiscovery.models.OdataEntity;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;

import java.util.List;

public class CommonSearchPlaceGroupResponseModel extends OdataEntity {
    public String id;
    public String name;
    public List<CommonSearchPlaceResponseModel> searchPlaces;
    public String description;
    public String deletedUtc;
}
