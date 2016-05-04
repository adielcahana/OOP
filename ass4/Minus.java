public class Minus extends BinaryExpression implements Expression{

    public Minus(Object minuend, Object subtrahend) {
        super(minuend, subtrahend);
        this.setOperator(" - ");
    }

    @Override
    public double evaluate() throws Exception {
        double difference = 0;
        try {
            difference = this.getArgA().evaluate() - this.getArgB().evaluate();
        //    System.out.println(difference);
        } catch (Exception e) {
            System.out.println("Minus evaluation faild :" + e);
            throw e;
        }
        return difference;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(this.getArgA().assign(var, expression), this.getArgB().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(this.getArgA().differentiate(var), this.getArgB().differentiate(var));
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
        if (this.getArgB().toString().equals(this.getArgA().toString())) {
            return new Num(0);
        }
        if (this.getArgA().toString().equals("0.0")) {
            return new Neg(this.getArgB());
        }
        if (this.getArgB().toString().equals("0.0")) {
            return this.getArgA();
        }
        return this;
    }
}
