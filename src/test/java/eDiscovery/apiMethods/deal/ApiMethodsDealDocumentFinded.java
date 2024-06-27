package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealDocumentFinded extends UrlBase {

    private static final String DEAL_DOCUMENT_FINDED_DEAL_DOCUMENT_FINDED   = "/DealDocumentFinded/DealDocumentFinded";
    private static final String ODATA_DEAL_DOCUMENT_FINDED                  = "/odata/DealDocumentFinded";

    @Step("Получение списка обнаруженных документов")
    public static Response getDealDocumentFinded(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_DOCUMENT_FINDED_DEAL_DOCUMENT_FINDED)
                .andReturn();
    }

    @Step("Получение списка обнаруженных документов по протоколу oData")
    public static Response getDealDocumentFindedOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_DEAL_DOCUMENT_FINDED)
                .andReturn();
    }

}
