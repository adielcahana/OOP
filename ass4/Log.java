public class Log extends BinaryExpression implements Expression {

    public Log(Object base, Object antilogarithm) {
        super(base, antilogarithm);
        this.setOperator(", ");
    }

    @Override
    public double evaluate() throws Exception {
        double logarithm = 0;
        try {
            logarithm = Math.log(this.getArgB().evaluate()) / Math.log(this.getArgA().evaluate());
            //System.out.println(logarithm);
        } catch (Exception e) {
            System.out.println("Pow evaluation failed :" + e);
        }
        return logarithm;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(this.getArgA().assign(var, expression), this.getArgB().assign(var, expression));
    }
    
    @Override
    public String toString() {
        return "Log" + super.toString();
    }

    @Override
    public Expression differentiate(String var) {
        boolean varInBase = this.getArgA().toString().contains(var);
        boolean varInAntilogarithm = this.getArgB().toString().contains(var);
        if (!varInBase && !varInAntilogarithm) {
            return new Num(0);
        }
        if (varInAntilogarithm) {
            return new Mult(
                    new Div(
                            new Num(1),
                            new Mult(this.getArgB(), new Log(new Const("e"), this.getArgA())))
                    , this.getArgB().differentiate(var));
        }
        Expression log = new Div(new Log(new Num(2), this.getArgB()), new Log(new Num(2), this.getArgA()));
        return log.differentiate(var);
    }

    @Override
    public Expression simplify() {
        if (this.getArgA().getVariables() == null && this.getArgB().getVariables() == null) {
            try {
                double evaluate = this.evaluate();
                Expression exp = new Num(evaluate);
                return exp;
            } catch (Exception e) {
            }
        }
        this.setArgA(this.getArgA().simplify());
        this.setArgB(this.getArgB().simplify());
        if (this.getArgA().toString().equals(this.getArgB().toString())) {
            return new Num(1);
        }
        return this;
    }
}
