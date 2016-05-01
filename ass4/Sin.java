public class Sin extends UnaryExpression implements Expression {

	Expression sinus;

	public Sin(Object sinus){
		super(sinus);
		this.operator = " Sin";
	}

	public double evaluate() throws Exception {
		double sin = 0;
		try {
			sin = Math.sin(arg.evaluate());
		} catch (Exception e) {
			System.out.println("Sin evaluation faild :" + e);
		}
		return sin;
	}

	public Expression assign(String var, Expression expression) {
		return new Sin(arg.assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Mult(arg.differentiate(var), new Cos(arg));
	}
}