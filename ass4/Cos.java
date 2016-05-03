public class Cos extends UnaryExpression implements Expression {
	
	public Cos(Object cosinus){
		super(cosinus);
		this.setOperator(" Cos");
	}

	public double evaluate() throws Exception {
		double cos = 0;
		System.out.println(Math.cos(getArg().evaluate()));
		try {
			cos = Math.cos(getArg().evaluate());
			System.out.println(cos);
		} catch (Exception e) {
			System.out.println("Cos evaluation faild :" + e);
		}
		return cos;
	}

	public Expression assign(String var, Expression expression) {
		return new Sin(getArg().assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Mult(getArg().differentiate(var), new Neg(new Sin(getArg())));
	}
	
}
