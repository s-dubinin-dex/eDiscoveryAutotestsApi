package eDiscovery.models.admin.emplyee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UpdateEmployeeRequestModel {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String roleId;

    public UpdateEmployeeRequestModel(CommonEmployeeResponseModel responseEmployeeCreation){
        this.id = responseEmployeeCreation.id;
        this.name = responseEmployeeCreation.name;
        this.roleId = responseEmployeeCreation.roleId;
    }
}
