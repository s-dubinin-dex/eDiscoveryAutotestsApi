package eDiscovery.data;

import com.github.javafaker.Faker;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;

import java.util.ArrayList;

public class DataGeneratorDealService {

    public static Faker faker = new Faker();

    public static AddSearchPlaceRequestModel getBasicSearchPlaceModel(){
        return AddSearchPlaceRequestModel.builder()
                .name(faker.letterify("???????????????????"))
                .categoryType("ARM")
                .type("LOCAL")
                .parameters(null)
                .excludes(new ArrayList<String>())
                .build();
    }
}
