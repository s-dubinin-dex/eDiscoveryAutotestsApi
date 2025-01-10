package eDiscovery.data.adminService;

import com.github.javafaker.Faker;
import eDiscovery.apiMethods.admin.ApiMethodsEmployee;
import eDiscovery.helpers.admin.PredefinedRoles;
import eDiscovery.helpers.admin.RoleHelper;
import eDiscovery.models.admin.emplyee.AddEmployeeRequestModel;
import eDiscovery.models.admin.emplyee.CommonEmployeeResponseModel;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorEmployee {

    private static Faker faker = new Faker();

    public static AddEmployeeRequestModel getAddEmployeeModelWithOnlyRequiredParameters(){
        return AddEmployeeRequestModel.builder()
                .name(getRandomName())
                .roleId(RoleHelper.getRoleByName(PredefinedRoles.FULL_WRITE.name).id)
                .email(faker.internet().emailAddress())
                .build();

    }

    public static CommonEmployeeResponseModel createEmployeeWithOnlyRequiredParameters(){
        return ApiMethodsEmployee.addEmployee(getAddEmployeeModelWithOnlyRequiredParameters()).as(CommonEmployeeResponseModel.class);
    }
}
