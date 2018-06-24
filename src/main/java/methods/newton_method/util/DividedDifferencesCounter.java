package methods.newton_method.util;

import functions.Function1D;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DividedDifferencesCounter {

    public Map<List<Integer>, Double> buildTable(List<Double> values, Function1D<Double, Double> function1D) {
        Map<List<Integer>, Double> dividedDifferencesTable = new HashMap<>();
        for(int i = 0; i < values.size(); i++){
            List<Integer> listIndexes = new ArrayList<>();
            listIndexes.add(i);
            dividedDifferencesTable.put(listIndexes, function1D.execute(values.get(i)));
        }

        for (int n = 2; n <= values.size(); n++) {
            for (int startingIndex = 0; startingIndex <= values.size() - n; startingIndex++) {
                List<Integer> indexes = new ArrayList<>();
                for (int i = startingIndex; i < n + startingIndex; i++) {
                    indexes.add(i);
                }
                List<Integer> first = indexes.subList(1, indexes.size());
                List<Integer> second = indexes.subList(0, indexes.size() - 1);
                dividedDifferencesTable.put(indexes,
                        (dividedDifferencesTable.get(first) - dividedDifferencesTable.get(second)) /
                                (values.get(indexes.get(indexes.size() - 1)) - values.get(indexes.get(0)) ));
            }
        }

        return dividedDifferencesTable;
    }
}
