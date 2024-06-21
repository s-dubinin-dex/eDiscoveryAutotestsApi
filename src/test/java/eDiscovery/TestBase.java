package eDiscovery;

import com.github.javafaker.Faker;
import eDiscovery.helpers.Authorization;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase extends UrlBase {

    public static Faker faker = new Faker();

    @BeforeAll
    static void beforeAll(){
        TOKEN = Authorization.getToken();
    }

    @BeforeEach
    void setUp() {
        RestAssured.responseSpecification = null;
        RestAssured.requestSpecification = null;
    }

    @AfterEach
    void tearDown() {
        RestAssured.responseSpecification = null;
        RestAssured.requestSpecification = null;
    }
}
