import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {

	private String variable;
	
	public Var(String variable){
		this.variable = variable;
	}
	
	public double evaluate(Map<String, Double> assignment) throws Exception {
		throw new Exception("cannot evaluate Var");
	}

	public double evaluate() throws Exception {
		throw new Exception("cannot evaluate Var");
	}

	public List<String> getVariables() {
		List<String> var = new ArrayList<String>();
		var.add(this.variable);
		return var; 
	}

	public String toString(){
		return this.variable;
	}

	public Expression assign(String var, Expression expression) {
		if(var.equals(variable)) {
		   return expression;
		} else { 
			return this;
		}
	}
	
	public Expression differentiate(String var) {
		return new Plus(argA.differentiate(var), argB.differentiate(var));
	}
}