public class Div extends BinaryExpression implements Expression{

    public Div(Object numerator, Object denominator) {
        super(numerator, denominator);
        this.operator = " / ";
    }

    @Override
    public double evaluate() throws Exception {
        double quotient = 0;
        try {
            quotient = argA.evaluate() / argB.evaluate();
    //        System.out.println(quotient);
        } catch (Exception e) {
            System.out.println("Div evaluation faild :" + e);
        }
        return quotient;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(argA.assign(var, expression), argB.assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        if (argA instanceof Num && argB instanceof Num) {
            return new Num(0);
        }
        if (argA instanceof Num && !(argB instanceof Num)) {
            return new Mult(argA, argB.differentiate(var));
        }
        return new Div(
                new Minus(
                        new Mult(argA.differentiate(var), argB), new Mult(argA, argB.differentiate(var)))
                , new Pow(argB, new Num(2)));
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
        if (argA.toString().equals("0.0")) {
            return new Num(0);
        }
        if (argA.toString().equals(argB.toString())) {
            return new Num(0);
        }
        if (argB.toString().equals("1.0")) {
            return argA;
        }
        return this;
    }
}
