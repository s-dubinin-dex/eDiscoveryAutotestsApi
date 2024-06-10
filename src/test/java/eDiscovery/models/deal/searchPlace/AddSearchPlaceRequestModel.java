package eDiscovery.models.deal.searchPlace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSearchPlaceRequestModel {
    public String name;
    public String categoryType;
    public String type;
    public SearchPlaceParametersModel parameters;
    public List<String> excludes;
}
