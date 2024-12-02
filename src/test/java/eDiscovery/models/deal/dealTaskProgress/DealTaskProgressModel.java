package eDiscovery.models.deal.dealTaskProgress;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DealTaskProgressModel {
    public String id;
    public int totalFiles;
    public long totalVolume;
    public int proceededFiles;
    public long proceededVolume;
    public Integer timeToFinish;
}
