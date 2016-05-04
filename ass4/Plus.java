public class Plus extends BinaryExpression implements Expression {

    public Plus(Object argA, Object argB) {
        super(argA, argB);
        this.setOperator(" + ");
    }

    @Override
    public double evaluate() throws Exception {
        double sum = 0;
        try {
            sum = this.getArgA().evaluate() + this.getArgB().evaluate();
//            System.out.println(sum);
        } catch (Exception e) {
            System.out.println("Plus evaluation faild :" + e);
            throw e;
        }
        return sum;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(this.getArgA().assign(var, expression), this.getArgB().assign(var, expression));
    }

    public String toString() {
        return "(" + this.getArgA().toString() + " + " + this.getArgB().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(this.getArgA().differentiate(var), this.getArgB().differentiate(var));
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
            return this.getArgB();
        }
        if (this.getArgB().toString().equals("0.0")) {
            return this.getArgA();
        }
        return this;
    }
}
