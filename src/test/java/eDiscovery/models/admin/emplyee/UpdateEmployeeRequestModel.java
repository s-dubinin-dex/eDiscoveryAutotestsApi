package eDiscovery.models.admin.emplyee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public class UpdateEmployeeRequestModel {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String roleId;
}
