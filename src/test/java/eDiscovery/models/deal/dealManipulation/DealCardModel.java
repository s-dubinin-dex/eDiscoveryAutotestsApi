package eDiscovery.models.deal.dealManipulation;

import java.util.List;

public class DealCardModel {
    public List<DealStatusHistoryModel> dealStatusHistories;
    public String id;
    public String name;
    public boolean quarantine;
    public List<String> searchPlaces;
    public List<String> searchQueries;
    public String dealStatus;
    public String creatorUserId;
    public String creatorUserName;
}
