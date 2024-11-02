package eDiscovery.models.admin.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonRoleResponseModel {
    public String id;
    public String name;
    public List<String> policies;
    public String description;
    public String createdUtc;
    public String deletedUtc;
}
