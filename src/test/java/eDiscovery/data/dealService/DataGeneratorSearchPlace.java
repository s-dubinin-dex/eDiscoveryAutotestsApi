package eDiscovery.data.dealService;

import com.github.javafaker.Faker;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.helpers.MaxAllowedFieldLengths;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;

import java.util.ArrayList;
import java.util.List;

public class DataGeneratorSearchPlace {

    public static Faker faker = new Faker();

    /**
     * SearchPlace Models
     * */

    public static AddSearchPlaceRequestModel getSearchPlaceModelWithOnlyRequiredParameters(){
        return getSearchPlaceModelWithOnlyRequiredParameters(
                DataGeneratorCommon.getRandomName()
        );
    }

    public static AddSearchPlaceRequestModel getSearchPlaceModelWithOnlyRequiredParameters(String name){
        return AddSearchPlaceRequestModel.builder()
                .name(name)
                .categoryType(SearchPlaceCategoryType.FileShare.name())
                .type(SearchPlaceType.SMB.name())
                .build();
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModel(String categoryType, String type){
        return AddSearchPlaceRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .categoryType(categoryType)
                .type(type)
                .parameters(null)
                .excludes(null)
                .build();
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModelArmLocal(){
        return getBasicSearchPlaceModel(
                SearchPlaceCategoryType.ARM.name(),
                SearchPlaceType.LOCAL.name()
        );
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModelFileShareSMB(){
        return getBasicSearchPlaceModel(
                SearchPlaceCategoryType.FileShare.name(),
                SearchPlaceType.SMB.name()
        );
    }

    /**
     * SearchPlace Generators
     * */

    public static CommonSearchPlaceResponseModel createSearchPlaceWithOnlyRequiredParameters(){
        return ApiMethodsSearchPlace.addSearchPlace(
                getSearchPlaceModelWithOnlyRequiredParameters()
        ).as(CommonSearchPlaceResponseModel.class);
    }

    public static CommonSearchPlaceResponseModel createSearchPlaceWithOnlyRequiredParameters(String name){
        return ApiMethodsSearchPlace.addSearchPlace(
                getSearchPlaceModelWithOnlyRequiredParameters(name)
        ).as(CommonSearchPlaceResponseModel.class);
    }

    public static CommonSearchPlaceResponseModel createBasicSearchPlaceArmLocal(){
        return ApiMethodsSearchPlace.addSearchPlace(
                getBasicSearchPlaceModelArmLocal()
        ).as(CommonSearchPlaceResponseModel.class);
    }

    public static CommonSearchPlaceResponseModel createBasicSearchPlaceFileShareSMB(){
        return ApiMethodsSearchPlace.addSearchPlace(
                getBasicSearchPlaceModelFileShareSMB()
        ).as(CommonSearchPlaceResponseModel.class);
    }

    /**
     * Valid SearchPlace attributes
     * */

    public static List<String> getValidSearchPlaceNames(){
        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.DEAL_SEARCH_PLACE_NAME));

        return result;
    }

    public static List<String> getValidSearchPlaceCategoryTypes(){
        return SearchPlaceCategoryType.getValidSearchPlaceCategoryTypes();
    }

    public static List<String> getValidSearchPlaceTypes(){
        return SearchPlaceType.getValidSearchPlaceTypes();
    }

    public static String[] getValidSearchPlaceExclusions(){
        return DataGeneratorCommon.getValidFolderExclusions();
    }

    public static List<List<String>>  getValidSearchPlaceExclusionsWithDifferentCount(){
        return DataGeneratorCommon.getValidFolderExclusionsWithDifferentCount();
    }

    public static List<String> getValidSearchPlaceURIInParameters() {
        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidURI());

        result.add(faker.lorem().characters(MaxAllowedFieldLengths.DEAL_SEARCH_PLACE_URI));

        return result;
    }

    public static List<String> getValidSearchPlaceUsernamesInParameters() {
        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidNames());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.DEAL_SEARCH_PLACE_USERNAME));

        return result;
    }

    public static List<String> getValidSearchPlacePasswordsInParameters(){
        List<String> result = new ArrayList<>(DataGeneratorCommon.getValidPasswords());

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.DEAL_SEARCH_PLACE_PASSWORD));

        return result;
    }

    /**
     * Invalid SearchPlace attributes
     * */

    public static List<String> getInvalidSearchPlaceNames(){
        List<String> result = new ArrayList<>();

        result.add(DataGeneratorCommon.getRandomName(MaxAllowedFieldLengths.DEAL_SEARCH_PLACE_NAME + 1));

        return result;
    }
}
