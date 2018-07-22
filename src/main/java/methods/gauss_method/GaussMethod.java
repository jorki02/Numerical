package methods.gauss_method;

import org.springframework.stereotype.Component;

@Component
public class GaussMethod {

    private double eps = 0.000001;

    public double[] execute(double[][] coefs, double[] y_values){
        for(int i = 0; i < coefs.length; i++){
            checkZeroMainCoef(coefs, y_values, i);
            for(int j = i; j < coefs[i].length; j++){
                coefs[i][j] /= coefs[i][i];
            }
            y_values[i] /= coefs[i][i];

            for(int k = i + 1; k < coefs.length; k++){
                double mult_coef = coefs[k][i];
                for(int j = i; j < coefs[k].length; j++){
                    coefs[k][j] -= mult_coef * coefs[i][j];
                }
                y_values[k] -= mult_coef * y_values[i];
            }
        }

        double[] x_values = new double[coefs.length];

        for(int i = coefs.length - 1; i >= 0; i--){
            x_values[i] = y_values[i];
            for(int j = i + 1; j < coefs[i].length; j++){
                x_values[i] -= coefs[i][j]*x_values[j];
            }
        }

        return x_values;
    }

    private void checkZeroMainCoef(double[][] coefs, double[] y_values, int n){
        if(Math.abs(coefs[n][n]) > eps) return;
        for(int i = n + 1; i < coefs.length; i++){
            if(Math.abs(coefs[i][n]) > eps){
                double[] temp = coefs[i];
                coefs[i] = coefs[n];
                coefs[n] = temp;
                double temp_y = y_values[i];
                y_values[i] = y_values[n];
                y_values[n] = temp_y;
                return;
            }
        }
    }

}
