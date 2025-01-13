package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Builder
@AllArgsConstructor
public class UpdateDealManipulationRequestModel {
    @JsonInclude()
    public String id;
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

    public UpdateDealManipulationRequestModel(CommonDealManipulationResponseModel responseCreation){
        this.id = responseCreation.id;
        this.name = responseCreation.name;
        this.dealPriority = responseCreation.dealPriority;
        this.searchPlaces = responseCreation.searchPlaces.stream().map(searchPlace -> searchPlace.id).toList();;
        this.classifySearchPlaces = responseCreation.classifySearchPlaces.stream().map(classifySearchPlace -> classifySearchPlace.id).toList();;
        this.searchPlaceGroups = responseCreation.searchPlaceGroups.stream().map(searchPlaceGroup -> searchPlaceGroup.id).toList();;
        this.dealSearchQueries = responseCreation.dealSearchQueries.stream().map(dealSearchQuery -> new DealSearchQueryModel(dealSearchQuery.id, dealSearchQuery.isActive)).toList();
        this.excludes = responseCreation.excludes;
        this.quarantine = responseCreation.quarantine;
        this.classifierDealData = new ClassifierDealData(responseCreation.needClassify, responseCreation.classifierProfileId);
        this.metadataFilterId = responseCreation.metadataFilter == null ? null : responseCreation.metadataFilter.id;
    }
}
