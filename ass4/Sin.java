public class Sin extends UnaryExpression implements Expression {

	public Sin(Object sinus){
		super(sinus);
		this.setOperator("Sin");
	}

	public double evaluate() throws Exception {
		double sin = 0;
		try {
			sin = Math.sin(Math.toRadians(getArg().evaluate()));
		} catch (Exception e) {
			System.out.println("Sin evaluation faild :" + e);
		}
		return sin;
	}

	public Expression assign(String var, Expression expression) {
		return new Sin(getArg().assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Mult(getArg().differentiate(var), new Cos(getArg()));
	}
}