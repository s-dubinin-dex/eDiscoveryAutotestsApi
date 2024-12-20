package eDiscovery.models.deal.dealProgress;

import eDiscovery.models.odata.OdataEntityResponseModel;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;

public class DealProgressModel extends OdataEntityResponseModel {
    public String id;
    public DealTaskProgressModel progressInfo;
}
