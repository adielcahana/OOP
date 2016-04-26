import java.security.InvalidParameterException;
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
	
	public UnaryExpression (Object arg){
		if((arg instanceof Double)){
			this.arg = new Num((Double) arg);
		} else if (arg instanceof Integer){
			this.arg = new Num((int) arg);
		}else if (arg instanceof String){
			this.arg = new Var((String) arg);
		} else if (arg instanceof Expression){
			this.arg = (Expression) arg;
		}else {
    		throw new InvalidParameterException();
    	}
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
		List<String> tempVars = arg.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		return variables;}
	
	public String toString(){
		return this.operator + "(" + arg.toString() + ")";
	}
	
	public abstract Expression assign(String var, Expression expression);

	public Expression simplify(){
		if (arg.getVariables() == null) {
			try {
				double evaluate = this.evaluate();
				Expression exp = new Num(evaluate); 
				return exp;
			} catch (Exception e){	
			}
	}
		return arg;
	}
}