import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Log implements Expression {
    Expression base;
    Expression antilogarithm;
	
	public Log(Expression base, Expression antilogarithm) {
		this.base = base;
		this.antilogarithm = antilogarithm;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double logarithm = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    logarithm = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Pow Var wasn't found:" + e);
        }
        return logarithm; 
	}

	@Override
	public double evaluate() throws Exception {
		double logarithm = 0;
		try {
			logarithm = Math.log(antilogarithm.evaluate())/Math.log(base.evaluate());
		} catch (Exception e) {
			System.out.println("Pow evaluation failed :" + e);
		}
		return logarithm;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(base.getVariables());
		variables.addAll(antilogarithm.getVariables());
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Log(base.assign(var, expression), antilogarithm.assign(var, expression));
	}		

	public String toString() {
		return "log(" + base.toString() + ", " + antilogarithm.toString() + ")";
	}
	
	@Override
	public Expression differentiate(String var) {
		return new Mult(new Div(new Num(1), new Mult(antilogarithm, new Log(new Const("e", 2.71828))), base), antilogarithm.differentiate(var));
	}
}
