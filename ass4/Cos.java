public class Cos extends UnaryExpression implements Expression {
	
	public Cos(Object cosinus){
		super(cosinus);
		this.setOperator("Cos");
	}

	public double evaluate() throws Exception {
		double cos = 0;
		try {
			cos = Math.cos(Math.toRadians(getArg().evaluate()));
		} catch (Exception e) {
			System.out.println("Cos evaluation faild :" + e);
		}
		return cos;
	}

	public Expression assign(String var, Expression expression) {
		return new Cos(getArg().assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Mult(getArg().differentiate(var), new Neg(new Sin(getArg())));
	}
	
	public Expression simplify(){
		if (getArg().getVariables() == null) {
			try {
				double evaluate = this.evaluate();
				Expression exp = new Num(evaluate); 
				return exp;
			} catch (Exception e){	
			}
	}
		return this;
	}
}
