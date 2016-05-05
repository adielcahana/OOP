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
			throw e;
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
		if(!getArg().toString().matches(".*\\d.*") && getArg().getVariables().size() == 1){
			String temp = getArg().getVariables().get(0);
			int count = this.toString().length() - this.toString().replace("-","").length();
			if (count%2 == 0 ){
				return new Var(temp);
			} else{
				return new Neg(temp);
			}
		}
		return this;	
	}
}
