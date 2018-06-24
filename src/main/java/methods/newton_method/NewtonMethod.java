package methods.newton_method;

import com.google.common.collect.Lists;
import functions.Function1D;
import methods.newton_method.util.DividedDifferencesCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class NewtonMethod {

    @Autowired
    DividedDifferencesCounter dividedDifferencesCounter;

    public double execute(double[] points, double x, Function1D<Double, Double> function){
        List<Double> listValues = Arrays.stream(points).boxed().collect(Collectors.toList());
        Map<List<Integer>, Double> dividedDifferences = dividedDifferencesCounter.buildTable(listValues, function);

        double result = 0.;
        List<Integer> dividedIndexes = new ArrayList<>();
        double polyCoef = 1;
        for (int i = 0; i < points.length; i++){
            dividedIndexes.add(i);
            result += dividedDifferences.get(dividedIndexes)*polyCoef;
            polyCoef *= x - points[i];
        }

        return result;
    }

}
