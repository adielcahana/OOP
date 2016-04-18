import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pow extends BinaryExpression implements Expression{
	
	public Pow(Expression base, Expression exponent) {
		super(base, exponent);
		this.operator = "^";
	}
	

	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double power = 0;
        try {
        	Entry<String, Double> value = i.next();
        	Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
		    while (i.hasNext()) {
		    	value = i.next();
			    newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
		    }
		    power = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Pow Var wasn't found:" + e);
        }
        return power; 
	}

	@Override
	public double evaluate() throws Exception {
		double power = 0;
		try {
			power = Math.pow(argA.evaluate(), argB.evaluate());
		//    System.out.println(power);
		} catch (Exception e) {
			System.out.println("Pow evaluation failed :" + e);
		}
		return power;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = argA.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = argB.getVariables();
		if (tempVars != null) {
			variables.removeAll(tempVars);
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Pow(argA.assign(var, expression), argB.assign(var, expression));
	}		

	public String toString() {
		return "(" + argA.toString() + "^" + argB.toString() + ")";
	}
	
	public Expression differentiate(String var) {
		boolean varInBase = argA.toString().contains(var);
		boolean varInExponent = argB.toString().contains(var);
		if (varInBase == true && varInExponent == true ){
			Expression expression = new Log(new Const("e"), argA);
			expression = new Mult(argB, expression);
			expression = new Pow(new Const("e"), expression);
			return expression.differentiate(var);
		}
		if (varInBase == true) {
			return new Div(new Mult(argB, this), argA);
		} else if (varInExponent == true) {
			Expression expression = new Log(new Const("e"), argA);
			expression = new Mult(new Mult(this, expression), argB.differentiate(var));
			return expression;
		}
		return new Num(0);	
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
		if (argA.toString().equals("0.0")) {
			return new Num(0);	
		}
		if (argB.toString().equals("1.0")) {
			return this.argA;	
		}
		return this;
	}
}
