import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class UnaryExpression {
	private Expression arg;
	private String operator;
	
	public UnaryExpression (Object arg){
        if (arg instanceof String) {
            this.arg = new Var((String) arg);
        } else if (arg instanceof Double) {
            this.arg = new Num((double) arg);
        } else if (arg instanceof Integer) {
            this.arg = new Num((int) arg);
        } else if (arg instanceof Expression) {
            this.arg = (Expression) arg;
        } else {
            throw new InvalidParameterException();
        }
	}
	
    protected Expression getArg() {
        return arg;
    }


    protected void setArg(Expression arg) {
		this.arg = arg;
	}

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