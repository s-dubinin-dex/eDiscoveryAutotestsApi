package eDiscovery.models.deal.dealManipulation;

import eDiscovery.helpers.enums.DealStatus;

public class DealStatusHistoryModel {
    public String id;
    public String dealId;
    public DealStatus dealStatus;
    public String changedStatusUserId;
    public String changedStatusUtc;
    public String changedStatusUserName;
}
