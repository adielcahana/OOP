import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class BinaryExpression {
	protected Expression argA;
    protected Expression argB;
    protected String operator;
	
	public BinaryExpression(Expression argA, Expression argB) {
		this.argA = argA;
		this.argB = argB;
	}
	
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double sum = 0;
        try {
        	Entry<String, Double> value = i.next();
        	Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
		    while (i.hasNext()) {
		    	value = i.next();
			    newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
		    }
		    sum = newExpression.evaluate();
        } catch (Exception e) {
          }
        return sum; 
	}

	public abstract double evaluate() throws Exception;

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
	
	public String toString() {
		return "(" + argA.toString() + this.operator + argB.toString() + ")";
	}
	
	public abstract Expression assign(String var, Expression expression);	

}
