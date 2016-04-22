import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Neg extends UnaryExpression implements Expression {

	Expression negation;
	
	public Neg(Expression negation){
		super(negation);
		this.operator = " -";
	}

	public double evaluate() throws Exception {
		double neg = 0;
		try {
			neg = - (arg.evaluate());
		} catch (Exception e) {
			System.out.println("neg evaluation faild :" + e);
		}
		return neg;
	}

	public Expression assign(String var, Expression expression) {
		return new Neg(arg.assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Neg(arg.differentiate(var));
	}

	@Override
	public Expression simplify() {
		return arg;
	}
}
