package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;

import java.util.List;

public class DataGeneratorSearchQuery {

    /*
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

    /*
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

    /*
     * Valid SearchQuery attributes
     * */

    public static List<String> getValidSearchQueryNames(){
        return DataGeneratorCommon.getValidNames();
    }

    public static List<String> getValidSearchQueryTypes(){
        return SearchQueryType.getValidSearchQueryTypes();
    }

    public static List<String> getValidSearchQueryValues(){
        //TODO: расширить валидными REGEX выражениями

        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());
        result.add(DataGeneratorCommon.getRandomName(2000)); // Строка из 2000 символов

        return result;
    }

    /*
     * Invalid SearchQuery attributes
     * */

    public static String[] getInvalidSearchQueryNames(){
        return DataGeneratorCommon.getInvalidNames();
    }

    public static String getSearchQueryNameWithExceedingLength(){
        return DataGeneratorCommon.getNameWithExceedingLength();
    }

    public static List<String> getInvalidSearchQueryTypes(){
        return SearchQueryType.getInvalidSearchQueryTypes();
    }
}
