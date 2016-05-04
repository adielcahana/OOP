public class Neg extends UnaryExpression implements Expression {

	public Neg(Object negation){
		super(negation);
		this.setOperator("-");
	}

	public double evaluate() throws Exception {
		double neg = 0;
		try {
			//System.out.println(getArg().evaluate());
			neg = - (getArg().evaluate());
		} catch (Exception e) {
			System.out.println("neg evaluation faild :" + e);
		}
		return neg;
	}

	public Expression assign(String var, Expression expression) {
		return new Neg(getArg().assign(var, expression));
	}

	@Override
	public Expression differentiate(String var) {
		return new Neg(getArg().differentiate(var));
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
