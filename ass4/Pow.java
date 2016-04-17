import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pow implements Expression{
	private Expression base;
	private Expression exponent;
	
	public Pow(Expression base, Expression exponent) {
		this.base = base;
		this.exponent = exponent;
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
			power = Math.pow(base.evaluate(), exponent.evaluate());
			System.out.println(power);
		} catch (Exception e) {
			System.out.println("Pow evaluation failed :" + e);
		}
		return power;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = base.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = exponent.getVariables();
		if (tempVars != null) {
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Pow(base.assign(var, expression), exponent.assign(var, expression));
	}		

	public String toString() {
		return "(" + base.toString() + " ^ " + exponent.toString() + ")";
	}
	
	public Expression differentiate(String var) {
		boolean varInBase = base.toString().contains(var);
		boolean varInExponent = exponent.toString().contains(var);
		if (varInBase == true && varInExponent == true ){
			Expression expression = new Log(new Const("e"), base);
			expression = new Mult(exponent, expression);
			expression = new Pow(new Const("e"), expression);
			return expression.differentiate(var);
		}
		if (varInBase == true) {
			return new Div(new Mult(exponent, this), base);
		} else if (varInExponent == true) {
			Expression expression = new Log(new Const("e"), base);
			expression = new Mult(new Mult(this, expression), exponent.differentiate(var));
			return expression;
		}
		return new Num(0);	
	}
}
