import configs.ConfigurationApp;
import functions.Function1D;
import functions.PolynomialFunction;
import methods.LagrangeMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
        LagrangeMethod lagrangeMethod = context.getBean(LagrangeMethod.class);
        PolynomialFunction function1D = new PolynomialFunction();
        function1D.setCoefs(new double[]{1., 1., 1.});
        System.out.println(lagrangeMethod.execute(new double[]{1., 2., 3.}, 1.5, function1D));
    }
}
