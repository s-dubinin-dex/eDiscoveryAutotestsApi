package eDiscovery.helpers;

import java.util.Map;

public class AuthorizationScope {

    public static Map<String, String> getAdminClientScope(){
        return Map.of(
                "client_id", "admin.client",
                "client_secret","9F45EA47-9BD6-48D8-B218-273A256DB093",
                "grant_type", "password",
                "username", "test@gmail.com",
                "password","005"
        );
    }

    public static Map<String, String> getLocalAgentClientScope(){
        return Map.of(
                "client_id", "localAgent.client",
                "client_secret","B43B818D-EB5C-4A75-B360-859BB81B5B5A",
                "grant_type", "client_credentials",
                "username", "crossService.agent2deal"
        );
    }

    public static Map<String, String> getCloudAgentClientScope(){
        return Map.of(
                "client_id", "cloudAgent.client",
                "client_secret","E4775430-3684-45FE-81FA-F31EE9DC13A7",
                "grant_type", "client_credentials",
                "username", "crossService.agent2deal"
        );
    }

}
