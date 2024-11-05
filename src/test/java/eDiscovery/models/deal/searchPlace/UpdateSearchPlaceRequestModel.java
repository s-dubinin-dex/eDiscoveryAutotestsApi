package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.util.List;

@Builder
public class UpdateSearchPlaceRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String categoryType;
    @JsonInclude()
    public String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SearchPlaceParametersModel parameters;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> excludes;
    @JsonInclude()
    public String id;
}
