package eDiscovery.models.classifier.markerResult;

import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.profile.CommonProfileResponseModel;

public class CommonMarkerResultResponseModel {

    public String id;
    public String externalId;
    public String externalDetailId;
    public String externalObjectId;
    public String actionDate;
    public String filePath;
    public String fileName;
    public String innerFilePath;
    public String innerFileName;
    public String processDescription;
    public String errorReason;
    public boolean isStartMarkerFinded;
    public boolean isResultMarkerFinded;
    public String externalStartMarkerId;
    public String externalResultMarkerId;
    public CommonMarkerResponseModel startMarker;
    public CommonMarkerResponseModel resultMarker;
    public CommonProfileResponseModel profileUsed;
    public boolean isStartPolicyFinded;
    public boolean isResultPolicyFinded;
    public String externalStartPolicyId;
    public String externalResultPolicyId;
    public String startPolicy;
    public String resultPolicy;

}
