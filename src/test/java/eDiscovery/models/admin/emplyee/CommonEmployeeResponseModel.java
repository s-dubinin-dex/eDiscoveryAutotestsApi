package eDiscovery.models.admin.emplyee;

import eDiscovery.models.OdataEntity;

public class CommonEmployeeResponseModel extends OdataEntity {
    public String id;
    public String name;
    public String roleId;
    public String email;
    public String role;
    public String activationDate;
    public String createdUtc;
    public String deletedUtc;
}
