import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cos extends UnaryExpression implements Expression {

	Expression cosinus;
	
	public Cos(Expression cosinus){
		super(cosinus);
		this.operator = " Cos";
	}

	public double evaluate() throws Exception {
		double cos = 0;
		try {
			cos = Math.cos(arg.evaluate());
		} catch (Exception e) {
			System.out.println("Cos evaluation faild :" + e);
		}
		return cos;
	}

	public Expression assign(String var, Expression expression) {
		return new Sin(arg.assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Mult(arg.differentiate(var), new Neg(new Sin(arg)));
	}

	public Expression simplify() {
		this.arg = this.arg.simplify();
		if (arg.getVariables() == null) {
			try {
				double evaluate = this.evaluate();
				if (evaluate == 0 % Math.PI){
					return new Num(1);
				}
				if(evaluate == (Math.PI / 2) % Math.PI){
					return new Num(0);
				}
			} catch (Exception e){	
			}
		}
		return arg;
	}
	
}
