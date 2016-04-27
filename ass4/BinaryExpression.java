import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public abstract class BinaryExpression {
    private Expression argA;
    private Expression argB;
    private String operator;

    public BinaryExpression(Object argA, Object argB) {
        // correctly assign argA expression according to its type   
        if (argA instanceof String) {
            this.argA = new Var((String) argA);
        } else if (argA instanceof Double) {
            this.argA = new Num((double) argA);
        } else if (argA instanceof Integer) {
            this.argA = new Num((int) argA);
        } else if (argA instanceof Expression) {
            this.argA = (Expression) argA;
        } else {
            throw new InvalidParameterException();
        }
        // correctly assign argB expression according to its type        
        if (argB instanceof String) {
            this.argB = new Var((String) argB);
        } else if (argB instanceof Double) {
            this.argB = new Num((double) argB);
        } else if (argB instanceof Integer) {
            this.argB = new Num((int) argB);
        } else if (argB instanceof Expression) {
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


    protected Expression getArgA() {
        return argA;
    }


    protected void setArgA(Expression argA) {
		this.argA = argA;
	}

	protected Expression getArgB() {
        return argB;
    }
	
	protected void setArgB(Expression argB) {
		this.argB = argB;
	}

    protected String getOperator() {
		return operator;
	}

	protected void setOperator(String operator) {
		this.operator = operator;
	}


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
        return "(" + argA.toString() + this.getOperator() + argB.toString() + ")";
    }

    public abstract Expression assign(String var, Expression expression);

}
