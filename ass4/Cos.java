import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cos implements Expression {

	Expression cosinus;
	
	public Cos(Expression cosinus){
		this.cosinus = cosinus;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double cos = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    cos = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Cos Var wasn't found:" + e);
        }
        return cos; 
	}

	public double evaluate() throws Exception {
		double cos = 0;
		try {
			cos = Math.cos(cosinus.evaluate());
		} catch (Exception e) {
			System.out.println("Cos evaluation faild :" + e);
		}
		return cos;
	}

	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(cosinus.getVariables());
		return variables;
	}

	   public String toString(){
		   return "(" + cosinus.toString() + ")";
	   }
	
	public Expression assign(String var, Expression expression) {
		return new Sin(cosinus.assign(var, expression));
	}
}
