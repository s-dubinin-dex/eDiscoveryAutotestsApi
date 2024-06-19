package eDiscovery.models.deal.dealManipulation;

import eDiscovery.helpers.enums.DealStatus;

import java.util.List;

public class CommonDealManipulationResponseModel {
    public String id;
    public String name;
    public boolean quarantine;
    public List<String> searchPlaces;
    public List<String> searchQueueries;
    public DealStatus dealStatus;
    public String creatorUserId;
    public String creatorUserName;
}
