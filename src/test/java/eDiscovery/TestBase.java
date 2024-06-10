package eDiscovery;

import eDiscovery.helpers.Authorization;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase extends UrlBase {

    @BeforeAll
    static void beforeAll(){
        TOKEN = Authorization.getToken();
    }

    @BeforeEach
    void setUp() {
        RestAssured.requestSpecification = null;
    }
}
