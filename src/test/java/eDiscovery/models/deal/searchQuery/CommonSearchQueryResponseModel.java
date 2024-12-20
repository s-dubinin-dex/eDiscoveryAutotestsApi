package eDiscovery.models.deal.searchQuery;

import eDiscovery.models.odata.OdataEntityResponseModel;

public class CommonSearchQueryResponseModel extends OdataEntityResponseModel {
    public String name;
    public String type;
    public String id;
    public String value;
    public String createdUtc;
    public String deletedUtc;
}
