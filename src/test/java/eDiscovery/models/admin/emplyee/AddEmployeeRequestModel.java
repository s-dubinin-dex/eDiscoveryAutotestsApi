package eDiscovery.models.admin.emplyee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class AddEmployeeRequestModel {
    @JsonInclude
    public String name;
    @JsonInclude
    public String roleId;
    @JsonInclude
    public String email;
}
