package eDiscovery.models.deal.searchPlace;

import eDiscovery.models.OdataEntity;

import java.util.List;

public class CommonSearchPlaceResponseModel extends OdataEntity {
    public String name;
    public String categoryType;
    public String type;
    public SearchPlaceParametersModel parameters;
    public List<String> excludes;
    public String id;
    public String createdUtc;
    public String deletedUtc;
}
