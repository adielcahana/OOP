public class Neg extends UnaryExpression implements Expression {

	Expression negation;
	
	public Neg(Object negation){
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

}
