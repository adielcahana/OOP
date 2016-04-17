import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Div implements Expression{
	private Expression numerator;
	private Expression denominator;
	
	public Div(Expression numerator, Expression denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double quotient = 0;
        try {
        	Entry<String, Double> value = i.next();
        	Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
		    while (i.hasNext()) {
		    	value = i.next();
			    newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
		    }
		    quotient = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Div Var wasn't found:" + e);
        }
        return quotient; 
	}

	@Override
	public double evaluate() throws Exception {
		double quotient = 0;
		try {
			quotient = numerator.evaluate() / denominator.evaluate();
			System.out.println(quotient);
		} catch (Exception e) {
			System.out.println("Div evaluation faild :" + e);
		}
		return quotient;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = numerator.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = denominator.getVariables();
		if (tempVars != null) {
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Div(numerator.assign(var, expression), denominator.assign(var, expression));
	}		

	public String toString() {
		return "(" + numerator.toString() + " / " + denominator.toString() + ")";
	}
	
	@Override
	public Expression differentiate(String var) {
		if (numerator instanceof Num && denominator instanceof Num) {
			return new Num(0);	
		}
		if (numerator instanceof Num && !(denominator instanceof Num)) {
			return new Mult(numerator, denominator.differentiate(var));
		}
		return new Div(new Minus(new Mult(numerator.differentiate(var), denominator),new Mult(numerator,denominator.differentiate(var))), new Pow(denominator, new Num(2)));
	}
	
	@Override
	public Expression simplify() {
		Div simpledExp = new Div(this.numerator.simplify(), this.numerator.simplify());
		if (simpledExp.numerator instanceof Num && simpledExp.denominator instanceof Num) {
			if (simpledExp.denominator.toString() != "0") {
                return new Num(simpledExp.evaluate());	
		    }
		}
		if (simpledExp.numerator.toString() == "0") {
			return new Num(0);	
		}
		if (simpledExp.denominator.toString() == "1") {
			return numerator;	
		}
		return simpledExp;
	}
}
