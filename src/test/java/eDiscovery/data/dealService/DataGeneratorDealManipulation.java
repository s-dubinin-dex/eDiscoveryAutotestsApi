package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.helpers.enums.DealStatus;
import eDiscovery.models.deal.dealManipulation.AddDealManipulationRequestModel;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGeneratorDealManipulation {

    /**
     * DealManipulation Models
     * */

    public static AddDealManipulationRequestModel getDealManipulationModelWithOnlyRequiredParameters(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        return AddDealManipulationRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .searchPlaces(searchPlaceIDs)
                .searchQueries(searchQueryIDs)
                .dealStatus(DealStatus.Waiting.name())
                .build();

    }

    public static AddDealManipulationRequestModel getBasicDealManipulationModel(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        return AddDealManipulationRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .searchPlaces(searchPlaceIDs)
                .excludes(new ArrayList<>())
                .searchQueries(searchQueryIDs)
                .dealStatus(DealStatus.Waiting.name())
                .quarantine(false)
                .build();
    }

    /**
     * DealManipulation Generators
     * */

    public static CommonDealManipulationResponseModel createDealManipulationWithOnlyRequiredParameters(List<String> searchPlaceIDs, List<String> searchQueryIDs){
        AddDealManipulationRequestModel requestDealCreationBody = getDealManipulationModelWithOnlyRequiredParameters(
                searchPlaceIDs,
                searchQueryIDs
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

    public static CommonDealManipulationResponseModel createDealManipulationWithOnlyRequiredParameters(){
        AddDealManipulationRequestModel requestDealCreationBody = getDealManipulationModelWithOnlyRequiredParameters(
                Collections.singletonList(DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters().id),
                Collections.singletonList(DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters().id)
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

    public static CommonDealManipulationResponseModel createBasicDealManipulation(){
        AddDealManipulationRequestModel requestDealCreationBody = getBasicDealManipulationModel(
                Collections.singletonList(DataGeneratorSearchPlace.createBasicSearchPlaceArmLocal().id),
                Collections.singletonList(DataGeneratorSearchQuery.createBasicSearchQuery().id)
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

}
