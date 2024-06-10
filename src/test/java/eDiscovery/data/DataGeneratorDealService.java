package eDiscovery.data;

import com.github.javafaker.Faker;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;

public class DataGeneratorDealService {

    public static Faker faker = new Faker();

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModel(){
        return AddSearchPlaceRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .categoryType("ARM")
                .type("LOCAL")
                .parameters(null)
                .excludes(null)
                .build();
    }

    public static AddSearchQueryRequestModel getBasicSearchQueryModel(){
        return AddSearchQueryRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .type("Regex")
                .value("\\d{10}")
                .build();
    }
}
