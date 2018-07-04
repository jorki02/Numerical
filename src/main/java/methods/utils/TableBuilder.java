package methods.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TableBuilder {

    public Map<List<Integer>, Double> buildTable(List<Double> x_values, List<Double> y_values, double x,
                                                 TableFunction tableFunction, StartValuesFunction startValuesFunction){
        Map<List<Integer>, Double> table = new HashMap<>();
        startValuesFunction.buildStartValues(table, y_values);

        for (int n = 2; n <= x_values.size(); n++) {
            for (int startingIndex = 0; startingIndex <= x_values.size() - n; startingIndex++) {
                List<Integer> indexes = new ArrayList<>();
                for (int i = startingIndex; i < n + startingIndex; i++) {
                    indexes.add(i);
                }
                List<Integer> first = indexes.subList(1, indexes.size());
                List<Integer> second = indexes.subList(0, indexes.size() - 1);
                table.put(indexes, tableFunction.countValue(x_values, y_values, indexes, first, second, table));
            }
        }

        return table;
    }

}
