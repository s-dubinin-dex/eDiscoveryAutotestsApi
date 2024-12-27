package eDiscovery.models.deal.dealTask;

import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;

import java.util.List;

public class DealTaskModel {
    public String id;
    public String dealPriority;
    public String taskType;
    public String agentId;
    public String dealId;
    public String dealName;
    public CommonSearchPlaceResponseModel searchPlace;
    public String progressInfo;
    public List<String> searchQueries;
    public String taskStartedUtc;
    public String taskFinishedUtc;
    public String status;
    public String errors;
    public String exportFileName;
    public String exportFileUrl;
    public List<String> discoveredDocuments;
    public String creatorUserId;
    public String creatorUserName;
    public String editorUserId;
    public String editorUserName;
    public String createdUtc;
    public String updatedUtc;
}
