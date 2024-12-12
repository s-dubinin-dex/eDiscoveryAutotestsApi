package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlaceGroup;
import eDiscovery.data.dealService.DataGeneratorSearchPlace;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchPlaceGroup.AddSearchPlaceGroupRequestModel;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;
import eDiscovery.models.deal.searchPlaceGroup.UpdateSearchPlaceGroupRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static eDiscovery.helpers.DataChecker.dateTimeISOPattern;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - SearchPlaceGroup")
public class SearchPlaceGroupCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Создание группы мест поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание группы мест поиска")
    @Description("Тест проверяет возможность создания группы мест поиска")
    public void testAddSearchPlaceGroup(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBody = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                .description(getRandomName())
                .build();

        CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBody).as(CommonSearchPlaceGroupResponseModel.class);

        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.searchPlaces.get(0)).usingRecursiveComparison().ignoringFields("createdUtc").isEqualTo(searchPlaceBody);
        assertThat(responseBody.description).isEqualTo(requestBody.description);
        assertThat(responseBody.deletedUtc).isNull();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Изменение группы мест поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение группы мест поиска")
    @Description("Тест проверяет возможность изменения группы мест поиска")
    public void testUpdateSearchPlaceGroup(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel searchPlaceBody1 = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();
        CommonSearchPlaceResponseModel searchPlaceBody2 = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody1.id))
                .description(getRandomName())
                .build();

        CommonSearchPlaceGroupResponseModel responseBodyCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        UpdateSearchPlaceGroupRequestModel requestBodyUpdate = UpdateSearchPlaceGroupRequestModel.builder()
                .id(responseBodyCreation.id)
                .name(responseBodyCreation.name)
                .searchPlaces(
                        List.of(
                                searchPlaceBody1.id,
                                searchPlaceBody2.id
                        )
                )
                .description(requestBodyCreation.description)
                .build();

        CommonSearchPlaceGroupResponseModel responseBodyUpdate = ApiMethodsSearchPlaceGroup.updateSearchPlaceGroup(requestBodyUpdate).as(CommonSearchPlaceGroupResponseModel.class);


        assertThat(responseBodyUpdate.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBodyUpdate.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBodyUpdate.searchPlaces).usingRecursiveComparison().ignoringCollectionOrder().comparingOnlyFields("id").isEqualTo(requestBodyUpdate.searchPlaces);
        assertThat(responseBodyUpdate.description).isEqualTo(responseBodyCreation.description);
        assertThat(responseBodyUpdate.deletedUtc).isNull();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Удаление группы мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление группы мест поиска")
    @Description("Тест проверяет возможность удаления группы мест поиска")
    public void testDeleteSearchPlaceGroup(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                .description(getRandomName())
                .build();

        CommonSearchPlaceGroupResponseModel responseBodyCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchPlaceGroup.deleteSearchPlaceGroup(responseBodyCreation.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupById(responseBodyCreation.id).as(CommonSearchPlaceGroupResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.description).isEqualTo(responseBodyCreation.description);
        assertThat(responseBody.deletedUtc).matches(dateTimeISOPattern());

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Получение списка групп мест поиска")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка групп мест поиска")
    @Description("Тест проверяет возможность получения списка групп мест поиска")
    public void testGetSearchPlaceGroupList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                .description(getRandomName())
                .build();

        ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupList().jsonPath().getList("", CommonSearchPlaceGroupResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        assertThat(responseBody.get(0).id).isNotEmpty();
        assertThat(responseBody.get(0).name).isNotEmpty();
        assertThat(responseBody.get(0).description).isNotEmpty();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Получение списка групп мест поиска")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка групп мест поиска по протоколу odata")
    @Description("Тест проверяет возможность получения списка групп мест поиска по протоколу odata")
    public void testGetSearchPlaceGroupListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                .description(getRandomName())
                .build();

        ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        List<CommonSearchPlaceGroupResponseModel> responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupListOData().jsonPath().getList("value", CommonSearchPlaceGroupResponseModel.class);

        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        assertThat(responseBody.get(0).id).isNotEmpty();
        assertThat(responseBody.get(0).name).isNotEmpty();
        assertThat(responseBody.get(0).description).isNotEmpty();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Получение группы мест поиска по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("УПолучение группы мест поиска по id")
    @Description("Получение группы мест поиска по id")
    public void testGetSearchPlaceGroupById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                .description(getRandomName())
                .build();

        CommonSearchPlaceGroupResponseModel responseBodyCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupById(responseBodyCreation.id).as(CommonSearchPlaceGroupResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.description).isEqualTo(responseBodyCreation.description);
        assertThat(responseBody.deletedUtc).isNull();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Группа мест поиска")
    @Story("Получение группы мест поиска по id")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("УПолучение группы мест поиска по id")
    @Description("Получение группы мест поиска по id в path param")
    public void testGetSearchPlaceGroupByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchPlaceResponseModel searchPlaceBody = DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal();

        AddSearchPlaceGroupRequestModel requestBodyCreation = AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceBody.id))
                .description(getRandomName())
                .build();

        CommonSearchPlaceGroupResponseModel responseBodyCreation = ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(requestBodyCreation).as(CommonSearchPlaceGroupResponseModel.class);

        CommonSearchPlaceGroupResponseModel responseBody = ApiMethodsSearchPlaceGroup.getSearchPlaceGroupByIdPath(responseBodyCreation.id).as(CommonSearchPlaceGroupResponseModel.class);

        assertThat(responseBody.id).isEqualTo(responseBodyCreation.id);
        assertThat(responseBody.name).isEqualTo(responseBodyCreation.name);
        assertThat(responseBody.description).isEqualTo(responseBodyCreation.description);
        assertThat(responseBody.deletedUtc).isNull();

    }

}
