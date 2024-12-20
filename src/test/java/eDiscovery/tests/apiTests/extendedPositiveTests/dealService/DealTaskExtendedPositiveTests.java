package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsDealTask;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.DealTaskStatus;
import eDiscovery.helpers.enums.DealTaskType;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealTask.DealTaskModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.dateTimeISOPattern;
import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - DealTask")
public class DealTaskExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка задач для карточки дела")
    @Description("Тест проверяет возможность получения списка задач для карточки дела")
    public void testGetDealTaskListForDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

        assertThat(responseBody).isNotEmpty();

        DealTaskModel currentTaskBody = responseBody.get(0);

        assertThat(currentTaskBody).isNotNull();

        assertThat(isValidUUID(currentTaskBody.id)).isTrue();
        assertThat(currentTaskBody.dealPriority).isEqualTo(responseBodyDealCreation.dealPriority);
        assertThat(currentTaskBody.taskType).isEqualTo(DealTaskType.Search.name());
        assertThat(currentTaskBody.dealId).isEqualTo(responseBodyDealCreation.id);
        assertThat(currentTaskBody.dealName).isEqualTo(responseBodyDealCreation.name);
        assertThat(currentTaskBody.taskStartedUtc).isNull();
        assertThat(currentTaskBody.taskFinishedUtc).isNull();
        assertThat(currentTaskBody.status).isEqualTo(DealTaskStatus.Waiting.name());
        assertThat(currentTaskBody.errors).isNull();
        assertThat(currentTaskBody.exportFileName).isNull();
        assertThat(currentTaskBody.exportFileUrl).isNull();
        assertThat(isValidUUID(currentTaskBody.creatorUserId)).isTrue();
        assertThat(currentTaskBody.creatorUserName).isEqualTo("Администратор");
        assertThat(currentTaskBody.editorUserId).isNull();
        assertThat(currentTaskBody.editorUserName).isNull();
        assertThat(currentTaskBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(currentTaskBody.updatedUtc).matches(dateTimeISOPattern());
        assertThat(currentTaskBody.searchPlace.id).isEqualTo(responseBodyDealCreation.searchPlaces.get(0).id);
        assertThat(currentTaskBody.searchPlace.name).isEqualTo(responseBodyDealCreation.searchPlaces.get(0).name);
        assertThat(currentTaskBody.progressInfo).isNull();
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с фильтром по периоду")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с фильтром по периоду")
    public void testGetDealTaskListForDealCardWithFilterCreatedUtc(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("createdUtc ge 2024-12-02T22:00:00.000Z and createdUtc le 2024-12-06T21:59:59.999Z and dealId eq %s", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

    @ParameterizedTest
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с фильтром по типу задачи")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с фильтром по типу задачи")
    @MethodSource("eDiscovery.helpers.enums.DealTaskType#getValidDealTaskTypes")
    public void testGetDealTaskListForDealCardWithFilterTaskType(DealTaskType taskType){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s and taskType eq '%s'", responseBodyDealCreation.id, taskType.name()))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с фильтром по месту поиска")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с фильтром по месту поиска")
    public void testGetDealTaskListForDealCardWithFilterSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s and SearchPlace/id eq 14de1c7b-884f-4739-be13-53b51c0e3283", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с фильтром по инициатору")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с фильтром по инициатору")
    public void testGetDealTaskListForDealCardWithFilterCreatorUserId(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("creatorUserId eq 8dd105b2-7e41-40c4-8700-022403dfcdc6 and dealId eq %s", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("createdUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с сортировкой по типу задачи")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с сортировкой по типу задачи")
    public void testGetDealTaskListForDealCardWithSortingTaskType(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("taskType asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с сортировкой по месту поиска")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с сортировкой по месту поиска")
    public void testGetDealTaskListForDealCardWithSortingSearchPlace(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("searchPlace/name asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Задачи")
    @Story("Получение списка задач")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка задач для карточки дела с сортировкой по статусу")
    @Description("Тест проверяет возможность получения списка задач для карточки дела с сортировкой по статусу")
    public void testGetDealTaskListForDealCardWithSortingStatus(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s", responseBodyDealCreation.id))
                .withExpand("SearchPlace,progressInfo")
                .withOrderBy("status asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealTaskModel> responseBody = ApiMethodsDealTask.getDealTaskListOData(parameters).jsonPath().getList("value", DealTaskModel.class);

    }

}
