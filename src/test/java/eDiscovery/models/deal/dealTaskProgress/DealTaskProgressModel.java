package eDiscovery.models.deal.dealTaskProgress;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DealTaskProgressModel {
    public String id;
    public int totalFiles;
    public int totalVolume;
    public int proceededFiles;
    public int proceededVolume;
    public Integer timeToFinish;
}
