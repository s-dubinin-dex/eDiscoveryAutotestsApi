package eDiscovery.data;

import com.github.javafaker.Faker;
import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.apiMethods.deal.ApiMethodsSearchPlace;
import eDiscovery.apiMethods.deal.ApiMethodsSearchQuery;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.AddDealManipulationRequestModel;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.CommonSearchPlaceResponseModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.helpers.enums.SearchQueryType;
import eDiscovery.models.deal.searchQuery.CommonSearchQueryResponseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGeneratorDealService {

    public static Faker faker = new Faker();

    /*
    * Search Place
    * */

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModel(SearchPlaceCategoryType categoryType, SearchPlaceType type){
        return AddSearchPlaceRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .categoryType(categoryType)
                .type(type)
                .parameters(null)
                .excludes(null)
                .build();
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModelArmLocal(){
        return getBasicSearchPlaceModel(SearchPlaceCategoryType.ARM, SearchPlaceType.LOCAL);
    }

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModelFileShareSMB(){
        return getBasicSearchPlaceModel(SearchPlaceCategoryType.FileShare, SearchPlaceType.SMB);
    }

    public static CommonSearchPlaceResponseModel createBasicSearchPlace(){
        return ApiMethodsSearchPlace.addSearchPlace(getBasicSearchPlaceModelArmLocal()).as(CommonSearchPlaceResponseModel.class);
    }

    /*
    * Search Query
    * */

    public static AddSearchQueryRequestModel getBasicSearchQueryModel(){
        return AddSearchQueryRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .type(SearchQueryType.Regex)
                .value("\\d{10}")
                .build();
    }

    public static CommonSearchQueryResponseModel createBasicSearchQuery(){
        return ApiMethodsSearchQuery.addSearchQuery(getBasicSearchQueryModel()).as(CommonSearchQueryResponseModel.class);
    }

    /*
    * Deal manipulation
    * */

    public static AddDealManipulationRequestModel getBasicDealManipulationModel(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        return AddDealManipulationRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .searchPlaces(searchPlaceIDs)
                .schedule(null)
                .useSchedule(false)
                .excludes(new ArrayList<>())
                .searchQueueries(searchQueryIDs)
                .dealStatus(DealStatus.Waiting)
                .quarantine(false)
                .findedDocumentsCount(0)
                .build();
    }

    public static CommonDealManipulationResponseModel createBasicDealManipulation(){
        AddDealManipulationRequestModel requestDealCreationBody = getBasicDealManipulationModel(
                Collections.singletonList(createBasicSearchPlace().id),
                Collections.singletonList(createBasicSearchQuery().id)
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

}
