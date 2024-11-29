package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsDealTask;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.enums.DealTaskStatus;
import eDiscovery.helpers.enums.DealTaskType;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealTask.DealTaskModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка задач для карточки дела")
    @Description("Тест проверяет возможность получения списка задач для карточки дела")
    public void testGetDealTaskListForDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("$filter", String.format("dealId eq %s", responseBodyDealCreation.id));
        parameters.put("$expand", "SearchPlace,progressInfo");
        parameters.put("$orderby", "createdUtc desc");
        parameters.put("$count", "true");
        parameters.put("$top", "10");
        parameters.put("$skip", "0");

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
}
