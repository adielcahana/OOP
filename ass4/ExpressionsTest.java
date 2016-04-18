import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExpressionsTest {
    public static void main(String[] args) {
    	Expression e1 =
    			new Log(new Const("e"),
                     new Pow(
                        new Mult(
                           new Plus(
                              new Mult(new Num(2), new Var("x")),
                              new Var("y")),
                           new Num(4)),
                     new Var("x")));
    	Expression e2 = 
    			new Div(
                        new Mult(
                           new Pow(
                              new Mult(new Num(2), new Var("x")),
                              new Var("y")),
                           new Num(4)),
                     new Var("x"));
    	//toString test
    	if (e1.toString().equals("Log(e, ((((2.0 * x) + y) * 4.0)^x))")) {
    		System.out.println("e1 is correct " + e1.toString());
    	} else {  	
    		System.out.println("Error in e1 " + e1.toString());
    	}
    	if (e2.toString().equals("((((2.0 * x)^y) * 4.0) / x)")) {
    		System.out.println("e2 is correct " + e2.toString());
    	} else {  	
    		System.out.println("Error in e2 " + e2.toString());
    	}
    	
    	//evaluate test
    	Map<String, Double> assignment = new TreeMap<String, Double>();
    	assignment.put("x", 2.0);
    	assignment.put("y", 4.0);
    	double value;
		try {
			value = e1.evaluate(assignment);
			if (Math.abs(value - 6.93147) <= 0.001) {
	    		System.out.println("e1 evaluation succseeded"); 
	    	} else {
	    		System.out.println("e1 evaluation failed " + value); 
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			value = e2.evaluate(assignment);
			if (value == 512.0) {
	    		System.out.println("e2 evaluation succseeded"); 
	    	} else {
	    		System.out.println("e2 evaluation failed " + value); 
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//Differentiation test
    	System.out.println(e1.differentiate("x"));
    	System.out.println(e1.differentiate("x").simplify());
    	System.out.println(e1.differentiate("y"));
    	System.out.println(e1.differentiate("y").simplify());
    	System.out.println(e2.differentiate("x"));
    	System.out.println(e2.differentiate("x").simplify());
    	System.out.println(e2.differentiate("y"));
    	System.out.println(e2.differentiate("y").simplify());
    	
    	// variables list test
    	List<String> Vars = e1.getVariables();
    	if (Vars.contains("x") && Vars.contains("y")) {
    		System.out.println("e1 getVars succseeded");
    	} else {
    		System.out.println("e1 getVars failed");
    	}    
    	Vars = e2.getVariables();
    	if (Vars.contains("x") && Vars.contains("y")) {
    		System.out.println("e2 getVars succseeded");
    	} else {
    		System.out.println("e2 getVars failed");
    	}
    
    	
    }
}
