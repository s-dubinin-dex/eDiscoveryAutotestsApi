package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsClassifyDocument extends UrlBase {

    private static final String CLASSIFY_DOCUMENT_CLASSIFY_DOCUMENT = "/ClassifyDocument/ClassifyDocument";

    @Step("Отправить документ на категоризацию через внешний сервис")
    public static Response classifyDocument(Map<String, Object> formData){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        RequestSpecification spec = given();
        formData.forEach((key, value) -> {
            if (value instanceof String){
                spec.multiPart(key, (String) value);
            } else if (value instanceof java.io.File) {
                spec.multiPart(key, (java.io.File) value);
            }
        });

        return spec
                .when()
                .post(CLASSIFY_DOCUMENT_CLASSIFY_DOCUMENT)
                .then()
                .extract().response();
    }
}
