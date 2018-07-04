package methods.hermite_interpolation;

import methods.utils.TableFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HermiteFunction implements TableFunction {

    /**
     * Store list number that x belongs.
     */
    private Map<Integer, Integer> mapNumberOfXValue;
    private Map<Integer, Double> factorialMap;

    HermiteFunction(Map<Integer, Integer> mapNumberOfXValue){
        this.mapNumberOfXValue = mapNumberOfXValue;
        this.factorialMap = new HashMap<>();
    }

    @Override
    public Double countValue(List<Double> values, List<Double> y_values, List<Integer> indexes, List<Integer> first, List<Integer> second, Map<List<Integer>, Double> table) {
        if(mapNumberOfXValue.get(indexes.get(0)).equals(mapNumberOfXValue.get(indexes.get(indexes.size() - 1)))){
            return y_values.get(mapNumberOfXValue.get(indexes.get(0)) + indexes.size() - 1)/countFactorial(indexes.size() - 1);
        }

        return (table.get(first) - table.get(second)) /
                (values.get(indexes.get(indexes.size() - 1)) - values.get(indexes.get(0)));
    }

    private double countFactorial(int n){
        if(factorialMap.containsKey(n)){
            return factorialMap.get(n);
        }
        if(n == 0){
            factorialMap.put(0, 1.);
            return 1;
        }
        if(factorialMap.containsKey(n-1)){
            factorialMap.put(n , factorialMap.get(n - 1) * n );
            return factorialMap.get(n);
        } else {
            return countFactorial(n - 1);
        }
    }
}
