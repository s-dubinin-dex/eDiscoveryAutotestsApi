package eDiscovery.helpers;

import eDiscovery.apiMethods.identity.ApiMethodsIdentity;
import eDiscovery.models.identity.UserInfo;

import java.util.Map;

public class Authorization {

    public static String getAccessToken(Map<String, String> authorizationScope){
        return ApiMethodsIdentity.connectToken(authorizationScope).jsonPath().get("access_token");
    }

    public static UserInfo getCurrentUserInfo(){
        return ApiMethodsIdentity.connectUserInfo().as(UserInfo.class);
    }
}
