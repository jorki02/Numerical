package methods.hermite_interpolation;

import methods.newton_method.NewtonMethod;
import methods.utils.StartValuesFunction;
import methods.utils.TableBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class HermiteInterpolation {

    @Autowired
    NewtonMethod newtonMethod;
    @Autowired
    TableBuilder tableBuilder;

    public double execute(double[] x_values, double[][] y_values, double x){
        List<Double> listXValues = Arrays.stream(x_values).boxed().collect(Collectors.toList());
        List<List<Double>> listYValues = Arrays.stream(y_values)
                .map(y_->Arrays.stream(y_).boxed().collect(Collectors.toList())).collect(Collectors.toList());
        return execute(listXValues, listYValues, x);
    }

    public double execute(List<Double> listXValues, List<List<Double>> listYValues, double x){
        List<Double> fullListXValues = new ArrayList<>();
        List<Double> fullListYValues = new ArrayList<>();
        Map<Integer, Integer> mapNumberOfXValue = new HashMap<>();
        int n = 0;
        int start = 0;
        for(int i = 0; i < listYValues.size(); i++){
            start = n;
            for(int j = 0; j < listYValues.get(i).size(); j++){
                mapNumberOfXValue.put(n, start);
                fullListXValues.add(listXValues.get(i));
                fullListYValues.add(listYValues.get(i).get(j));
                n++;
            }
        }

        StartValuesFunction startValuesFunction = ((table, y_values) -> { IntStream.range(0, y_values.size())
                .forEach(i ->table.put(IntStream.of(i).boxed()
                        .collect(Collectors.toList()), y_values.get(mapNumberOfXValue.get(i))));});

        Map<List<Integer>, Double> dividedDifferences = tableBuilder.buildTable(fullListXValues, fullListYValues, x,
                new HermiteFunction(mapNumberOfXValue), startValuesFunction);

        return newtonMethod.countResult(fullListXValues, dividedDifferences, x);
    }

}
