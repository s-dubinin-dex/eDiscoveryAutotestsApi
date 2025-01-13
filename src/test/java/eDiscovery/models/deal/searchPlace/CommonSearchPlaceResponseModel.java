package eDiscovery.models.deal.searchPlace;

import eDiscovery.models.odata.OdataEntityResponseModel;
import lombok.Getter;

import java.util.List;

@Getter
public class CommonSearchPlaceResponseModel extends OdataEntityResponseModel {
    public String id;
    public String name;
    public String categoryType;
    public String type;
    public SearchPlaceParametersModel parameters;
    public List<String> excludes;
    public String createdUtc;
    public String deletedUtc;
}
