package eDiscovery.models.classifier.encryptionPermission;

import eDiscovery.models.OdataEntity;

public class CommonEncryptionPermissionResponseModel extends OdataEntity {
    public String id;
    public int policyId;
    public String name;
    public String algorithm;
    public String description;
    public boolean isActive;
    public String dssVersion;
}
