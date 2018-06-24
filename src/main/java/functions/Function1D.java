package functions;

public interface Function1D<Value,Result> extends Function {

    Result execute(Value value);

}
