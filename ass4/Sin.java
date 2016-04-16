import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Sin implements Expression {

	Expression sinus;
	
	public Sin(Expression sinus){
		this.sinus = sinus;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double sin = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    sin = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Sin Var wasn't found:" + e);
        }
        return sin; 
	}

	public double evaluate() throws Exception {
		double sin = 0;
		try {
			sin = Math.sin(sinus.evaluate());
		} catch (Exception e) {
			System.out.println("Sin evaluation faild :" + e);
		}
		return sin;
	}

	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(sinus.getVariables());
		return variables;
	}

	   public String toString(){
		   return "(" + sinus.toString() + ")";
	   }
	
	public Expression assign(String var, Expression expression) {
		return new Sin(sinus.assign(var, expression));
	}
}
