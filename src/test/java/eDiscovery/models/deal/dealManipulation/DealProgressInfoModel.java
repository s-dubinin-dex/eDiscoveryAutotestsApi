package eDiscovery.models.deal.dealManipulation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DealProgressInfoModel {
    public String id;
    public int totalFiles;
    public int totalVolume;
    public int proceededFiles;
    public int proceededVolume;
    public int timeToFinish;
}
