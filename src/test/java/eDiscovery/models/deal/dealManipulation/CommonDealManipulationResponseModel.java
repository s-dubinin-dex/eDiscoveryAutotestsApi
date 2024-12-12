package eDiscovery.models.deal.dealManipulation;

import eDiscovery.models.OdataEntity;
import eDiscovery.models.deal.dealTaskProgress.DealTaskProgressModel;

import java.util.List;

public class CommonDealManipulationResponseModel extends OdataEntity {

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
    public String createdUtc;
    public String creatorUserId;
    public String creatorUserName;
    public ClassifierDealData classifierDealData;
    public String deletedUtc;
}
