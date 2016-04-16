import java.util.List;
import java.util.Map;

public class Var implements Expression {

	private String variable;
	
	public Var(String variable){
		this.variable = variable;
	}
	
	public double evaluate(Map<String, Double> assignment) throws Exception {
		try{
        } catch (Exception e) {
        	System.out.println("num wasn't found:" + e);
        }
        return 0; 
	}

	public double evaluate() throws Exception {
		try{	
		}
		catch (Exception e){
			System.out.println("var wasn't found:" + e);
		}
		return 0;
	}

	public List<String> getVariables() {
		return null;
	}

	public String toString(){
		return this.variable;
	}

	public Expression assign(String var, Expression expression) {
		double value = Double.parseDouble(var);
		Num num = new Num(value);
		return num;
		}
}