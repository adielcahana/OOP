import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Log implements Expression {
	private Expression base;
	private Expression antilogarithm;
	
	public Log(Expression base, Expression antilogarithm) {
		this.base = base;
		this.antilogarithm = antilogarithm;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double logarithm = 0;
        try {
        	Entry<String, Double> value = i.next();
        	Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
		    while (i.hasNext()) {
		    	value = i.next();
			    newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
		    }
		    logarithm = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Log Var wasn't found:" + e);
        }
        return logarithm; 
	}

	@Override
	public double evaluate() throws Exception {
		double logarithm = 0;
		try {
			logarithm = Math.log(antilogarithm.evaluate()) / Math.log(base.evaluate());
			System.out.println(logarithm);
		} catch (Exception e) {
			System.out.println("Pow evaluation failed :" + e);
		}
		return logarithm;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = base.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = antilogarithm.getVariables();
		if (tempVars != null) {
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Log(base.assign(var, expression), antilogarithm.assign(var, expression));
	}		

	public String toString() {
		return "Log(" + base.toString() + ", " + antilogarithm.toString() + ")";
	}
	
	@Override
	public Expression differentiate(String var) {
		boolean varInBase = base.toString().contains(var);
		boolean varInAntilogarithm = antilogarithm.toString().contains(var);
		if (varInBase == false && varInAntilogarithm == false ) {
			return new Num(0);	
		}
		if (varInAntilogarithm == true) {
			return new Mult(new Div(new Num(1), new Mult(antilogarithm, new Log(new Const("e"), base))), antilogarithm.differentiate(var));	
		}
		Expression log = new Div(new Log(new Num(2), antilogarithm), new Log(new Num(2), base));
		return log.differentiate(var); 
	}
}
