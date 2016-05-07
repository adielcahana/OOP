import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-04-18 */
public class Var implements Expression {

    private String variable;

    /** Var constructor.
     * Set the var a string.
     * <p>
     * @param variable - the string of the var */
    public Var(String variable) {
        this.variable = variable;
    }

    public double evaluate(Map<String, Double> assignment) throws Exception {
        throw new Exception("cannot evaluate Var");
    }

    public double evaluate() throws Exception {
        throw new Exception("cannot evaluate Var");
    }

    /** @return the var in a list of string. */
    public List<String> getVariables() {
        List<String> var = new ArrayList<String>();
        var.add(this.variable);
        return var;
    }

    /** @return the var string. */
    public String toString() {
        return this.variable;
    }

    /** Return the Var assign.
     * <p>
     * @param var - 
     * @param expression -
     * @return the const. */
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.variable)) {
            return expression;
        } else {
            return this;
        }
    }

    /** Return the Var differentiate.
     * <p>
     * @param var - the var to differentiate according it.
     * @return the differentiate If this == the Var to differentiate return Num(1) else return num(0).*/
    public Expression differentiate(String var) {
        if (this.variable == var) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /** Return the Var simplify.
     * <p>
     * @return the Var. */
    public Expression simplify() {
        return this;
    }
}