package methods.lagrange_method;

import functions.Function;
import functions.Function1D;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
public class LagrangeMethod{

    public double execute(double[] x_values, double[] y_values, double x){
        List<Double> listXValues = Arrays.stream(x_values).boxed().collect(Collectors.toList());
        List<Double> listYValues = Arrays.stream(y_values).boxed().collect(Collectors.toList());
        return execute(listXValues, listYValues, x);
    }

    public double execute(List<Double> listXValues, List<Double> listYValues, double x){

        double result = 0;
        List<Future<Double>> futures = new ArrayList<>();
        for(int i = 0; i < listXValues.size(); i++){
            futures.add(coefMultiplier(listXValues, x, i));
        }
        for(int i = 0; i < listXValues.size(); i++){
            double multi = 0;
            try {
                multi = futures.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            result += listYValues.get(i)*multi;
        }

        return result;
    }

    @Async
    Future<Double> coefMultiplier(List<Double> points, double x, int i){
        double multi = 1;
        for(int j = 0; j < points.size(); j++){
            if(j == i) continue;
            multi *= (x - points.get(j))/(points.get(i) - points.get(j));
        }
        return new AsyncResult<>(multi);
    }

}
