package eDiscovery.models.deal.searchPlaceGroup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public class UpdateSearchPlaceGroupRequestModel {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public List<String> searchPlaces;
    @JsonInclude()
    public String description;
}
