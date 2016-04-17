import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Minus implements Expression{
	private Expression minuend;
	private Expression subtrahend;
	
	public Minus(Expression minuend, Expression subtrahend) {
		this.minuend = minuend;
		this.subtrahend = subtrahend;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double evaluate = 0;
        try {
        	Entry<String, Double> value = i.next();
        	Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
		    while (i.hasNext()) {
		    	value = i.next();
			    newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
		    }
		    evaluate = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Minus Var wasn't found:" + e);
        }
        return evaluate; 
	}

	@Override
	public double evaluate() throws Exception {
		double difference = 0;
		try {
			difference = minuend.evaluate() - subtrahend.evaluate();
			System.out.println(difference);
		} catch (Exception e) {
			System.out.println("Minus evaluation faild :" + e);
		}
		return difference;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = minuend.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = subtrahend.getVariables();
		if (tempVars != null) {
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Minus(minuend.assign(var, expression), subtrahend.assign(var, expression));
	}		

	public String toString() {
		return "(" + minuend.toString() + " - " + subtrahend.toString() + ")";
	}
	
	@Override
	public Expression differentiate(String var) {
		return new Minus(minuend.differentiate(var), subtrahend.differentiate(var));
	}
	
	@Override
	public Expression simplify() {
		Minus simpledExp = new Minus(this.minuend.simplify(), this.minuend.simplify());
		if (simpledExp.minuend instanceof Num && simpledExp.subtrahend instanceof Num) {
			if (simpledExp.minuend.toString() == "0" && simpledExp.subtrahend.toString() == "0") {
				return new Num(0);
			return new Num(simpledExp.evaluate());
			}
		}
		if (simpledExp.minuend.toString() == "0") {
			return new Neg(subtrahend);	
		}
		if (simpledExp.subtrahend.toString() == "0") {
			return minuend;	
		}
		return simpledExp;
	}
}
