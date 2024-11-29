package eDiscovery.models.classifier.encryptionPermission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonEncryptionPermissionResponseModel {
    public String id;
    public int policyId;
    public String name;
    public String algorithm;
    public String description;
    public boolean isActive;
    public String dssVersion;
}
