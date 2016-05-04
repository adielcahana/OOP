import java.util.ArrayList;
import java.util.List;

public class Pow extends BinaryExpression implements Expression{

    public Pow(Object base, Object exponent) {
        super(base, exponent);
        this.setOperator("^");
    }

    @Override
    public double evaluate() throws Exception {
        double power = 0;
        try {
            power = Math.pow(this.getArgA().evaluate(), this.getArgB().evaluate());
        //    System.out.println(power);
        } catch (Exception e) {
            System.out.println("Pow evaluation failed :" + e);
            throw e;
        }
        return power;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(this.getArgA().assign(var, expression), this.getArgB().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        boolean varInBase = this.getArgA().toString().contains(var);
        boolean varInExponent = this.getArgB().toString().contains(var);
        if (varInBase && varInExponent){
            Expression expression = new Log(new Const("e"), this.getArgA());
            expression = new Mult(this.getArgB(), expression);
            expression = new Pow(new Const("e"), expression);
            return expression.differentiate(var);
        }
        if (varInBase) {
            return new Mult(new Div(new Mult(this.getArgB(), this), this.getArgA()) , this.getArgA().differentiate(var));
        } else if (varInExponent) {
            Expression expression = new Log(new Const("e"), this.getArgA());
            expression = new Mult(new Mult(this, expression), this.getArgB().differentiate(var));
            return expression;
        }
        return new Num(0);
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
        if (this.getArgA().toString().equals("0.0")) {
            return new Num(0);
        }
        if (this.getArgB().toString().equals("1.0")) {
            return this.getArgA();
        }
        return this;
    }
}
