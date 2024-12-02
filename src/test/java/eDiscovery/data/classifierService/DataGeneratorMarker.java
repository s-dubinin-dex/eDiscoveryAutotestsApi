package eDiscovery.data.classifierService;

import eDiscovery.apiMethods.classifier.ApiMethodsMarker;
import eDiscovery.models.classifier.marker.CommonMarkerResponseModel;

public class DataGeneratorMarker {

    public static CommonMarkerResponseModel getFirstMarker(){
        return ApiMethodsMarker.getMarkerListOData().jsonPath().getList("value", CommonMarkerResponseModel.class).get(0);
    }

}
