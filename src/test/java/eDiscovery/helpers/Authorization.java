package eDiscovery.helpers;

import eDiscovery.apiMethods.identity.ApiMethodsIdentity;
import eDiscovery.models.identity.UserInfo;

public class Authorization {

    public static String getToken(){
        return ApiMethodsIdentity.connectToken().jsonPath().get("access_token");
    }

    public static UserInfo getCurrentUserInfo(){
        return ApiMethodsIdentity.userInfo().as(UserInfo.class);
    }
}
