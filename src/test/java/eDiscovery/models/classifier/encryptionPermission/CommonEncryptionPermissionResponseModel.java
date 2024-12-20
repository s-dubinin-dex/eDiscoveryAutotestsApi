package eDiscovery.models.classifier.encryptionPermission;

import eDiscovery.models.odata.OdataEntityResponseModel;

public class CommonEncryptionPermissionResponseModel extends OdataEntityResponseModel {
    public String id;
    public int policyId;
    public String name;
    public String algorithm;
    public String description;
    public boolean isActive;
    public String dssVersion;
}
