package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealDocumentProblemFinded extends UrlBase {

    private static final String DEAL_DOCUMENT_PROBLEM_FINDED_DEAL_DOCUMENT_PROBLEM_FINDED   = "/DealDocumentProblemFinded/DealDocumentProblemFinded";
    private static final String ODATA_DEAL_DOCUMENT_PROBLEM_FINDED                          = "/odata/DealDocumentProblemFinded";

    @Step("Получение списка обнаруженных документов")
    public static Response getDealDocumentProblemFinded(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_DOCUMENT_PROBLEM_FINDED_DEAL_DOCUMENT_PROBLEM_FINDED)
                .andReturn();
    }

    @Step("Получение списка обнаруженных документов по протоколу oData")
    public static Response getDealDocumentProblemFindedOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_DEAL_DOCUMENT_PROBLEM_FINDED)
                .andReturn();
    }
}
