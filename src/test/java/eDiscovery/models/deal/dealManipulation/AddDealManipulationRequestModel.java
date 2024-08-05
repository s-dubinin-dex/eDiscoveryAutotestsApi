package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDealManipulationRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude()
    public List<String> searchPlaces;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> excludes;
    @JsonInclude()
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> searchQueries;
    public String dealStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean quarantine;
}
