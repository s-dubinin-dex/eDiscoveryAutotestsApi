package eDiscovery.models.classifier.rule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonRuleResponseModel {
    public String id;
    public String name;
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
