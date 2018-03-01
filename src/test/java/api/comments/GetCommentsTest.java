package api.comments;

import api.EndPoints;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static api.Filter.applyFilter;
import static api.Serializer.deserializeAsList;
import static api.comments.CommentsFilters.POST_ID_EQUALS_1_AND_BODY_CONTAINS_NON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetCommentsTest {

    private static Response response;

    @BeforeClass
    public static void getCommentsEndpoint() {
        response = given().expect().statusCode(200).
                when().get(EndPoints.COMMENTS);
    }

    @Test
    public void getCommentsAndVerifyResponse() {
        whenGetCommentsEndpoint().
                then().
                assertThat().
                body("", hasSize(greaterThan(0))).
                and().body("email", hasItem("Jayne_Kuhic@sydney.com"));
    }

    @Test
    public void getCommentsAndVerifyFiltering() {
        List<Comment> comments = deserializeAsList(whenGetCommentsEndpoint(), Comment.class);
        List<Comment> filteredComments = applyFilter(comments, POST_ID_EQUALS_1_AND_BODY_CONTAINS_NON);
        checkFiltering(filteredComments);
    }

    private void checkFiltering(List<Comment> filteredComments) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(filteredComments).as("Comment containing postId different then 1").
                allMatch( c -> c.getPostId() == 1);
        softly.assertThat(filteredComments).as("Comments not containing 'non' in body" ).
                allMatch( c -> c.getBody().contains("non"));
        softly.assertAll();
    }

    private Response whenGetCommentsEndpoint() {
        return response;
    }

}
