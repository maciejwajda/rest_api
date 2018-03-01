package api.comments;

import java.util.function.Predicate;

public class CommentsFilters {

    public static final Predicate<Comment> POST_ID_EQUALS_1_AND_BODY_CONTAINS_NON = comment ->
            comment.getPostId() == 1 && comment.getBody().contains("non");
}
