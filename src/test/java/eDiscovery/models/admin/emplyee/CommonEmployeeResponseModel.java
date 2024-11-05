package eDiscovery.models.admin.emplyee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonEmployeeResponseModel {
    public String id;
    public String name;
    public String roleId;
    public String email;
    public String role;
    public String activationDate;
    public String createdUtc;
    public String deletedUtc;
}
