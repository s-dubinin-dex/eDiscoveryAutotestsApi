package eDiscovery.models.deal.dealManipulation;

import eDiscovery.models.odata.OdataEntityResponseModel;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;

import java.util.List;

public class CommonDealManipulationResponseModel extends OdataEntityResponseModel {
    public String id;
    public String name;
    public String dealPriority;
    public boolean quarantine;
    public DealMetadataFilterModel metadataFilter;
    public List<DealSearchPlaceModel> searchPlaces;
    public List<ClassifySearchPlacesModel> classifySearchPlaces;
    public List<SearchPlaceGroupsModel> searchPlaceGroups;
    public List<DealSearchQueryModel> dealSearchQueries;
    public DealTaskProgressModel progressInfo;
    public String dealStatus;
    public List<String> excludes;
    public boolean needClassify;
    public String classifierProfileId;
    public ClassifierDealData classifierDealData;
    public String createdUtc;
    public String creatorUserId;
    public String creatorUserName;
}
