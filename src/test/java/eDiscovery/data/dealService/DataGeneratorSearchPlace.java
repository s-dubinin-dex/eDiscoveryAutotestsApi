package eDiscovery.data.dealService;

import com.github.javafaker.Faker;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;

import java.util.Arrays;
import java.util.List;

public class DataGeneratorSearchPlace {

    /*
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

    /*
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

    /*
     * Valid SearchPlace attributes
     * */

    public static List<String> getValidSearchPlaceNames(){
        return DataGeneratorCommon.getValidNames();
    }

    public static String[] getValidSearchPlaceCategoryTypes(){
        // TODO: исправить по примеру searchQuery
        return Arrays.stream(SearchPlaceCategoryType.values()).map(Enum::name).toArray(String[]::new);
    }

    public static String[] getValidSearchPlaceTypes(){
        // TODO: исправить по примеру searchQuery
        return Arrays.stream(SearchPlaceType.values()).map(Enum::name).toArray(String[]::new);
    }

    public static String[] getValidSearchPlaceExclusions(){
        return DataGeneratorCommon.getValidFolderExclusions();
    }

    public static List<List<String>>  getValidSearchPlaceExclusionsWithDifferentCount(){
        return DataGeneratorCommon.getValidFolderExclusionsWithDifferentCount();
    }

    public static String[] getValidSearchPlaceURIInParameters() {
        return DataGeneratorCommon.getValidURI();
    }

    public static List<String> getValidSearchPlaceUsernamesInParameters() {
        return DataGeneratorCommon.getValidNames();
    }

    public static String[] getValidSearchPlacePasswordsInParameters(){
        return DataGeneratorCommon.getValidPasswords();
    }

    /*
     * Invalid SearchPlace attributes
     * */

}
