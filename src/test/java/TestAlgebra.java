import configs.ConfigurationApp;
import methods.gauss_method.GaussMethod;
import methods.lagrange_method.LagrangeMethod;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAlgebra {

    @Test
    public void testGauss(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        GaussMethod gaussMethod = context.getBean(GaussMethod.class);
        double[][] coefs = new double[][]{{1, 2, 3}, {1, 2, 4}, {2 ,5 ,6}};
        double[] y_values = new double[]{4, 3, 6};

        Assert.assertTrue(countNorm(new double[]{11., -2., -1.}, gaussMethod.execute(coefs, y_values)) < 0.00001);
    }

    private double countNorm(double[] x, double[] y){
        double norm = 0.;
        for(int i = 0; i < x.length; i++){
            norm += (x[i] - y[i])*(x[i] - y[i]);
        }
        return Math.sqrt(norm);
    }

}
