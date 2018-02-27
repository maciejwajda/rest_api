package api;
import io.restassured.response.Response;

import java.util.List;

public class Serializer {

    public static <T> List<T> deserializeAsList(Response response, Class<T> objectType){
        return response.body().jsonPath().getList("", objectType);
    }
}
