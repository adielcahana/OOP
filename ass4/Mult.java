import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Mult implements Expression {
	private Expression multiplier;
	private Expression multiplicand;
	
	public Mult(Expression multiplier, Expression multiplicand) {
		this.multiplier = multiplier;
		this.multiplicand = multiplicand;
	}
	
	@Override
	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double product = 0;
        try {
        	Entry<String, Double> value = i.next();
        	Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
		    while (i.hasNext()) {
		    	value = i.next();
			    newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
		    }
		    product = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Mult Var wasn't found:" + e);
        }
        return product; 
	}

	@Override
	public double evaluate() throws Exception {
		double product = 0;
		try {
			product = multiplier.evaluate() * multiplicand.evaluate();
			System.out.println(product);
		} catch (Exception e) {
			System.out.println("Mult evaluation faild :" + e);
		}
		return product;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		List<String> tempVars = multiplier.getVariables();
		if (tempVars != null) {
		    variables.addAll(tempVars);
		}
		tempVars = multiplicand.getVariables();
		if (tempVars != null) {
			variables.addAll(tempVars);
		}
		return variables;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Mult(multiplier.assign(var, expression), multiplicand.assign(var, expression));
	}		

	public String toString() {
		return "(" + multiplier.toString() + " * " + multiplicand.toString() + ")";
	}
	
	@Override
	public Expression differentiate(String var) {
		if (multiplier instanceof Num && multiplicand instanceof Num) {
			return new Num(0);	
		}
		if (multiplier instanceof Num && !(multiplicand instanceof Num)) {
			return new Mult(multiplier, multiplicand.differentiate(var));
		}
		return new Plus(new Mult(multiplier.differentiate(var), multiplicand),new Mult(multiplier, multiplicand.differentiate(var)));
		}
	@Override
	public Expression simplify() {
		Mult simpledExp = new Mult(this.multiplier.simplify(), this.multiplier.simplify());
		if (simpledExp.multiplier instanceof Num && simpledExp.multiplicand instanceof Num) {
			if (simpledExp.multiplier.toString() == "0" || simpledExp.multiplicand.toString() == "0") {
				return new Num(0);
			return new Num(simpledExp.evaluate());	
		    }
		}
		if (simpledExp.multiplier.toString() == "1") {
			return multiplicand;	
		}
		if (simpledExp.multiplicand.toString() == "1") {
			return multiplier;	
		}
		return simpledExp;
	}
}


