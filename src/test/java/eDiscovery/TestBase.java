package eDiscovery;

import com.github.javafaker.Faker;
import eDiscovery.helpers.Authorization;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase extends UrlBase {

    public static Faker faker = new Faker();

    @BeforeAll
    static void beforeAll(){
        TOKEN = Authorization.getToken();
    }

    @AfterEach
    void tearDown() {
        RestAssured.responseSpecification = null;
        RestAssured.requestSpecification = null;
    }
}
