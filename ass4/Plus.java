import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Plus implements Expression {
    private Expression argA;
    private Expression argB;
	
	public Plus(Expression argA, Expression argB) {
		this.argA = argA;
		this.argB = argB;
	}
	
	@Override
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
        	System.out.println("Plus Var wasn't found:" + e);
        }
        return sum; 
	}

	@Override
	public double evaluate() throws Exception {
		double sum = 0;
		try {
			sum = argA.evaluate() + argB.evaluate();
			System.out.println(sum);
		} catch (Exception e) {
			System.out.println("Plus evaluation faild :" + e);
		}
		return sum;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = argA.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = argB.getVariables();
		if (tempVars != null) {
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Plus(argA.assign(var, expression), argB.assign(var, expression));
	}		

	public String toString() {
		return "(" + argA.toString() + " + " + argB.toString() + ")";
	}

	@Override
	public Expression differentiate(String var) {
		return new Plus(argA.differentiate(var), argB.differentiate(var));
	}
	
	@Override
	public Expression simplify() {
		Plus simpledExp = new Plus(this.argA.simplify(), this.argA.simplify());
		if (simpledExp.argA instanceof Num && simpledExp.argB instanceof Num) {
			if (simpledExp.argA.toString() == "0" && simpledExp.argB.toString() == "0") {
				return new Num(0);	
			}
			return new Num(simpledExp.evaluate());	
		}
		if (simpledExp.argA.toString() == "0") {
			return argB;	
		}
		if (simpledExp.argB.toString() == "0") {
			return argA;	
		}
		return simpledExp;
	}
}
