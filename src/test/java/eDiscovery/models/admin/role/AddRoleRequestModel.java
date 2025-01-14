package eDiscovery.models.admin.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public class AddRoleRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude()
    public List<String> policies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
}
