package eDiscovery.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class ResponseSpecifications {
    public static ResponseSpecification responseSpecOK200JSONBody(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }

    public static ResponseSpecification responseSpecOK200WithEmptyBody(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectBody(equalTo(""))
                .expectStatusCode(HttpStatus.SC_OK)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }

    public static ResponseSpecification responseSpec400BadRequest(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }

    public static ResponseSpecification responseSpec403Forbidden(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_FORBIDDEN)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }

    public static ResponseSpecification responseSpec404NotFound(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }

    public static ResponseSpecification responseSpec409Conflict(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_CONFLICT)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }

    public static ResponseSpecification responseSpec415UnsupportedMediaType(){
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)
                .expectResponseTime(lessThanOrEqualTo(1L), SECONDS)
                .build();
    }
}
