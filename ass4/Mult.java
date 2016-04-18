import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Mult extends BinaryExpression implements Expression {
	
	public Mult(Expression multiplier, Expression multiplicand) {
		super(multiplier, multiplicand);
		this.operator = " * ";
	}
	

	@Override
	public double evaluate() throws Exception {
		double product = 0;
		try {
			product = argA.evaluate() * argB.evaluate();
		//	System.out.println(product);
		} catch (Exception e) {
			System.out.println("Mult evaluation faild :" + e);
		}
		return product;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return new Mult(argA.assign(var, expression), argB.assign(var, expression));
	}		

	
	@Override
	public Expression differentiate(String var) {
		if (argA instanceof Num && argB instanceof Num) {
			return new Num(0);	
		}
		if (argA instanceof Num && !(argB instanceof Num)) {
			return new Mult(argA, argB.differentiate(var));
		}
		return new Plus(new Mult(argA.differentiate(var), argB),new Mult(argA, argB.differentiate(var)));
		}
	@Override
	public Expression simplify() {
		if (argA.getVariables() == null && argB.getVariables() == null) {
			try {
				double evaluate = this.evaluate();
				Expression exp = new Num(evaluate); 
				return exp;
			} catch (Exception e){	
			}
		}
		this.argA = this.argA.simplify();
		this.argB = this.argB.simplify();
		if (argA.toString().equals("0.0") || argB.toString().equals("0.0")) {
			return new Num(0);	
		}
		if (argA.toString().equals("1.0")) {
			return argB;	
		}
		if (argB.toString().equals("1.0")) {
			return argA;	
		}
		return this;
	}
}


