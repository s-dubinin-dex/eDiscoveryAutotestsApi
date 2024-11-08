package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsDiscoveredDocument extends UrlBase {

    private static final String DISCOVERED_DOCUMENT_DISCOVERED_DOCUMENT = "/DiscoveredDocument/DiscoveredDocument";
    private static final String ODATA_DISCOVERED_DOCUMENT               = "/odata/DiscoveredDocument";

    @Step("Получение списка обнаруженных документов")
    public static Response getDiscoveredDocument(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DISCOVERED_DOCUMENT_DISCOVERED_DOCUMENT)
                .andReturn();
    }

    @Step("Получение списка обнаруженных документов по протоколу oData")
    public static Response getDiscoveredDocumentOData(){

        return getDiscoveredDocumentOData(new HashMap<>());

    }

    @Step("Получение списка обнаруженных документов по протоколу oData")
    public static Response getDiscoveredDocumentOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_DISCOVERED_DOCUMENT)
                .andReturn();
    }

}
