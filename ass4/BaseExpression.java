import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class BaseExpression {

	private String operator;
	
	protected void setOperator(String operator) {
		this.operator = operator;
	}
	
    protected String getOperator() {
		return operator;
	}
	
	public double evaluate(Map<String, Double> assignment) throws Exception {
        Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double expression = 0;
        try {
            Entry<String, Double> value = i.next();
            Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
            while (i.hasNext()) {
                value = i.next();
                newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
            }
            expression = newExpression.evaluate();
        } catch (Exception e) {
          }
        return expression;
    }
	
	public abstract double evaluate() throws Exception;
	
	public abstract List<String> getVariables();	
	
	public abstract String toString();
	
	public abstract Expression assign(String var, Expression expression);
}
