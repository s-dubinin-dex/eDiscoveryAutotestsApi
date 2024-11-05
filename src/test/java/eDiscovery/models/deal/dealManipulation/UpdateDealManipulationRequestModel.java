package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.util.List;

@Builder
public class UpdateDealManipulationRequestModel {
    @JsonInclude()
    public String id;
    @JsonInclude()
    public String name;
    @JsonInclude()
    public List<String> searchPlaces;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> excludes;
    @JsonInclude()
    public List<String> searchQueries;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dealStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean quarantine;
}
