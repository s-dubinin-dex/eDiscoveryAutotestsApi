package eDiscovery.models.deal.agents;

import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;

import java.util.List;

public class SearchPlaceAgentModel extends CommonSearchPlaceResponseModel {
    public List<String> searchPlaceGroups;
    public List<String> deals;
    public List<String> classifyDeals;
    public String creatorUserId;
    public String editorUserId;
    public String updatedUtc;
}
