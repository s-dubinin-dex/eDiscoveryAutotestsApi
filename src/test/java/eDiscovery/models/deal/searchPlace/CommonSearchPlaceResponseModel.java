package eDiscovery.models.deal.searchPlace;

import eDiscovery.models.OdataEntity;
import lombok.Getter;

import java.util.List;

@Getter
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
