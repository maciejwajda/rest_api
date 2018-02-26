package api.comments;

import lombok.Data;

@Data
class CommentsPojo {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
