package eDiscovery.models.classifier.rule;

import eDiscovery.models.OdataEntity;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;

import java.util.List;

public class CommonRuleResponseModel extends OdataEntity {
    public String id;
    public String name;
    public int order;
    public CommonMarkerResponseModel marker;
    public CommonEncryptionPermissionResponseModel policy;
    public boolean isActive;
    public List<CommonSearchQueryClassifierResponseModel> searchQueries;
    public List<String> profiles;
    public String creatorUserId;
    public String creatorUserName;
    public String createdUtc;
    public String deletedUtc;
}
