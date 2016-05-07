import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-24 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression arg;

    public UnaryExpression(Object arg) {
        if (arg instanceof String) {
            this.arg = new Var((String) arg);
        } else if (arg instanceof Double) {
            this.arg = new Num((double) arg);
        } else if (arg instanceof Integer) {
            this.arg = new Num((int) arg);
        } else if (arg instanceof Expression) {
            this.arg = (Expression) arg;
        } else {
            throw new InvalidParameterException();
        }
        this.setOperator(getOperator());
    }

    protected Expression getArg() {
        return arg;
    }


    protected void setArg(Expression arg) {
        this.arg = arg;
    }

    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        List<String> tempVars = arg.getVariables();
        if (tempVars != null) {
            variables.addAll(tempVars);
        }
        if (variables.isEmpty()) {
            return null;
        }
        return variables;
    }

    public String toString(){
        if (arg instanceof BinaryExpression){
            return this.getOperator() + getArg().toString();
        }
        return this.getOperator() + "(" + arg.toString() + ")";
    }

}