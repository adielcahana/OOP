import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Div implements Expression{
	Expression numerator;
    Expression denominator;
	
	public Div(Expression numerator, Expression denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double quotient = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
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
		} catch (Exception e) {
			System.out.println("Div evaluation faild :" + e);
		}
		return quotient;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(numerator.getVariables());
		variables.addAll(denominator.getVariables());
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
		return new Div(numerator.differentiate(var), denominator.differentiate(var));
	}
}
