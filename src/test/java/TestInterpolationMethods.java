import configs.ConfigurationApp;
import functions.Function1D;
import functions.PolynomialFunction;
import methods.lagrange_method.LagrangeMethod;
import methods.aitkens_scheme.AitkenScheme;
import methods.newton_method.NewtonMethod;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class TestInterpolationMethods{

    private final double eps = 0.0001;

    @Test
    public void testPolynomialLagrange(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        LagrangeMethod lagrangeMethod = context.getBean(LagrangeMethod.class);
        PolynomialFunction function1D = new PolynomialFunction();
        function1D.setCoefs(new double[]{1., 1., 1.});
        List<Double> listXValues = DoubleStream.of(1., 2., 3.).boxed().collect(Collectors.toList());
        Assert.assertTrue((lagrangeMethod.execute(listXValues, buildFunctionValues(listXValues, function1D), 1.5) - 4.75) < eps );
    }

    @Test
    public void testPolynomialNewton(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        NewtonMethod newtonMethod = context.getBean(NewtonMethod.class);
        PolynomialFunction function1D = new PolynomialFunction();
        function1D.setCoefs(new double[]{1., 1., 1.});
        List<Double> listXValues = DoubleStream.of(1., 2., 3.).boxed().collect(Collectors.toList());
        Assert.assertTrue(
                (newtonMethod.execute(listXValues, buildFunctionValues(listXValues, function1D), 1.5) - 4.75) < eps );
    }

    @Test
    public void testAitkensScheme(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        AitkenScheme aitkenScheme = context.getBean(AitkenScheme.class);
        PolynomialFunction function1D = new PolynomialFunction();
        function1D.setCoefs(new double[]{1., 1., 1.});
        List<Double> listXValues = DoubleStream.of(1., 2., 3.).boxed().collect(Collectors.toList());
        Assert.assertTrue(
                (aitkenScheme.execute(listXValues, buildFunctionValues(listXValues, function1D), 1.5) - 4.75) < eps );
    }

    private List<Double> buildFunctionValues(List<Double> XValues, Function1D<Double, Double> function1D){
        return XValues.stream().map(function1D::execute).collect(Collectors.toList());
    }

}
