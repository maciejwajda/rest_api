package api;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {

    /**
     * Filter list using given condition
     *
     * @param items list to be filtered
     * @param condition filter criteria
     * @return List of filtered objects
     */
    public static <T> List<T> applyFilter(List<T> items, Predicate<T> condition){
        return items.stream().
                filter(condition).
                collect(Collectors.toList());
    }
}
