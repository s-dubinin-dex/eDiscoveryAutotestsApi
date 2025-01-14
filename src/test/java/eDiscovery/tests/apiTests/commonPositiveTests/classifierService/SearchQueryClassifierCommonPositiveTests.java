package eDiscovery.tests.apiTests.commonPositiveTests.classifierService;

import eDiscovery.TestBase;
import eDiscovery.apiMethods.classifier.ApiMethodsSearchQueryClassifier;
import eDiscovery.data.classifierService.DataGeneratorSearchQueryClassifier;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.classifier.searchQuery.AddSearchQueryClassifierRequestModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;
import eDiscovery.models.classifier.searchQuery.UpdateSearchQueryClassifierRequestModel;
import eDiscovery.spec.RequestSpecifications;
import eDiscovery.spec.ResponseSpecifications;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;
import static eDiscovery.helpers.DataChecker.*;
import static eDiscovery.helpers.DataChecker.dateTimeISOPattern;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Common positive tests: Classifier - SearchQuery")
public class SearchQueryClassifierCommonPositiveTests extends TestBase {

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Создание поискового запроса")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание поискового запроса")
    @Description("Тест проверяет возможность создания поискового запроса")
    public void testAddSearchQueryClassifier(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        AddSearchQueryClassifierRequestModel requestBody = DataGeneratorSearchQueryClassifier.getSearchQueryModelWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody);

        CommonSearchQueryClassifierResponseModel responseBody = response.as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.name).isEqualTo(requestBody.name);
        assertThat(responseBody.type).isEqualTo(SearchQueryType.Regex.name());
        assertThat(responseBody.value).isEqualTo(requestBody.value);
        assertThat(isValidUUID(responseBody.id)).isTrue();
//        assertThat(responseBody.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(responseBody.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Изменение поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение поискового запроса")
    @Description("Тест проверяет возможность изменения поискового запроса")
    public void testUpdateSearchQueryClassifier(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        CommonSearchQueryClassifierResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        UpdateSearchQueryClassifierRequestModel requestModelSearchQueryUpdate = UpdateSearchQueryClassifierRequestModel.builder()
                .name(getRandomName())
                .type(SearchQueryType.Text.name())
                .value("\\d{11}")
                .id(responseBodySearchQueryCreation.id)
                .build();

        Response responseSearchQueryUpdate = ApiMethodsSearchQueryClassifier.updateSearchQuery(requestModelSearchQueryUpdate);

        CommonSearchQueryClassifierResponseModel responseBodySearchQueryUpdate = responseSearchQueryUpdate.as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodySearchQueryUpdate.name).isEqualTo(requestModelSearchQueryUpdate.name);
        assertThat(responseBodySearchQueryUpdate.type).isEqualTo(SearchQueryType.Text.name());
        assertThat(responseBodySearchQueryUpdate.value).isEqualTo(requestModelSearchQueryUpdate.value);
        assertThat(isValidUUID(responseBodySearchQueryUpdate.id)).isTrue();
