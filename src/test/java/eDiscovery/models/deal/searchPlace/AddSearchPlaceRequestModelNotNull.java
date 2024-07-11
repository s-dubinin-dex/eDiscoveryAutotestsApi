package eDiscovery.models.deal.searchPlace;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddSearchPlaceRequestModelNotNull {
    public String name;
    public String categoryType;
    public String type;
    public SearchPlaceParametersModelNotNull parameters;
    public List<String> excludes;
}
