package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public class AddDealManipulationRequestModel {
    @JsonInclude()
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dealPriority;
    @JsonInclude()
    public List<String> searchPlaces;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> classifySearchPlaces;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> searchPlaceGroups;
    @JsonInclude()
    public List<DealSearchQueryModel> dealSearchQueries;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<String> excludes;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean quarantine;
    @JsonInclude()
    public ClassifierDealData classifierDealData;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String metadataFilterId;
}
