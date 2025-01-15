package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlaceGroup;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.data.dealService.DataGeneratorSearchPlaceGroup;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.models.deal.searchPlaceGroup.AddSearchPlaceGroupRequestModel;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;
import eDiscovery.models.odata.OdataListResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Deal - SearchPlaceGroup: Расширенные позитивные тесты")
public class SearchPlaceGroupExtendedPositiveTests extends TestBase {

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка odata параметров при получении списка групп мест поиска")
    class CheckGetSearchPlaceGroupListOdataParameters{

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с фильтрацией по названию группы места поиска")
        @Description("Тест проверяет возможность получения списка групп мест поиска с фильтрацией по названию группы места поиска")
        public void testGetSearchPlaceGroupListWithFilterName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("contains(name, '')")
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(requestParameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с фильтрацией по состоянию удаления")
        @Description("Тест проверяет возможность получения списка групп мест поиска с фильтрацией по состоянию удаления")
        public void testGetSearchPlaceListWithFilterDeletedUtcNeNull(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("deletedUtc ne null")
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(requestParameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с фильтрацией по состоянию удаления")
        @Description("Тест проверяет возможность получения списка групп мест поиска с фильтрацией по состоянию удаления")
        public void testGetSearchPlaceListWithFilterDeletedUtcEqNull(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withFilter("deletedUtc eq null")
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(requestParameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с сортировкой по названию")
        @Description("Тест проверяет возможность получения списка групп мест поиска с сортировкой по названию")
        public void testGetSearchPlaceGroupListWithSortingName(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("name asc")
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(requestParameters);

        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с сортировкой по описанию")
        @Description("Тест проверяет возможность получения списка групп мест поиска с сортировкой по описанию")
        public void testGetSearchPlaceGroupListWithSortingDescription(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> requestParameters  = OdataParametersBuilder.builder()
                    .withOrderBy("description asc")
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(requestParameters);

        }

        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с подсчётом количества результатов")
        @Description("Тест проверяет возможность получения списка групп мест поиска с подсчётом количества результатов")
        @Test
        public void testGetSearchPlaceGroupListWithCount(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withCount(true)
                    .build();

            Response response = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(parameters);

            OdataListResponseModel responseListWithCount = response.as(OdataListResponseModel.class);
            List<CommonSearchPlaceGroupResponseModel> responseElements = response.jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

            assertThat(Integer.parseInt(responseListWithCount.odataCount)).isEqualTo(responseElements.size());
        }

        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с ограничением количества результатов = 100")
        @Description("Тест проверяет возможность получения списка групп мест поиска с ограничением количества результатов = 100")
        @Test
        public void testGetSearchPlaceGroupListWithTop100(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withTop(100)
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с пропуском первых 10 результатов")
        @Description("Тест проверяет возможность получения списка групп мест поиска с пропуском первых 10 результатов")
        @Test
        public void testGetSearchPlaceGroupListWithSkip10(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withSkip(10)
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(parameters);
        }

        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка групп мест поиска с экспандом поля searchPlaces")
        @Description("Тест проверяет возможность получения списка групп мест поиска с экспандом поля searchPlaces")
        @Test
        public void testGetSearchPlaceGroupListWithExpandsearchPlaces(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withExpand("searchPlaces")
                    .build();

            ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(parameters);
        }

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Получение списка мест поиска с фильтром по удалённым местам поиска")
        @Description("Тест проверяет возможность получения списка мест поиска с фильтром по удалённым местам поиска")
        public void testGetSearchPlaceGroupListWithIncludeDeleted(){
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel searchPlaceGroupForDeletion = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchPlaceGroup.deleteSearchPlaceGroup(searchPlaceGroupForDeletion.id);

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            Map<String, String> parameters = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            List<CommonSearchPlaceGroupResponseModel> searchPlaceGroupListWithoutDeleted = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData().jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);
            List<CommonSearchPlaceGroupResponseModel> searchPlaceGroupListWithDeleted = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(parameters).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

            assertThat(searchPlaceGroupListWithoutDeleted.stream().filter(searchPlaceGroup -> searchPlaceGroup.id.equals(searchPlaceGroupForDeletion.id))).hasSize(0);
            assertThat(searchPlaceGroupListWithDeleted.stream().filter(searchPlaceGroup -> searchPlaceGroup.id.equals(searchPlaceGroupForDeletion.id))).hasSize(1);
        }

    }

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка полей удалённой группы мест поиска в списке")
    class CheckDeletedSearchPlaceGroupInList{

        @Test
        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Проверка полей в теле ответа при получении списка групп мест поиска по удалённой группе мест поиска")
        @Description("Тест проверяет поля в теле ответа при получении списка групп мест поиска по удалённой группе мест поиска")
        public void testGetDeletedSearchPlaceGroupWithOnlyRequiredParametersFromListOdataCheckResponseBody() {
            SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

            CommonSearchPlaceGroupResponseModel responseSearchPlaceGroupCreation = DataGeneratorSearchPlaceGroup.createSearchPlaceGroupWithOnlyRequiredParameters();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
            ApiMethodsSearchPlaceGroup.deleteSearchPlaceGroup(responseSearchPlaceGroupCreation.id);

            Map<String, String> params = OdataParametersBuilder.builder()
                    .withIncludeDeleted(true)
                    .build();

            SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
            CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData(params).jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class)
                    .stream().filter(searchPlaceGroup -> searchPlaceGroup.id.equals(responseSearchPlaceGroupCreation.id))
                    .findFirst().orElse(null);

            assertThat(responseBody).isNotNull();

            assertAll(
                    () -> assertThat(responseBody.id).isEqualTo(responseSearchPlaceGroupCreation.id),
                    () -> assertThat(responseBody.name).isEqualTo(responseSearchPlaceGroupCreation.name),
                    () -> assertThat(responseBody.description).isEqualTo(responseSearchPlaceGroupCreation.description),
                    () -> assertThat(responseBody.deletedUtc).matches(dateTimeCommonPattern())
            );
        }








    }

    @Nested
    @DisplayName("Deal - SearchPlaceGroup: Проверка работы методов, используемых на UI")
    class CheckSearchPlaceGroupUIMethods{

        @Epic("Сервис Deal")
        @Feature("Группы мест поиска")
        @Story("Получение списка групп мест поиска")
        @Tag("webui")
        @Severity(SeverityLevel.NORMAL)
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
        @Severity(SeverityLevel.NORMAL)
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

}