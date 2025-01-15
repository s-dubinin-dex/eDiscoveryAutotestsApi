package eDiscovery.models.deal.searchPlaceGroup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class UpdateSearchPlaceGroupRequestModel {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public List<String> searchPlaces;
    @JsonInclude()
    public String description;

    public UpdateSearchPlaceGroupRequestModel(CommonSearchPlaceGroupResponseModel responseCreation){
        this.id = responseCreation.id;
        this.name = responseCreation.name;
        this.searchPlaces = responseCreation.searchPlaces.stream().map(searchPlace -> searchPlace.id).toList();
        this.description = responseCreation.description;
    }
}
