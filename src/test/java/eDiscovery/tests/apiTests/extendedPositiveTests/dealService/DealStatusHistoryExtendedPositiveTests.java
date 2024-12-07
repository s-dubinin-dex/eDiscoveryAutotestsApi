package eDiscovery.tests.apiTests.extendedPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsDealStatusHistory;
import eDiscovery.data.dealService.DataGeneratorDealManipulation;
import eDiscovery.helpers.OdataParametersBuilder;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealStatusHistory.DealStatusHistoryModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static eDiscovery.helpers.DataChecker.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Extended positive tests: Deal - DealStatusHistory")
public class DealStatusHistoryExtendedPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Tag("webui")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка изменений статусов дела по протоколу odata для карточки дела")
    @Description("Тест проверяет возможность получения списка изменений статусов дела по протоколу odata для карточки дела")
    public void testGetDealStatusHistoryListForDealCard(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s", responseBodyDealCreation.id))
                .withOrderBy("changedStatusUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryListOData(parameters).jsonPath().getList("value", DealStatusHistoryModel.class);

        assertThat(responseBody).hasSize(1);
        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).dealId).isEqualTo(responseBodyDealCreation.id);
        assertThat(responseBody.get(0).dealStatus).isEqualTo(DealStatus.Running.name());
        assertThat(isValidUUID(responseBody.get(0).changedStatusUserId)).isTrue();
        assertThat(responseBody.get(0).changedStatusUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.get(0).changedStatusUserName).isEqualTo("Администратор");
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка изменений статусов дела по протоколу odata для карточки дела с фильтром по периоду")
    @Description("Тест проверяет возможность получения списка изменений статусов делапо протоколу odata для карточки дела с фильтром по периоду")
    public void testGetDealStatusHistoryListForDealCardWithFilterChangedStatusUtc(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("changedStatusUtc ge %s and changedStatusUtc le %s and dealId eq %s", LocalDate.now(), LocalDate.now().plusDays(1), responseBodyDealCreation.id))
                .withOrderBy("changedStatusUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryListOData(parameters).jsonPath().getList("value", DealStatusHistoryModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody).hasSize(1);
        assertThat(responseBody.get(0)).isNotNull();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка изменений статусов дела по протоколу odata для карточки дела с фильтром по инициатору")
    @Description("Тест проверяет возможность получения списка изменений статусов делапо протоколу odata для карточки дела с фильтром по инициатору")
    public void testGetDealStatusHistoryListForDealCardWithFilterChangedStatusUserId (){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("changedStatusUserId eq 8dd105b2-7e41-40c4-8700-022403dfcdc6 and dealId eq %s", responseBodyDealCreation.id))
                .withOrderBy("changedStatusUtc desc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryListOData(parameters).jsonPath().getList("value", DealStatusHistoryModel.class);

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("История статусов дела")
    @Story("Получение списка изменений статусов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка изменений статусов дела по протоколу odata для карточки дела с сортировкой по Статусу дела")
    @Description("Тест проверяет возможность получения списка изменений статусов делапо протоколу odata для карточки дела с сортировкой по Статусу дела")
    public void testGetDealStatusHistoryListForDealCardWithSortingDealStatus (){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonDealManipulationResponseModel responseBodyDealCreation = DataGeneratorDealManipulation.createDealManipulationWithOnlyRequiredParameters();
        ApiMethodsDealManipulation.startDeal(responseBodyDealCreation.id);

        Map<String, String> parameters = OdataParametersBuilder.builder()
                .withFilter(String.format("dealId eq %s", responseBodyDealCreation.id))
                .withOrderBy("dealStatus asc")
                .withCount(true)
                .withTop(10)
                .withSkip(0)
                .build();

        List<DealStatusHistoryModel> responseBody = ApiMethodsDealStatusHistory.getDealStatusHistoryListOData(parameters).jsonPath().getList("value", DealStatusHistoryModel.class);

        assertThat(responseBody).isNotNull();
        assertThat(responseBody).hasSize(1);
        assertThat(responseBody.get(0)).isNotNull();

    }
}
