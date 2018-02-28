package api.comments;

import java.util.function.Predicate;

public class CommentsFilters {

    public static final Predicate<Comment> ID_EQUALS_1_AND_BODY_CONTAINS_NON = comment ->
            comment.getId() == 1 && comment.getBody().contains("non");
}
