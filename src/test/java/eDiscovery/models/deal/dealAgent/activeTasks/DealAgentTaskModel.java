package eDiscovery.models.deal.dealAgent.activeTasks;

import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;

import java.util.List;

public class DealAgentTaskModel {

    public String id;
    public String dealId;
    public String dealPriority;
    public String issueType;
    public List<String> dealExcludes;
    public String searchPlaceId;
    public String placeCategory;
    public String placeType;
    public String placeParameters;
    public List<String> placeExcludes;
    public String status;
    public List<CommonSearchQueryResponseModel> searchQueries;
    public String compressExportArchive;
    public boolean autoQuarantine;
    public boolean classifyBySearchPlace;
    public List<String> fileInfos;
    public String classifierProfileInfo;
    public String metadataFilterInfos;

}
