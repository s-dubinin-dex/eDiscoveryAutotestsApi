package eDiscovery.tests.apiTests.commonPositiveTests.dealService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.deal.ApiMethodsFilterAttributeSchema;
import eDiscovery.models.deal.filterAttributeSchema.FilterAttributeSchemaResponseModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static eDiscovery.helpers.DataChecker.isValidUUID;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Deal - FilterAttributeSchema")
public class FilterAttributeSchemaCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Deal")
    @Feature("Схемы фильтров метаданных")
    @Story("Получение списка схем фильтров метаданных")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка схем фильтров метаданных")
    @Description("Тест проверяет возможность получения списка схем фильтров метаданных")
    public void testGetFilterAttributeSchemaList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<FilterAttributeSchemaResponseModel> responseBody = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaList().jsonPath().getList("", FilterAttributeSchemaResponseModel.class);

        assertThat(responseBody).isNotNull();

        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).title).isNotEmpty();
        assertThat(responseBody.get(0).type).isNotEmpty();
        assertThat(responseBody.get(0).value).isNotEmpty();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Схемы фильтров метаданных")
    @Story("Получение списка схем фильтров метаданных")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка схем фильтров метаданных по протоколу odata")
    @Description("Тест проверяет возможность получения списка схем фильтров метаданных по протоколу odata")
    public void testGetFilterAttributeSchemaListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        List<FilterAttributeSchemaResponseModel> responseBody = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaListOData().jsonPath().getList("value", FilterAttributeSchemaResponseModel.class);

        assertThat(responseBody).isNotNull();

        assertThat(responseBody.get(0)).isNotNull();

        assertThat(isValidUUID(responseBody.get(0).id)).isTrue();
        assertThat(responseBody.get(0).title).isNotEmpty();
        assertThat(responseBody.get(0).type).isNotEmpty();
        assertThat(responseBody.get(0).value).isNotEmpty();

    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Схемы фильтров метаданных")
    @Story("Получение схемы фильтра метаданных по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение схемы фильтра метаданных по id")
    @Description("Тест проверяет возможность получения схемы фильтра метаданных по id")
    public void testGetFilterAttributeSchemaById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        FilterAttributeSchemaResponseModel filterAttributeSchemaToCheck = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaListOData().jsonPath().getList("value", FilterAttributeSchemaResponseModel.class).get(0);

        FilterAttributeSchemaResponseModel responseBody = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaById(filterAttributeSchemaToCheck.id).as(FilterAttributeSchemaResponseModel.class);

        assertThat(responseBody).isNotNull();

        assertThat(responseBody.id).isEqualTo(filterAttributeSchemaToCheck.id);
        assertThat(responseBody.title).isEqualTo(filterAttributeSchemaToCheck.title);
        assertThat(responseBody.type).isEqualTo(filterAttributeSchemaToCheck.type);
        assertThat(responseBody.value).isEqualTo(filterAttributeSchemaToCheck.value);
    }

    @Test
    @Epic("Сервис Deal")
    @Feature("Схемы фильтров метаданных")
    @Story("Получение схемы фильтра метаданных по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение схемы фильтра метаданных по id в path param")
    @Description("Тест проверяет возможность получения схемы фильтра метаданных по id в path param")
    public void testGetFilterAttributeSchemaByIdPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        FilterAttributeSchemaResponseModel filterAttributeSchemaToCheck = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaListOData().jsonPath().getList("value", FilterAttributeSchemaResponseModel.class).get(0);

        FilterAttributeSchemaResponseModel responseBody = ApiMethodsFilterAttributeSchema.getFilterAttributeSchemaByIdPath(filterAttributeSchemaToCheck.id).as(FilterAttributeSchemaResponseModel.class);

        assertThat(responseBody).isNotNull();

        assertThat(responseBody.id).isEqualTo(filterAttributeSchemaToCheck.id);
        assertThat(responseBody.title).isEqualTo(filterAttributeSchemaToCheck.title);
        assertThat(responseBody.type).isEqualTo(filterAttributeSchemaToCheck.type);
        assertThat(responseBody.value).isEqualTo(filterAttributeSchemaToCheck.value);
    }
}
