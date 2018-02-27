package api;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {

    public static <T>List<T> applyFilter(List<T> comments, Predicate<T> condition){
        return comments.stream().
                filter(condition).
                collect(Collectors.toList());
    }
}
