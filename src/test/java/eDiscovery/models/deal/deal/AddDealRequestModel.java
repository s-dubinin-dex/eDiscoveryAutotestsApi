package eDiscovery.models.deal.deal;

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
public class AddDealRequestModel {
    public String name;
    public List<String> searchPlaces;
    public String schedule;
    public String useSchedule;
    public List<String> excludes;
    public List<String> searchQueueries;
    public DealStatus dealStatus;
    public boolean quarantine;

}
