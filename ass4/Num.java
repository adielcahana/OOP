import java.util.List;
import java.util.Map;

public class Num implements Expression{
	
	private double value;
	
	public Num(double value){
	this.value = value;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
        throw new Exception("cannot assgin to Num");
	}

	public double evaluate() throws Exception {
		double evaluate = 0;
		try {
			evaluate = this.value;
		}
		catch (Exception e) {
		}
		return evaluate;
	}

	public String toString(){
		return Double.toString(value);
	}
	
	public List<String> getVariables() {
		return null;
	}

	public Expression assign(String var, Expression expression) {
		return this;
	}
}