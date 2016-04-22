import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class UnaryExpression {
	protected Expression arg;
	protected String operator;

	public UnaryExpression (Expression arg){
		this.arg = arg;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
		Iterator<Entry<String, Double>> i = values.iterator();
		double expression = 0;
		Expression newExpression = null;
		try {
			while (i.hasNext()) {
				newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
			}
			expression = newExpression.evaluate();
		} catch (Exception e) {
		}
		return expression; 
	}

	public abstract double evaluate() throws Exception;

	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(arg.getVariables());
		return variables;
	}
	
	public String toString(){
		return this.operator + "(" + arg.toString() + ")";
	}
	
	public abstract Expression assign(String var, Expression expression);

	}