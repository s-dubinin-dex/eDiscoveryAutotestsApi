package eDiscovery;

import com.github.javafaker.Faker;
import eDiscovery.spec.SpecificationsServer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.config.LogConfig.logConfig;

public class TestBase {

    public static Faker faker = new Faker();

    @BeforeAll
    static void beforeAll(){
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssured.config()
                .logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }

    @BeforeEach
    void setUp() {
        SpecificationsServer.clearSpecifications();
    }

    @AfterEach
    void tearDown() {
        SpecificationsServer.clearSpecifications();
    }
}
