package api;
import io.restassured.response.Response;

import java.util.List;

public class Serializer {


    /**
     * Deserialize JSON from response
     *
     * @param response to be deserialized
     * @param genericType The generic list type
     * @return List of deserialized objects of genericType type
     */
    public static <T> List<T> deserializeAsList(Response response, Class<T> genericType){
        return response.body().jsonPath().getList("", genericType);
    }
}