//        assertThat(responseBodySearchQueryUpdate.createdUtc).matches(dateTimeYYYYMMDDHHmmssPattern());
        assertThat(responseBodySearchQueryUpdate.deletedUtc).isNull();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Удаление поискового запроса")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление поискового запроса")
    @Description("Тест проверяет возможность удаления поискового запроса")
    public void testDeleteSearchQueryClassifier(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());

        CommonSearchQueryClassifierResponseModel responseBodySearchQueryCreation = DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200WithEmptyBody());
        ApiMethodsSearchQueryClassifier.deleteSearchQuery(responseBodySearchQueryCreation.id);

        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());
        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.getSearchQueryODataByIdPath(responseBodySearchQueryCreation.id).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBody.name).isEqualTo(responseBodySearchQueryCreation.name);
        assertThat(responseBody.type).isEqualTo(SearchQueryType.Regex.name());
        assertThat(responseBody.value).isEqualTo(responseBodySearchQueryCreation.value);
        assertThat(isValidUUID(responseBody.id)).isTrue();
        assertThat(responseBody.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBody.deletedUtc).matches(dateTimeISOPattern());
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Получение списка поисковых запросов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов")
    @Description("Тест проверяет возможность получения списка поисковых запросов")
    public void testGetSearchQueryClassifierList(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQueryClassifier.getSearchQueryList();

        List<CommonSearchQueryClassifierResponseModel> responseBody = response.jsonPath().getList("", CommonSearchQueryClassifierResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
        //TODO: Здесь и везде дальше проверять, что в ответе валидные данные с непустыми полями
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Получение списка поисковых запросов")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение списка поисковых запросов по протоколу oData")
    @Description("Тест проверяет возможность получения списка поисковых запросов по протоколу oData")
    public void testGetSearchQueryClassifierListOData(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        DataGeneratorSearchQueryClassifier.createSearchQueryWithOnlyRequiredParameters();
        Response response = ApiMethodsSearchQueryClassifier.getSearchQueryListOData(new HashMap<>());

        List<CommonSearchQueryClassifierResponseModel> responseBody = response.jsonPath().getList("value", CommonSearchQueryClassifierResponseModel.class);
        assertThat(responseBody).isNotEmpty();
        assertThat(responseBody.get(0)).isNotNull();
    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Получение поискового запроса по протоколу oData по id")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение поискового запроса по протоколу oData по id")
    @Description("Тест проверяет возможность получения поискового запроса по протоколу oData по id в скобках")
    public void testGetSearchQueryClassifierODataById(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        String searchQueryNameForFilter = "testSearchQueryODataByIdInRoundBrackets" + getRandomName();

        AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                .name(searchQueryNameForFilter)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        CommonSearchQueryClassifierResponseModel responseBodyODataById = ApiMethodsSearchQueryClassifier.getSearchQueryODataById(responseBody.id).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyODataById.id).isEqualTo(responseBody.id);
        assertThat(responseBodyODataById.name).isEqualTo(responseBody.name);
        assertThat(responseBodyODataById.type).isEqualTo(responseBody.type);
        assertThat(responseBodyODataById.value).isEqualTo(responseBody.value);
        assertThat(responseBodyODataById.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyODataById.deletedUtc).isNull();

    }

    @Test
    @Epic("Сервис Classifier")
    @Feature("Поисковые запросы")
    @Story("Получение поискового запроса по протоколу oData по id")
    @Tag("webui")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получение поискового запроса по протоколу oData по id")
    @Description("Тест проверяет возможность получения поискового запроса по протоколу oData по id в path param")
    public void testGetSearchQueryClassifierODataByIdInPath(){
        SpecificationsServer.installRequestSpecification(RequestSpecifications.basicRequestSpecificationWithAdminAuthorization());
        SpecificationsServer.installResponseSpecification(ResponseSpecifications.responseSpecOK200JSONBody());

        String searchQueryNameForFilter = "testSearchQueryODataByIdInRoundBrackets" + getRandomName();

        AddSearchQueryClassifierRequestModel requestBody = AddSearchQueryClassifierRequestModel.builder()
                .name(searchQueryNameForFilter)
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
        CommonSearchQueryClassifierResponseModel responseBody = ApiMethodsSearchQueryClassifier.addSearchQuery(requestBody).as(CommonSearchQueryClassifierResponseModel.class);

        CommonSearchQueryClassifierResponseModel responseBodyODataById = ApiMethodsSearchQueryClassifier.getSearchQueryODataByIdPath(responseBody.id).as(CommonSearchQueryClassifierResponseModel.class);

        assertThat(responseBodyODataById.id).isEqualTo(responseBody.id);
        assertThat(responseBodyODataById.name).isEqualTo(responseBody.name);
        assertThat(responseBodyODataById.type).isEqualTo(responseBody.type);
        assertThat(responseBodyODataById.value).isEqualTo(responseBody.value);
        assertThat(responseBodyODataById.createdUtc).matches(dateTimeISOPattern());
        assertThat(responseBodyODataById.deletedUtc).isNull();

    }
}
