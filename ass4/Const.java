import java.util.List;
import java.util.Map;

public class Const implements Expression{

	private Num value;
	private String name;

	public Const(String constant){
		if (constant.equals("e")){
			this.value = new Num(Math.E);
			this.name = constant;
		}
		else if(constant.equals("Pi")){
			this.value = new Num(Math.PI);
			this.name = constant;
		}else{
			throw new RuntimeException("Error wrong argument" + constant);	
		}
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		throw new Exception("cannot assgin to Num");
	}

	public double evaluate() throws Exception {
		double evaluate = 0;
		try {
			evaluate = this.value.evaluate();
		}
		catch (Exception e) {
		}
		return evaluate;
	}

	@Override
	public List<String> getVariables() {
		return null;
	}

	public String toString(){
		return this.name;
	}

	@Override
	public Expression assign(String var, Expression expression) {
		return this;
	}

	@Override
	public Expression differentiate(String var) {
		return new Num(0);
	}
}
