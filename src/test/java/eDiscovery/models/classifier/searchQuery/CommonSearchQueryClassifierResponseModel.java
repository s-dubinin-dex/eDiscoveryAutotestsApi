package eDiscovery.models.classifier.searchQuery;

import eDiscovery.models.odata.OdataEntityResponseModel;

public class CommonSearchQueryClassifierResponseModel extends OdataEntityResponseModel {
    public String name;
    public String type;
    public String id;
    public String value;
    public String createdUtc;
    public String deletedUtc;
}
