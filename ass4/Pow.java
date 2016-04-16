import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pow implements Expression{
    Expression base;
    Expression exponent;
	
	public Pow(Expression base, Expression exponent) {
		this.base = base;
		this.exponent = exponent;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double power = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
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
		} catch (Exception e) {
			System.out.println("Pow evaluation failed :" + e);
		}
		return power;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(base.getVariables());
		variables.addAll(exponent.getVariables());
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
		return new Div(new Mult(exponent, this), base);
	}
}
