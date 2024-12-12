package eDiscovery.models.classifier.profile;

import eDiscovery.models.OdataEntity;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.rule.CommonRuleResponseModel;

import java.util.List;

public class CommonProfileResponseModel extends OdataEntity {
    public String id;
    public String name;
    public boolean canClassifyDocumentsWithMarker;
    public boolean considerTagsCriticality;
    public List<CommonRuleResponseModel> rules;
    public CommonMarkerResponseModel baseMarker;
    public String basePolicy;
    public boolean isDefault;
    public boolean isActive;
    public String markerInfos;
    public String createdUtc;
    public String creatorUserId;
    public String creatorUserName;
    public String updatedUtc;
    public String editorUserId;
    public String editorUserName;
    public String encryptionPermissionInfos;
}
