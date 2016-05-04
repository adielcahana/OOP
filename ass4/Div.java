public class Div extends BinaryExpression implements Expression{

    public Div(Object numerator, Object denominator) {
        super(numerator, denominator);
        this.setOperator(" / ");
    }

    @Override
    public double evaluate() throws Exception {
        double quotient = 0;
        try {
            quotient = this.getArgA().evaluate() / this.getArgB().evaluate();
    //        System.out.println(quotient);
        } catch (Exception e) {
            System.out.println("Div evaluation faild :" + e);
            throw e;
        }
        return quotient;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(this.getArgA().assign(var, expression), this.getArgB().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        if (this.getArgA() instanceof Num && this.getArgB() instanceof Num) {
            return new Num(0);
        }
//        if (this.getArgA() instanceof Num && !(this.getArgB() instanceof Num)) {
//            return new Mult(this.getArgA(), this.getArgB().differentiate(var));
//        }
        return new Div(
                new Minus(
                        new Mult(this.getArgA().differentiate(var), this.getArgB()), new Mult(this.getArgA(), this.getArgB().differentiate(var)))
                , new Pow(this.getArgB(), new Num(2)));
    }

    @Override
    public Expression simplify() {
        if (this.getArgA().getVariables() == null && this.getArgB().getVariables() == null) {
            try {
                double evaluate = this.evaluate();
                Expression exp = new Num(evaluate);
                return exp;
            } catch (Exception e){
            }
        }
        this.setArgA(this.getArgA().simplify());
        this.setArgB(this.getArgB().simplify());
        if (this.getArgA().toString().equals("0.0")) {
            return new Num(0);
        }
        if (this.getArgA().toString().equals(this.getArgB().toString())) {
            return new Num(1);
        }
        if (this.getArgB().toString().equals("1.0")) {
            return this.getArgA();
        }
        return this;
    }
}
