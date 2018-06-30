package methods.aitkens_scheme;

import functions.Function1D;
import methods.utils.TableBuilder;
import methods.utils.TableFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AitkenScheme {

    @Autowired
    TableBuilder tableBuilder;

    public double execute(double[] x_values, double[] y_values, double x){
        List<Double> listXValues = Arrays.stream(x_values).boxed().collect(Collectors.toList());
        List<Double> listYValues = Arrays.stream(y_values).boxed().collect(Collectors.toList());
        return execute(listXValues, listYValues, x);
    }

    public double execute(List<Double> listXValues, List<Double> listYValues, double x){
        TableFunction tableFunction = ((values, indexes, first, second, table) ->
                (table.get(first)*(x - values.get(indexes.get(0))) - table.get(second)*(x - values.get(indexes.get(indexes.size() - 1))) ) /
                (values.get(indexes.get(indexes.size() - 1)) - values.get(indexes.get(0)) ));
        Map<List<Integer>, Double> polynomialsTable = tableBuilder.buildTable(listXValues, listYValues, x, tableFunction);
        List<Integer> indexes = IntStream.range(0, listXValues.size()).boxed().collect(Collectors.toList());

        return polynomialsTable.get(indexes);
    }

}
