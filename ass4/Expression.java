import java.util.List;
import java.util.Map;

public interface Expression {
   // Evaluate the expression using the variable values provided
   // in the assignment, and return the result.  If the expression
   // contains a variable which is not in the assignment, an exception
   // is thrown. 
   public double evaluate(Map<String, Double> assignment) throws Exception;

   // A convenience method. Like the `evaluate(assignment)` method above,
   // but uses an empty assignment.
   public  double evaluate() throws Exception;

   // Returns a list of the variables in the expression.
   public List<String> getVariables();

   // Returns a nice string representation of the expression.
   public String toString();

   // Returns a new expression in which all occurrences of the variable
   // var are replaced with the provided expression (Does not modify the
   // current expression).
   public Expression assign(String var, Expression expression);
   
   // Returns the expression tree resulting from differentiating
   // the current expression relative to variable `var`.
   public Expression differentiate(String var);
}