import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Plus implements Expression {
    Expression argA;
    Expression argB;
	
	public Plus(Expression argA, Expression argB) {
		this.argA = argA;
		this.argB = argB;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double sum = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    sum = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Plus Var wasn't found:" + e);
        }
        return sum; 
	}

	@Override
	public double evaluate() throws Exception {
		double sum = 0;
		try {
			sum = argA.evaluate()+ argB.evaluate();
		} catch (Exception e) {
			System.out.println("Plus evaluation faild :" + e);
		}
		return sum;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(argA.getVariables());
		variables.addAll(argB.getVariables());
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Plus(argA.assign(var, expression), argB.assign(var, expression));
	}		

	public String toString() {
		return "(" + argA.toString() + "+" + argB.toString() + ")";
	}
}
