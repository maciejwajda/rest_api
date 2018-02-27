package api.comments;

import lombok.Data;

@Data
class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
