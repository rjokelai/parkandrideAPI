package fi.hsl.parkandride.itest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.ResponseSpecification;

import fi.hsl.parkandride.core.domain.Facility;
import fi.hsl.parkandride.core.service.ValidationException;
import fi.hsl.parkandride.front.UrlSchema;

public class ErrorHandlingITest extends AbstractIntegrationTest {
    @Test
    public void notFound() {
        when()
            .get(UrlSchema.FACILITIES + "/42")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body(is(""))
        ;
    }

    @Test
    public void validationException() {
        given()
            .header("Content-Type", "application/json;charset=UTF-8")
            .body(new Facility())
        .when()
            .post(UrlSchema.FACILITIES)
        .then()
            .spec(assertResponse(HttpStatus.BAD_REQUEST, ValidationException.class))
        ;
    }

    @Test
    public void httpMessageNotReadableException() {
        given()
            .header("Content-Type", "application/json;charset=UTF-8")
            .body("{ \"name\": \"foo\", \"location\": \"invalid location\"  }")
        .when()
            .post(UrlSchema.FACILITIES)
        .then()
            .spec(assertResponse(HttpStatus.BAD_REQUEST, HttpMessageNotReadableException.class))
        ;
    }

    @Test
    public void httpRequestMethodNotSupportedException() {
        given()
            .header("Content-Type", "application/json;charset=UTF-8")
            .body(new Facility())
        .when()
            .put(UrlSchema.FACILITIES)
        .then()
            .spec(assertResponse(HttpStatus.BAD_REQUEST, HttpRequestMethodNotSupportedException.class))
        ;
    }

    // HttpMessageNotReadableException_jsonMappingException: unclear how to trigger
    // HttpMediaTypeException: unclear how to trigger
    // bindException: unclear how to trigger
    // exception: unclear how to trigger

    private static ResponseSpecification assertResponse(HttpStatus status, Class<?> exClass) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status.value())
                .expectBody("status", is(status.value()))
                .expectBody("exception", is(exClass.getCanonicalName()))
                .build();
    }
}
