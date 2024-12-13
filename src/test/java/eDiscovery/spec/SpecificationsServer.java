package eDiscovery.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationsServer {

    private static final ThreadLocal<RequestSpecification> requestSpecThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ResponseSpecification> responseSpecThreadLocal = new ThreadLocal<>();

    public static void installSpecification(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        installRequestSpecification(requestSpecification);
        installResponseSpecification(responseSpecification);
    }

    public static void installRequestSpecification(RequestSpecification requestSpecification) {
        requestSpecThreadLocal.set(requestSpecification);
    }

    public static void installResponseSpecification(ResponseSpecification responseSpecification) {
        responseSpecThreadLocal.set(responseSpecification);
    }

    public static void clearSpecifications(){
        requestSpecThreadLocal.remove();
        responseSpecThreadLocal.remove();
    }

    public static void setBaseUrl(String url){

    }

    public static RequestSpecification getUrlSpecification(String url) {
        return new RequestSpecBuilder().setBaseUri(url).build();
    }
}
