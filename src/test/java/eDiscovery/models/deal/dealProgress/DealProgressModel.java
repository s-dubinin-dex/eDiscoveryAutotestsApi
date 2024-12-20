package eDiscovery.models.deal.dealProgress;

import eDiscovery.models.OdataEntity;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;

public class DealProgressModel extends OdataEntity {
    public String id;
    public DealTaskProgressModel progressInfo;
}
