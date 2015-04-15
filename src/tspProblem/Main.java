package tspProblem;

/**
 * Created by Fiser on 23/2/15.
 */
public class Main {
    public static void main(String[] args) {
        try {
     //       TouringProblem problem = new TouringProblem(); //Carga el problema con los estados finales y los operadores
     //       problem.solve(DepthFS.getInstance());

            TouringProblem problem2 = new TouringProblem(); //Carga el problema con los estados finales y los operadores
            problem2.solve(new TreeSearch(new FuncionEvaluacion()));

     //       TouringProblem problem3 = new TouringProblem(); //Carga el problema con los estados finales y los operadores
     //       problem3.solve(BreadthFS.getInstance());


        } catch (Exception ex) {
            System.err.println("% [tspProblem.Main Program] Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
