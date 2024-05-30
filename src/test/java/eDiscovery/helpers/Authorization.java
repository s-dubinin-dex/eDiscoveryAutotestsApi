package eDiscovery.helpers;

import eDiscovery.apiMethods.identity.ApiMethodsIdentity;

public class Authorization {
    public static String getToken(){
        return ApiMethodsIdentity.connectToken().jsonPath().get("access_token");
    }
}
