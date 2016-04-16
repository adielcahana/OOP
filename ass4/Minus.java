import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Minus implements Expression{
	Expression minuend;
    Expression subtrahend;
	
	public Minus(Expression minuend, Expression subtrahend) {
		this.minuend = minuend;
		this.subtrahend = subtrahend;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double evaluate = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
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
		} catch (Exception e) {
			System.out.println("Minus evaluation faild :" + e);
		}
		return difference;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(minuend.getVariables());
		variables.addAll(subtrahend.getVariables());
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
}
