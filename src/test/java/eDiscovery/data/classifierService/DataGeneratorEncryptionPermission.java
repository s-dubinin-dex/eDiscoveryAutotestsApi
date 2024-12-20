package eDiscovery.data.classifierService;

import eDiscovery.apiMethods.classifier.ApiMethodsEncryptionPermission;
import eDiscovery.models.classifier.encryptionPermission.CommonEncryptionPermissionResponseModel;

public class DataGeneratorEncryptionPermission {
    public static CommonEncryptionPermissionResponseModel getFirstEncryptionPermission(){
        return ApiMethodsEncryptionPermission.getEncryptionPermissionListOData().jsonPath().getList("value", CommonEncryptionPermissionResponseModel.class).get(0);
    }
}
