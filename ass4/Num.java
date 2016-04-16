import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Num implements Expression{
	
	private double value;
	
	public Num(double value){
	this.value = this.getValue();
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double value = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    value = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("num wasn't found:" + e);
        }
        return value; 
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

	public double getValue() {
		return value;
	}
}
