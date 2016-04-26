public class Plus extends BinaryExpression implements Expression {

    public Plus(Object argA, Object argB) {
        super(argA, argB);
        this.operator = " + ";
    }

    @Override
    public double evaluate() throws Exception {
        double sum = 0;
        try {
            sum = argA.evaluate() + argB.evaluate();
//            System.out.println(sum);
        } catch (Exception e) {
            System.out.println("Plus evaluation faild :" + e);
        }
        return sum;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(argA.assign(var, expression), argB.assign(var, expression));
    }

    public String toString() {
        return "(" + argA.toString() + " + " + argB.toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(argA.differentiate(var), argB.differentiate(var));
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
            return argB;
        }
        if (argB.toString().equals("0.0")) {
            return argA;
        }
        return this;
    }
}
