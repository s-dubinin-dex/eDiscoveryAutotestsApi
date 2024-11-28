package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsDealManipulation;
import eDiscovery.data.DataGeneratorCommon;
import eDiscovery.models.deal.dealManipulation.AddDealManipulationRequestModel;
import eDiscovery.models.deal.dealManipulation.ClassifierDealData;
import eDiscovery.models.deal.dealManipulation.CommonDealManipulationResponseModel;
import eDiscovery.models.deal.dealManipulation.DealSearchQueryModel;

import java.util.Collections;

public class DataGeneratorDealManipulation {

    /**
     * DealManipulation Models
     * */

    public static AddDealManipulationRequestModel getDealManipulationModelWithOnlyRequiredParameters(String searchPlaceID, String searchQueryID){
        return AddDealManipulationRequestModel.builder()
                .name(DataGeneratorCommon.getRandomName())
                .searchPlaces(Collections.singletonList(searchPlaceID))
                .dealSearchQueries(Collections.singletonList(new DealSearchQueryModel(searchQueryID, true)))
                .classifierDealData(new ClassifierDealData())
                .build();
    }

    public static AddDealManipulationRequestModel getDealManipulationModelWithOnlyRequiredParameters(){
        return getDealManipulationModelWithOnlyRequiredParameters(
                DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters().id,
                DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters().id
        );
    }

    /**
     * DealManipulation Generators
     * */

    public static CommonDealManipulationResponseModel createDealManipulationWithOnlyRequiredParameters(String searchPlaceID, String searchQueryID){
        AddDealManipulationRequestModel requestDealCreationBody = getDealManipulationModelWithOnlyRequiredParameters(
                searchPlaceID,
                searchQueryID
        );

        return ApiMethodsDealManipulation.addDeal(requestDealCreationBody).as(CommonDealManipulationResponseModel.class);
    }

    public static CommonDealManipulationResponseModel createDealManipulationWithOnlyRequiredParameters(){
        return createDealManipulationWithOnlyRequiredParameters(
                DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters().id,
                DataGeneratorSearchQuery.createSearchQueryWithOnlyRequiredParameters().id
        );
    }
}
