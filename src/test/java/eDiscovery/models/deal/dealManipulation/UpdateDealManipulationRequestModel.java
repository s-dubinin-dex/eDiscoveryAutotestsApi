package eDiscovery.models.deal.dealManipulation;

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
    public String id;
    public String name;
    public List<String> searchPlaces;
    public String schedule;
    public boolean useSchedule;
    public List<String> excludes;
    public List<String> searchQueueries;
    public DealStatus dealStatus;
    public boolean quarantine;
    int findedDocumentsCount;
}
