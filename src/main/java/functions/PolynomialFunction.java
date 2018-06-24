package functions;

import java.util.ArrayList;
import java.util.List;

public class PolynomialFunction implements Function1D<Double, Double> {

    List<Double> coefs;

    public void setCoefs(double[] coefs){
        this.coefs = new ArrayList<>();
        for(double coef : coefs){
            this.coefs.add(coef);
        }
    }

    public Double execute(Double x){
        if (coefs == null || coefs.size() == 0) return 0.;
        double result = 0;
        double x_ = 1;
        for(Double coef : coefs){
            result += coef*x_;
            x_ *= x;
        }
        return result;
    }

}
