package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Builder
@AllArgsConstructor
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

    public UpdateSearchPlaceRequestModel(CommonSearchPlaceResponseModel creationResponseBody){
        this.id = creationResponseBody.id;
        this.name = creationResponseBody.name;
        this.categoryType = creationResponseBody.categoryType;
        this.type = creationResponseBody.type;
        this.parameters = creationResponseBody.parameters;
        this.excludes = creationResponseBody.excludes;
    }
}
