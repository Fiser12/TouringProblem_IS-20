package tspProblem;

/**
 * Created by Fiser on 23/2/15.
 */
public class Main {
    public static void main(String[] args) {
        try {
            TouringProblem problem = new TouringProblem();
            problem.solve(new HillClimbingSearch(new FuncionEvaluacionEuclidean(), 10));

        } catch (Exception ex) {
            System.err.println("% [tspProblem.Main Program] Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
