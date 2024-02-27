package juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestAPIUser {

    String endpoint = "https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1";

    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYmYwZDRlMDNlYjM4MzJlMWQyNGU4OTM3ODYzMDQ0NSIsInN1YiI6IjY1ZGQ4MjMzOWFlNjEzMDE4Njc0ZTkzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DI_9uAzz8Dq_cJwJTnXFXCHl2JP2Pz26tNwMyc5c8fE";

    @Test
    public void testStatusCode(){
        given()
                .header("Authorization", token)
                .get(endpoint)
                .then()
                .statusCode(200);
    }

    @Test
    public void testMovieList(){
        given()
                .header("Authorization", token)
                .get(endpoint)
                .then()
                .body("total_results", equalTo(3653));
    }

    @Test
    public void testMovieTitle(){
        given()
                .header("Authorization", token)
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("results.title[1]", equalTo("Anyone But You"))
                .log().all();
    }

    @Test
    public void testUnauthorized(){
        given()
                .get(endpoint)
                .then()
                .statusCode(401)
                .body("status_message", equalTo("Invalid API key: You must be granted a valid key."))
                .log().all();
    }



}
