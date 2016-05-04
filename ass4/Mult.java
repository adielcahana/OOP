public class Mult extends BinaryExpression implements Expression {

    public Mult(Object multiplier, Object multiplicand) {
        super(multiplier, multiplicand);
        this.setOperator(" * ");
    }

    @Override
    public double evaluate() throws Exception {
        double product = 0;
        try {
            product = this.getArgA().evaluate() * this.getArgB().evaluate();
        //    System.out.println(product);
        } catch (Exception e) {
            System.out.println("Mult evaluation faild :" + e);
            throw e;
        }
        return product;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(this.getArgA().assign(var, expression), this.getArgB().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        if (this.getArgA() instanceof Num && this.getArgB() instanceof Num) {
            return new Num(0);
        }
        if (this.getArgA() instanceof Num && !(this.getArgB() instanceof Num)) {
            return new Mult(this.getArgA(), this.getArgB().differentiate(var));
        }
        return new Plus(new Mult(this.getArgA().differentiate(var), this.getArgB()),
                        new Mult(this.getArgA(), this.getArgB().differentiate(var)));
    }

    @Override
    public Expression simplify() {
        if (getArgA().getVariables() == null && getArgB().getVariables() == null) {
            try {
                double evaluate = this.evaluate();
                Expression exp = new Num(evaluate);
                return exp;
            } catch (Exception e) {
            }
        }
        this.setArgA(this.getArgA().simplify());
        this.setArgB(this.getArgB().simplify());
        if (getArgA().toString().equals("0.0") || getArgB().toString().equals("0.0")) {
            return new Num(0);
        }
        if (getArgA().toString().equals("1.0")) {
            return getArgB();
        }
        if (getArgB().toString().equals("1.0")) {
            return getArgA();
        }
        return this;
    }
}