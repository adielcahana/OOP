import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Num implements Expression{
	
	private double value;
	
	public Num(double value){
	this.value = value;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
        } catch (Exception e) {
        	System.out.println("num wasn't found:" + e);
        }
        return 0; 
	}

	public double evaluate() throws Exception {
		double evaluate = 0;
		try{
			evaluate = this.value;
		}
		catch (Exception e){
			System.out.println("num wasn't found:" + e);
		}
		return evaluate;
	}

	public List<String> getVariables() {
		return null;
	}

	public Expression assign(String var, Expression expression) {
		return null;
	}
}