package eDiscovery.models.deal.markerResult;

import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;

public class CommonMarkerResultFromDealModel {
    public String id;
    public String externalId;
    public String externalDetailId;
    public String externalObjectId;
    public String actionDate;
    public String filePath;
    public String fileName;
    public String innerFilePath;
    public String innerFileName;
    public boolean isStartMarkerFinded;
    public boolean isResultMarkerFinded;
    public String externalStartMarkerId;
    public String externalResultMarkerId;
    public MarkerModel startMarker;
    public MarkerModel resultMarker;
    public String errorReason;
    public CommonSearchPlaceResponseModel searchPlace;

    public static class MarkerModel {
        public String id;
        public String name;
        public String description;
        public boolean isActive;
        public String level;
        public String color;
    }
}
