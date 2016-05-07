import java.util.List;
import java.util.Map;
/**
* @author Adiel cahana <adiel.cahana@gmail.com>
* @version 1.0
* @since 2016-05-02 */
public interface Expression {
    /**
     * Evaluate the expression and return the result.
     * <p>
     * the evaluation is using the variable values provided in the assignment
     * If the expression contains a variable which is not in the assignment, an exception is thrown
     * @param assignment - Map of variables and values
     * @return result*/
   double evaluate(Map<String, Double> assignment) throws Exception;

   /**
    * Evaluate the expression and return the result.
    * <p>
    * @return result*/
   double evaluate() throws Exception;

   /**
    * Returns a list of the variables in the expression.
    * <p>
    * @param variables - list of the variables, null if there is no vars*/
   List<String> getVariables();

   /**
    * return a string representation of the expression
    * <p>
    * @return String - printable representation*/
   String toString();

   /**
    * assigns a given expression to a var.
    * <p>
    * @param var - a string of the var to assign the expression to
    * @param expression - a string of the var to assign the expression to
    * @return Pow - a new Pow expression with the new assigned vars.*/
   Expression assign(String var, Expression expression);

   /**
    * differentiate the expression with respect to a given var.
    * <p>
    * @param var - the var to differentiate
    * @return Expression - the differentiated expression.*/
  Expression differentiate(String var);

   /**
    * simplify the representation of the given expression with respect to a given var.
    * <p>
    * @return Expression - the simplified expression.*/
  Expression simplify();
}