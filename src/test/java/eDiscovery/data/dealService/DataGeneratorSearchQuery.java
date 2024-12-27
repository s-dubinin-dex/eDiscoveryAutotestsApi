package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.helpers.MaxAllowedFieldLengths;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;

import java.util.ArrayList;
import java.util.List;

public class DataGeneratorSearchQuery {

    /**
     * SearchQuery Models
     * */

    public static AddSearchQueryRequestModel getSearchQueryModelWithOnlyRequiredParameters(){
        return AddSearchQueryRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
    }

    public static AddSearchQueryRequestModel getBasicSearchQueryModel(){
        return getSearchQueryModelWithOnlyRequiredParameters();
    }

    /**
     * SearchQuery Generators
     * */

    public static CommonSearchQueryResponseModel createSearchQueryWithOnlyRequiredParameters(){
        return ApiMethodsSearchQuery.addSearchQuery(
                getSearchQueryModelWithOnlyRequiredParameters()
        ).as(CommonSearchQueryResponseModel.class);
    }

    public static CommonSearchQueryResponseModel createBasicSearchQuery(){
        return ApiMethodsSearchQuery.addSearchQuery(
                getBasicSearchQueryModel()
        ).as(CommonSearchQueryResponseModel.class);
    }

    /**
     * Valid SearchQuery attributes
     * */

    public static List<String> getValidSearchQueryNames(){

        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.DEAL_SEARCH_QUERY_NAME));

        return result;
    }

    public static List<SearchQueryType> getValidSearchQueryTypes(){
        return SearchQueryType.getValidSearchQueryTypes();
    }

    public static List<String> getValidSearchQueryValues(){
        //TODO: расширить валидными REGEX выражениями

        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.DEAL_SEARCH_QUERY_VALUE)); // Строка из 2000 символов

        return result;
    }

    /**
     * Invalid SearchQuery attributes
     * */

    public static String[] getEmptySearchQueryNames(){
        return DataGeneratorCommon.getEmptyNames();
    }

    public static String getSearchQueryNameWithExceedingLength(){
        return DataGeneratorCommon.getRandomName(257);
    }

    public static List<SearchQueryType> getInvalidSearchQueryTypes(){
        return SearchQueryType.getInvalidSearchQueryTypes();
    }

    public static String getSearchQueryValueWithExceedingLength(){
        return DataGeneratorCommon.getRandomName(2001);
    }

    //TODO: Для негативных тестов с невалидными (недопустимыми) данными, например, цифр в полях запроса использоватьт JSONObject requestBody = new JSONObject();
    // И добавить перегрузку в методах апи на JsonObject
    // Пример:
    /*
    *
    * JSONObject requestBody = new JSONObject();
    * requestBody.put("FirstName", someRandomString);
    * requestBody.put("LastName", someRandomString);
    *
    * RequestSpecification request = RestAssured.given();
    * request.header("Content-Type", "application/json");
    * request.body(requestBody.toJSONString());
    * Response response = request.post("https://webhook.site/a18a23cb-e9a0-4f03-a7fa-cdfcfa76ca98");
    * int statusCode = response.getStatusCode();
    * Assert.assertEquals(statusCode, 200);
    * System.out.println("The status code recieved: " + statusCode);
    *
    *
    * */
}
