package methods;

import functions.Function;
import functions.Function1D;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class LagrangeMethod{

    public double execute(double[] points, double x, Function1D<Double, Double> function){

        double result = 0;
        List<Future<Double>> futures = new ArrayList<>();
        for(int i = 0; i < points.length; i++){
            futures.add(coefMultiplier(points, x, i));
        }
        for(int i = 0; i < points.length; i++){
            double multi = 0;
            try {
                multi = futures.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            result += function.execute(points[i])*multi;
        }

        return result;
    }

    @Async
    public Future<Double> coefMultiplier(double[] points, double x, int i){
        double multi = 1;
        for(int j = 0; j < points.length; j++){
            if(j == i) continue;
            multi *= (x - points[j])/(points[i] - points[j]);
        }
        return new AsyncResult<>(multi);
    }

}
