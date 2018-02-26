package api.comments;

import api.Comments;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class GetCommentsTest {

    @Test
    public void getCommentsAndVerifyResponse() {
        given().
                expect().
                statusCode(200).
                and().body("", hasSize(greaterThan(0))).
                and().body("email", hasItem("Jayne_Kuhic@sydney.com")).
                when().
                get(Comments.COMMENTS_ENDPOINT);
    }

    @Test
    public void getCommentsAndDeserialize() {
        List<Comments> comments = when().
                get(Comments.COMMENTS_ENDPOINT).
                then().
                extract().body().jsonPath().getList("", Comments.class);
    }

    @Test
    public void getCommentsAndFilter() {
        List<Comments> comments = when().
                get(Comments.COMMENTS_ENDPOINT).
                then().
                extract().body().jsonPath().getList("", Comments.class);
        List<Comments> filteredComments = comments.stream().
                filter(c -> c.getId() == 1).
                filter(c -> !c.getBody().contains("non")).
                collect(Collectors.toList());
    }


}
