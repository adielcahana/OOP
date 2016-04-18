import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Log extends BinaryExpression implements Expression {

	
	public Log(Expression base, Expression antilogarithm) {
		super(base, antilogarithm);
		this.operator = ", ";
	}

	@Override
	public double evaluate() throws Exception {
		double logarithm = 0;
		try {
			logarithm = Math.log(argB.evaluate()) / Math.log(argA.evaluate());
			//System.out.println(logarithm);
		} catch (Exception e) {
			System.out.println("Pow evaluation failed :" + e);
		}
		return logarithm;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Log(argA.assign(var, expression), argB.assign(var, expression));
	}		

	public String toString() {
		return "Log" + super.toString();
	}
	
	@Override
	public Expression differentiate(String var) {
		boolean varInBase = argA.toString().contains(var);
		boolean varInAntilogarithm = argB.toString().contains(var);
		if (varInBase == false && varInAntilogarithm == false ) {
			return new Num(0);	
		}
		if (varInAntilogarithm == true) {
			return new Mult(new Div(new Num(1), new Mult(argB, new Log(new Const("e"), argA))), argB.differentiate(var));	
		}
		Expression log = new Div(new Log(new Num(2), argB), new Log(new Num(2), argA));
		return log.differentiate(var); 
	}
	
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
		if (argA.toString().equals(argB.toString())) {
			return new Num(1);	
		}
		return this;
	}
}
