package eDiscovery.tests.apiTests.extendedPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsProfile;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.classifier.profile.CommonProfileResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Classifier - Profile")
public class ProfileExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение профиля категоризации по id для карточки профиля")
    @Description("Тест проверяет возможность получения профиля категоризации по id для карточки профиля")
    public void testGetProfileByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withExpand("baseMarker,rules($expand=searchQueries,marker,policy)")
                .build();

        CommonProfileResponseModel profileToCheck = ApiMethodsProfile.getProfileListOData().jsonPath().getList("value", CommonProfileResponseModel.class).get(0);

        CommonProfileResponseModel responseBody = ApiMethodsProfile.getProfileByIdPath(profileToCheck.id).as(CommonProfileResponseModel.class);

        assertThat(responseBody.id).isEqualTo(profileToCheck.id);
        assertThat(responseBody.name).isEqualTo(profileToCheck.name);
        assertThat(responseBody.canClassifyDocumentsWithMarker).isEqualTo(profileToCheck.canClassifyDocumentsWithMarker);
        assertThat(responseBody.considerTagsCriticality).isEqualTo(profileToCheck.considerTagsCriticality);
        assertThat(responseBody.isActive).isEqualTo(profileToCheck.isActive);
        assertThat(responseBody.isDefault).isEqualTo(profileToCheck.isDefault);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке")
    public void testGetProfileListOdataWebUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с фильтром по статусу профиля")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с фильтром по статусу профиля")
    public void testGetProfileListOdataWebUIWithFilterIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and isActive eq true")
                .withExpand("baseMarker,rules")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с сортировкой по Названию")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с сортировкой по Названию")
    public void testGetProfileListOdataWebUIWithSortingName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с сортировкой по Статусу")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с сортировкой по Статусу")
    public void testGetProfileListOdataWebUIWithSortingIsActive(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("isActive asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("", CommonProfileResponseModel.class);

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Профиль категоризации")
    @Story("Получение профилей категоризации")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка профилей категоризации для отображения в списке с сортировкой по Дефолтности профиля")
    @Description("Тест проверяет возможность получения списка профилей категоризации для отображения в списке с сортировкой по Дефолтности профиля")
    public void testGetProfileListOdataWebUIWithSortingIsDefault(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("baseMarker,rules")
                .withOrderBy("isDefault asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonProfileResponseModel> responseBody = ApiMethodsProfile.getProfileListOData(params).jsonPath().getList("", CommonProfileResponseModel.class);

    }

}
