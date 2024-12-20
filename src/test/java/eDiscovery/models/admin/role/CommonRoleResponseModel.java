package eDiscovery.models.admin.role;

import eDiscovery.models.odata.OdataEntityResponseModel;

import java.util.List;

public class CommonRoleResponseModel extends OdataEntityResponseModel {
    public String id;
    public String name;
    public List<String> policies;
    public String description;
    public String createdUtc;
    public String deletedUtc;
}
