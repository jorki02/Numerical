import configs.ConfigurationApp;
import functions.PolynomialFunction;
import methods.LagrangeMethod;
import methods.newton_method.NewtonMethod;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestInterpolationMethods{

    private final double eps = 0.0001;

    @Test
    public void testPolynomialLagrange(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        LagrangeMethod lagrangeMethod = context.getBean(LagrangeMethod.class);
        PolynomialFunction function1D = new PolynomialFunction();
        function1D.setCoefs(new double[]{1., 1., 1.});
        Assert.assertTrue((lagrangeMethod.execute(new double[]{1., 2., 3.}, 1.5, function1D) - 4.75) < eps );
    }

    @Test
    public void testPolynomialNewton(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        NewtonMethod newtonMethod = context.getBean(NewtonMethod.class);
        PolynomialFunction function1D = new PolynomialFunction();
        function1D.setCoefs(new double[]{1., 1., 1.});
        Assert.assertTrue((newtonMethod.execute(new double[]{1., 2., 3.}, 1.5, function1D) - 4.75) < eps );
    }

}
