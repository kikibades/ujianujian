package juaracoding;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetPopular {


    String endpoint = "https://api.themoviedb.org/3/movie/popular";

    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYmYwZDRlMDNlYjM4MzJlMWQyNGU4OTM3ODYzMDQ0NSIsInN1YiI6IjY1ZGQ4MjMzOWFlNjEzMDE4Njc0ZTkzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DI_9uAzz8Dq_cJwJTnXFXCHl2JP2Pz26tNwMyc5c8fE";

    @Test
    public void testStatusCode() {
        given()
                .header("Authorization", token)
                .get(endpoint)
                .then()
                .statusCode(200);
    }

    @Test
    public void testMovieList() {
        given()
                .header("Authorization", token)
                .get(endpoint)
                .then()
                .body("total_results", equalTo(853845))
                .body("results.popularity[2]", equalTo(2038.149));
    }

    @Test
    public void testMovieTitle() {
        given()
                .header("Authorization", token)
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("results.title[2]", equalTo("No Way Up"))
                .log().all();
    }

    @Test
    public void testUnauthorized() {
        given()
                .get(endpoint)
                .then()
                .statusCode(401)
                .body("status_message", equalTo("Invalid API key: You must be granted a valid key."))
                .log().all();


    }
}
