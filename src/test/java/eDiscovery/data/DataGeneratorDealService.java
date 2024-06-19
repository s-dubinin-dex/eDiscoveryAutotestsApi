package eDiscovery.data;

import com.github.javafaker.Faker;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.helpers.enums.SearchPlaceCategoryType;
import eDiscovery.helpers.enums.SearchPlaceType;
import eDiscovery.helpers.enums.SearchQueryType;

public class DataGeneratorDealService {

    public static Faker faker = new Faker();

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

    public static AddSearchQueryRequestModel getBasicSearchQueryModel(){
        return AddSearchQueryRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .type(SearchQueryType.Regex)
                .value("\\d{10}")
                .build();
    }
}
