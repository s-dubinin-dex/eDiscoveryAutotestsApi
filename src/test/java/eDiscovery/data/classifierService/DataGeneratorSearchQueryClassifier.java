package eDiscovery.data.classifierService;

import eDiscovery.apiMethods.classifier.ApiMethodsSearchQueryClassifier;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.helpers.MaxAllowedFieldLengths;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.classifier.searchQuery.AddSearchQueryClassifierRequestModel;
import eDiscovery.models.classifier.searchQuery.CommonSearchQueryClassifierResponseModel;

import java.util.ArrayList;
import java.util.List;

public class DataGeneratorSearchQueryClassifier {

    /**
     * SearchQuery Models
     * */

    public static AddSearchQueryClassifierRequestModel getSearchQueryModelWithOnlyRequiredParameters(){
        return AddSearchQueryClassifierRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .type(SearchQueryType.Regex.name())
                .value("\\d{10}")
                .build();
    }

    public static AddSearchQueryClassifierRequestModel getBasicSearchQueryModel(){
        return getSearchQueryModelWithOnlyRequiredParameters();
    }

    /**
     * SearchQuery Generators
     * */

    public static CommonSearchQueryClassifierResponseModel createSearchQueryWithOnlyRequiredParameters(){
        return ApiMethodsSearchQueryClassifier.addSearchQuery(
                getSearchQueryModelWithOnlyRequiredParameters()
        ).as(CommonSearchQueryClassifierResponseModel.class);
    }

    public static CommonSearchQueryClassifierResponseModel createBasicSearchQuery(){
        return ApiMethodsSearchQueryClassifier.addSearchQuery(
                getBasicSearchQueryModel()
        ).as(CommonSearchQueryClassifierResponseModel.class);
    }

    /**
     * Valid SearchQuery attributes
     * */

    public static List<String> getValidSearchQueryNames(){

        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.CLASSIFIER_SEARCH_QUERY_NAME));

        return result;
    }

    public static List<String> getValidSearchQueryTypes(){
        return SearchQueryType.getValidSearchQueryTypes();
    }

    public static List<String> getValidSearchQueryValues(){

        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.CLASSIFIER_SEARCH_QUERY_VALUE)); // Строка из 2000 символов

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

    public static List<String> getInvalidSearchQueryTypes(){
        return SearchQueryType.getInvalidSearchQueryTypes();
    }

    public static String getSearchQueryValueWithExceedingLength(){
        return DataGeneratorCommon.getRandomName(2001);
    }
}
