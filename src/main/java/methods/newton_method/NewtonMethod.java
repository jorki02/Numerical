package methods.newton_method;

import com.google.common.collect.Lists;
import functions.Function1D;
import methods.newton_method.util.DividedDifferencesCounter;
import methods.utils.StartValuesFunction;
import methods.utils.TableBuilder;
import methods.utils.TableFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class NewtonMethod {

    @Autowired
    TableBuilder tableBuilder;

    public double execute(double[] x_values, double[] y_values, double x){
        List<Double> listXValues = Arrays.stream(x_values).boxed().collect(Collectors.toList());
        List<Double> listYValues = Arrays.stream(y_values).boxed().collect(Collectors.toList());
        return execute(listXValues, listYValues, x);
    }

    public double execute(List<Double> listXValues, List<Double> listYValues, double x){
        TableFunction tableFunction = ((values, y_values, indexes, first, second, table) ->
                (table.get(first) - table.get(second)) /
                        (values.get(indexes.get(indexes.size() - 1)) - values.get(indexes.get(0)) ));
        StartValuesFunction startValuesFunction = ((table, y_values) -> { IntStream.range(0, y_values.size())
                .forEach(i ->table.put(IntStream.of(i).boxed().collect(Collectors.toList()), y_values.get(i)));});
        Map<List<Integer>, Double> dividedDifferences = tableBuilder.buildTable(listXValues, listYValues, x,
                tableFunction, startValuesFunction);
        return countResult(listXValues, dividedDifferences, x);
    }

    public double countResult(List<Double> listXValues, Map<List<Integer>, Double> dividedDifferences, double x){
        double result = 0.;
        List<Integer> dividedIndexes = new ArrayList<>();
        double polyCoef = 1;
        for (int i = 0; i < listXValues.size(); i++) {
            dividedIndexes.add(i);
            result += dividedDifferences.get(dividedIndexes)*polyCoef;
            polyCoef *= x - listXValues.get(i);
        }
        return result;
    }

}
