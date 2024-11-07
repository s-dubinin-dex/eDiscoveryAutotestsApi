package eDiscovery.models.deal.dealProgress;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProgressInfoModel {
    public String id;
    public int totalFiles;
    public int totalVolume;
    public int proceededFiles;
    public int proceededVolume;
    public Integer timeToFinish;
}
