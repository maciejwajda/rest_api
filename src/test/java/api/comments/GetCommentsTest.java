package api.comments;

import api.EndPoints;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import static api.Filter.applyFilter;
import static api.Serializer.deserializeAsList;
import static api.comments.CommentsFilters.ID_EQUALS_1_AND_BODY_CONTAINS_NON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
                body("", hasSize(greaterThan(0))).
                and().body("email", hasItem("Jayne_Kuhic@sydney.com"));
    }

    @Test
    public void getCommentsAndFilter() {
        List<Comment> comments = deserializeAsList(whenGetCommentsEndpoint(), Comment.class);
        List<Comment> filteredComments = applyFilter(comments, ID_EQUALS_1_AND_BODY_CONTAINS_NON);
        assertThat("Wrong filtering in comments", filteredComments, everyItem(
                allOf(
                        hasProperty("id", equalTo(1)),
                        hasProperty("body", containsString("non"))
                )
        ));
    }

    private Response whenGetCommentsEndpoint() {
        return response;
    }

}
