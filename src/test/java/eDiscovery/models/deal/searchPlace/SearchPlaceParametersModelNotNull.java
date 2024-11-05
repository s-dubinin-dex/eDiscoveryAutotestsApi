package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchPlaceParametersModelNotNull {
    public String uri;
    public String username;
    public String password;
}
