import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Mult implements Expression {
	Expression multiplier;
    Expression multiplicand;
	
	public Mult(Expression multiplier, Expression multiplicand) {
		this.multiplier = multiplier;
		this.multiplicand = multiplicand;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double product = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    product = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Plus Var wasn't found:" + e);
        }
        return product; 
	}

	@Override
	public double evaluate() throws Exception {
		double product = 0;
		try {
			product = multiplier.evaluate() * multiplicand.evaluate();
		} catch (Exception e) {
			System.out.println("Minus evaluation faild :" + e);
		}
		return product;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(multiplier.getVariables());
		variables.addAll(multiplicand.getVariables());
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Mult(multiplier.assign(var, expression), multiplicand.assign(var, expression));
	}		

	public String toString() {
		return "(" + multiplier.toString() + " * " + multiplicand.toString() + ")";
	}
	
	@Override
	public Expression differentiate(String var) {
		return new Mult(multiplier.differentiate(var), multiplicand.differentiate(var));
	}
}
