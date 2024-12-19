package eDiscovery.data.dealService;

import eDiscovery.apiMethods.deal.ApiMethodsSearchPlaceGroup;
import eDiscovery.models.deal.searchPlaceGroup.AddSearchPlaceGroupRequestModel;
import eDiscovery.models.deal.searchPlaceGroup.CommonSearchPlaceGroupResponseModel;

import java.util.Collections;

import static eDiscovery.data.DataGeneratorCommon.getRandomName;

public class DataGeneratorSearchPlaceGroup {

    public static AddSearchPlaceGroupRequestModel getSearchPlaceGroupWithOnlyRequiredParameters(){
        return AddSearchPlaceGroupRequestModel.builder()
                .name(getRandomName())
                .description(getRandomName())
                .searchPlaces(Collections.singletonList(DataGeneratorSearchPlace.createSearchPlaceWithOnlyRequiredParameters().id))
                .build();
    }

    public static CommonSearchPlaceGroupResponseModel createSearchPlaceGroupWithOnlyRequiredParameters(){
        return ApiMethodsSearchPlaceGroup.addSearchPlaceGroup(getSearchPlaceGroupWithOnlyRequiredParameters()).as(CommonSearchPlaceGroupResponseModel.class);
    }
}
