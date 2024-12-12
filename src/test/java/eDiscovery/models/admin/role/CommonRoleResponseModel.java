package eDiscovery.models.admin.role;

import eDiscovery.models.OdataEntity;

import java.util.List;

public class CommonRoleResponseModel extends OdataEntity {
    public String id;
    public String name;
    public List<String> policies;
    public String description;
    public String createdUtc;
    public String deletedUtc;
}
