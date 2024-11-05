package eDiscovery.helpers.admin;

import eDiscovery.apiMethods.admin.ApiMethodsRole;
import eDiscovery.models.admin.role.CommonRoleResponseModel;

import java.util.List;

public class RoleHelper {

    public static CommonRoleResponseModel getRoleById(String id){
        List<CommonRoleResponseModel> roles = ApiMethodsRole.getRolesListOData().jsonPath().getList("value", CommonRoleResponseModel.class);

        return roles.stream()
                .filter(role -> role.id.equals(id))
                .findFirst()
                .orElse(null);

    }

    public static CommonRoleResponseModel getRoleByName(String name){
        List<CommonRoleResponseModel> roles = ApiMethodsRole.getRolesListOData().jsonPath().getList("value", CommonRoleResponseModel.class);

        return roles.stream()
                .filter(role -> role.name.equals(name))
                .findFirst()
                .orElse(null);

    }
}
