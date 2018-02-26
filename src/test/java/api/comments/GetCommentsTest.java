package api.comments;

import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class GetCommentsTest {

    private static final String COMMENTS_ENDPOINT = "https://jsonplaceholder.typicode.com/comments";

    @Test
    public void getCommentsAndVerifyResponse(){
        given().
                expect().
                statusCode(200).
                and().body("", hasSize(greaterThan(0))).
                and().body("email", hasItem("Jayne_Kuhic@sydney.com")).
        when().
                get(COMMENTS_ENDPOINT);
    }

    @Test
    public void getCommentsAndDeserialize(){
        List<CommentsPojo> comments = when().
                get(COMMENTS_ENDPOINT).
        then().
                extract().body().jsonPath().getList("", CommentsPojo.class);
    }

    @Test
    public void getCommentsAndFilter(){
        List<CommentsPojo> comments = when().
                get(COMMENTS_ENDPOINT).
                then().
                extract().body().jsonPath().getList("", CommentsPojo.class);
        List<CommentsPojo> filteredComments = comments.stream().
                filter(c -> c.getId() == 1).
                filter(c -> !c.getBody().contains("non")).
                collect(Collectors.toList());
    }


}
