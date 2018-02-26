package api;

import lombok.Data;

@Data
public class Comments {
    public static final String COMMENTS_ENDPOINT = "https://jsonplaceholder.typicode.com/comments";
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
