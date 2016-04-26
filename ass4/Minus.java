public class Minus extends BinaryExpression implements Expression{

    public Minus(Object minuend, Object subtrahend) {
        super(minuend, subtrahend);
        this.operator = " - ";
    }

    @Override
    public double evaluate() throws Exception {
        double difference = 0;
        try {
            difference = argA.evaluate() - argB.evaluate();
        //    System.out.println(difference);
        } catch (Exception e) {
            System.out.println("Minus evaluation faild :" + e);
        }
        return difference;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(argA.assign(var, expression), argB.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(argA.differentiate(var), argB.differentiate(var));
    }

    @Override
    public Expression simplify() {
        if (argA.getVariables() == null && argB.getVariables() == null) {
            try {
                double evaluate = this.evaluate();
                Expression exp = new Num(evaluate);
                return exp;
            } catch (Exception e){
            }
        }
        this.argA = this.argA.simplify();
        this.argB = this.argB.simplify();
        if (argB.toString().equals(argA.toString())) {
            return new Num(0);
        }
        if (argA.toString().equals("0.0")) {
            return new Neg(argB);
        }
        if (argB.toString().equals("0.0")) {
            return argA;
        }
        return this;
    }
}
