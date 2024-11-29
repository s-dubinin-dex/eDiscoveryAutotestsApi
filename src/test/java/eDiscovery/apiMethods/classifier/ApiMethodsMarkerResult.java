package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsMarkerResult extends UrlBase {

    private static final String MARKER_RESULT_MARKER_RESULT = "/MarkerResult/MarkerResult";
    private static final String ODATA_MARKER_RESULT         = "/odata/MarkerResult";

    public static Response getMarkerResultList(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(MARKER_RESULT_MARKER_RESULT)
                .then()
                .extract().response();

    }

    public static Response getMarkerResultListOData(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_MARKER_RESULT)
                .then()
                .extract().response();

    }

}
