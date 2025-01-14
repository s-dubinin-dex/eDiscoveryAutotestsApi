package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlaceGroup;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.searchPlaceGroup.AddSearchPlaceGroupRequestModel;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

@DisplayName("Extended positive tests: Deal - SearchPlaceGroup")
public class SearchPlaceGroupExtendedPositiveTests extends TestBase {

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка групп мест поиска для отображения в списке групп мест поиска")
    @Description("Тест проверяет возможность получения списка групп мест поиска для отображения в списке групп мест поиска")
    @Test
    public void testGetSearchPlaceGroupListWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchPlaces")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .withIncludeDeleted(true)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка групп мест поиска для выпадающего списка групп мест поиска")
    @Description("Тест проверяет возможность получения списка групп мест поиска для выпадающего списка групп мест поиска")
    @Test
    public void testGetSearchPlaceGroupListWEBUIForFilterSearchPlaceGroup(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка групп мест поиска для отображения в списке групп мест поиска с фильтром по удалённым")
    @Description("Тест проверяет возможность получения списка групп мест для отображения в списке групп мест поиска с фильтром по удалённым")
    @Test
    public void testGetSearchPlaceGroupListWEBUIFilterWithDeleted(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and deletedUtc ne null")
                .withExpand("searchPlaces")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .withIncludeDeleted(true)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка групп мест поиска для отображения в списке групп мест поиска с фильтром по не удалённым")
    @Description("Тест проверяет возможность получения списка групп мест для отображения в списке групп мест поиска с фильтром по не удалённым")
    @Test
    public void testGetSearchPlaceGroupListWEBUIFilterWithNotDeleted(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'') and deletedUtc eq null")
                .withExpand("searchPlaces")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .withIncludeDeleted(false)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка групп мест поиска для отображения в списке групп мест поиска с сортировкой по Названию")
    @Description("Тест проверяет возможность получения списка групп мест для отображения в списке групп мест поиска с сортировкой по Названию")
    @Test
    public void testGetSearchPlaceGroupListWEBUIFilterWithSortingName(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchPlaces")
                .withOrderBy("name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .withIncludeDeleted(true)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение списка групп мест поиска")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка групп мест поиска для отображения в списке групп мест поиска с сортировкой по Описанию")
    @Description("Тест проверяет возможность получения списка групп мест для отображения в списке групп мест поиска с сортировкой по Описанию")
    @Test
    public void testGetSearchPlaceGroupListWEBUIFilterWithSortingDescription(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        Map<String, String> params = OdataParametersBuilder.builder()
                .withFilter("contains(tolower(name),'')")
                .withExpand("searchPlaces")
                .withOrderBy("description asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .withIncludeDeleted(true)
                .build();

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

    }

    @Epic("Сервис Deal")
    @Feature("Группы мест поиска")
    @Story("Получение группы мест поиска по id")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение группы мест поиска для загрузки данных для редактирования группы мест поиска")
    @Description("Тест проверяет возможность получения группы мест поиска для загрузки данных для редактирования группы мест поиска")
    @Test
    public void testGetSearchPlaceGroupWEBUI(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal().id))
                .description(getRandomName())
                .build();

        CommonSearchPlaceGroupResponseModel responseBodyCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        Map<String, String> params = OdataParametersBuilder.builder()
                .withExpand("searchPlaces")
                .build();

        CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupByIdPath(responseBodyCreation.id, params).as(CommonSearchPlaceGroupResponseModel.class);

    }

}
