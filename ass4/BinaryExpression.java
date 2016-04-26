import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class BinaryExpression {
    protected Expression argA;
    protected Expression argB;
    protected String operator;

    public BinaryExpression(Expression argA, Expression argB) {
        this.argA = argA;
        this.argB = argB;
    }
    public BinaryExpression(Object argA, Object argB) {
    	// String + number case
    	if (argA instanceof String && argB instanceof Double) {
    		this.argA = new Var((String) argA);
    		this.argB = new Num((Double) argB);
    	} else if (argA instanceof Double && argB instanceof String) {
    		this.argA = new Num((double) argA);
    		this.argB = new Var((String) argB);
    	} else if (argA instanceof String && argB instanceof Integer) {
    		this.argA = new Var((String) argA);
    		this.argB = new Num((int) argB);
    	} else if (argA instanceof Integer && argB instanceof String) {
    		this.argA = new Num((int) argA);
    		this.argB = new Var((String) argB);
    	// number + number case	
    	}  else if (argA instanceof Integer && argB instanceof Integer) {
    		this.argA = new Num((int) argA);
    		this.argB = new Num((int) argB);
    	} else if (argA instanceof Integer && argB instanceof Double) {
    		this.argA = new Num((int) argA);
    		this.argB = new Num((double) argB);
    	} else if (argA instanceof Double && argB instanceof Integer) {
    		this.argA = new Num((double) argA);
    		this.argB = new Num((int) argB);
    	//String + String case
    	} else if (argA instanceof String && argB instanceof String) {
    		this.argA = new Var((String) argA);
    		this.argB = new Var((String) argB);
    	// expression + expression case
    	} else if (argA instanceof Expression && argB instanceof Expression) {
    		this.argA = (Expression) argA;
    		this.argB = (Expression) argB;
    	// expression + string case
    	} else if (argA instanceof Expression && argB instanceof String) {
    		this.argA = (Expression) argA;
    		this.argB = new Var((String) argB);
    	} else if (argA instanceof String && argB instanceof Expression) {
    		this.argA = new Var((String) argA);
    		this.argB = (Expression) argB;
    	// expression + number
    	} else if (argA instanceof Expression && argB instanceof Double) {
    		this.argA = (Expression) argA;
    		this.argB = new Num((double) argB);
    	} else if (argA instanceof Double && argB instanceof Expression) {
    		this.argA = new Num((double) argA);
    		this.argB = (Expression) argB;
    	} else if (argA instanceof Expression && argB instanceof Integer) {
    		this.argA = (Expression) argA;
    		this.argB = new Num((int) argB);
    	} else if (argA instanceof Integer && argB instanceof Expression) {
    		this.argA = new Num((int) argA);
    		this.argB = (Expression) argB;
    	} else {
    		throw new InvalidParameterException();
    	}
    }
    /*public BinaryExpression(Expression argA, String var) {
        this.argA = argA;
        this.argB = new Var(var);
    }
    public BinaryExpression(String var, Expression argB) {
        this.argA = new Var(var);
        this.argB = argB;
    }
    public BinaryExpression(Expression argA, double num) {
        this.argA = argA;
        this.argB = new Num(num);
    }
    public BinaryExpression(double num, Expression argB) {
        this.argA = new Num(num);
        this.argB = argB;
    }
    public BinaryExpression(double numA, double numB) {
        this.argA = new Num(numA);
        this.argB = new Num(numB);
    }
    public BinaryExpression(double num, String var) {
        this.argA = new Num(num);
        this.argB = new Var(var);
    }
    public BinaryExpression(String var, double num) {
        this.argA = new Var(var);
        this.argB = new Num(num);
    }
    public BinaryExpression(String varA, String varB) {
        this.argA = new Var(varA);
        this.argB = new Var(varB);
    }*/


    public double evaluate(Map<String, Double> assignment) throws Exception {
        Set<Entry<String, Double>> values = assignment.entrySet();
        Iterator<Entry<String, Double>> i = values.iterator();
        double sum = 0;
        try {
            Entry<String, Double> value = i.next();
            Expression newExpression = this.assign(value.getKey(), new Num(value.getValue()));
            while (i.hasNext()) {
                value = i.next();
                newExpression = newExpression.assign(value.getKey(), new Num(value.getValue()));
            }
            sum = newExpression.evaluate();
        } catch (Exception e) {
          }
        return sum;
    }

    public abstract double evaluate() throws Exception;

    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        List<String> tempVars = argA.getVariables();
        if (tempVars != null) {
            variables.addAll(tempVars);
        }
        tempVars = argB.getVariables();
        if (tempVars != null) {
            variables.removeAll(tempVars);
            variables.addAll(tempVars);
        }
        return variables;
    }

    public String toString() {
        return "(" + argA.toString() + this.operator + argB.toString() + ")";
    }

    public abstract Expression assign(String var, Expression expression);

}
