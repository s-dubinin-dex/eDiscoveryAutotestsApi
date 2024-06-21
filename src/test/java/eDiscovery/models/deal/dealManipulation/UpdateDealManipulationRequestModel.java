package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonInclude;
import eDiscovery.helpers.enums.DealStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    public List<String> searchQueueries;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public DealStatus dealStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean quarantine;
}
