package api.comments;

import api.EndPoints;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import java.util.function.Predicate;
import static api.Filter.applyFilter;
import static api.Serializer.deserializeAsList;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

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
                body("", hasSize(greaterThan(0))).
                and().body("email", hasItem("Jayne_Kuhic@sydney.com"));
    }

    @Test
    public void getCommentsAndFilter() {
        List<Comment> comments = deserializeAsList(whenGetCommentsEndpoint(), Comment.class);
        List<Comment> filteredComments = applyFilter(comments, idEquals1AndBodyContainsNon());
        assertTrue(filteredComments.stream().allMatch(idEquals1AndBodyContainsNon()));
    }

    private Predicate<Comment> idEquals1AndBodyContainsNon() {
        return comment -> comment.getId() == 1 && comment.getBody().contains("non");
    }

    private Response whenGetCommentsEndpoint() {
        return response;
    }

}
