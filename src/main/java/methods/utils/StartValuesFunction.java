package methods.utils;

import java.util.List;
import java.util.Map;

public interface StartValuesFunction {
    void buildStartValues(Map<List<Integer>, Double> table, List<Double> y_values);
}
