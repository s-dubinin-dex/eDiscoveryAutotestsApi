package eDiscovery.models.deal.searchQuery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UpdateSearchQueryRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude()
    public String type;
    @JsonInclude()
    public String value;
    @JsonInclude()
    public String id;

    public UpdateSearchQueryRequestModel(CommonSearchQueryResponseModel creationResponseBody){
        this(
                creationResponseBody.name,
                creationResponseBody.type,
                creationResponseBody.value,
                creationResponseBody.id
        );
    }
}
