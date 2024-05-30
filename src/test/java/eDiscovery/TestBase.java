package eDiscovery;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    @BeforeEach
    void setUp() {
        RestAssured.requestSpecification = null;
    }
}
