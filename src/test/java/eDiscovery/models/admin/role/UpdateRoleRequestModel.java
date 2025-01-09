package eDiscovery.models.admin.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleRequestModel {
    @JsonInclude
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public List<String> policies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;

    public UpdateRoleRequestModel(CommonRoleResponseModel responseRoleCreation){
        this.id = responseRoleCreation.id;
        this.name = responseRoleCreation.name;
        this.policies = responseRoleCreation.policies;
        this.description = responseRoleCreation.description;
    }
}

