package methods.utils;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface TableFunction {
    Double countValue(List<Double> values, List<Integer> indexes, List<Integer> first, List<Integer> second, Map<List<Integer>, Double> table);
}
