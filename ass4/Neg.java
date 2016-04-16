import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Neg implements Expression {

	Expression negation;
	
	public Neg(Expression negation){
		this.negation = negation;
	}

	public double evaluate(Map<String, Double> assignment) throws Exception {
		Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double neg = 0;
        Expression newExpression = null;
        try {
		    while (i.hasNext()) {
			    newExpression = this.assign(i.next().getKey(), new Num(i.next().getValue()));
		    }
		    neg = newExpression.evaluate();
        } catch (Exception e) {
        	System.out.println("Neg Var wasn't found:" + e);
        }
        return neg; 
	}

	public double evaluate() throws Exception {
		double neg = 0;
		try {
			neg = - (negation.evaluate);
		} catch (Exception e) {
			System.out.println("neg evaluation faild :" + e);
		}
		return neg;
	}

	@Override
	public List<String> getVariables() {
		List<String> variables = new ArrayList<String>();
		variables.addAll(negation.getVariables());
		return variables;
	}

	   public String toString(){
		   return "(" + negation.toString() + ")";
	   }
	
	public Expression assign(String var, Expression expression) {
		return new Neg(negation.assign(var, expression));
	}
}
