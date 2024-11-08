package eDiscovery.models.deal.dealManipulation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonDealManipulationResponseModel {

    public String id;
    public String name;
    public String dealPriority;
    public boolean quarantine;
    public List<String> fileTypes;
    public List<DealSearchPlaceModel> searchPlaces;
    public List<ClassifySearchPlacesModel> classifySearchPlaces;
    public List<SearchPlaceGroupsModel> searchPlaceGroups;
    public List<DealSearchQueryModel> dealSearchQueries;
    public DealTaskProgressModel progressInfo;
    public String dealStatus;
    public List<String> excludes;
    public String searchMask;
    public boolean needClassify;
    public String classifierProfileId;
    public String createdUtc;
    public String creatorUserId;
    public String creatorUserName;

}
