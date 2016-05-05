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
			throw e;
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
	
	public Expression simplify(){
		if (getArg().getVariables() == null) {
			try {
				double evaluate = this.evaluate();
				Expression exp = new Num(evaluate); 
				return exp;
			} catch (Exception e){	
			}
	}
		if (this == new Sin(new Plus(getArg(), 90)) || (this == new Sin(new Plus(90, getArg())))){
			return new Cos(getArg());
		}
		return this;
	}
}