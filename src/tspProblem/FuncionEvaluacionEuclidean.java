package tspProblem;

import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

public class FuncionEvaluacionEuclidean extends EvaluationFunction {
    public double calcularCoste(Ciudad origen, Ciudad destino) {

        double costeTotal = Math.sqrt(Math.pow(origen.getX() - destino.getX(), 2) + Math.pow(origen.getY() - destino.getY(), 2));
        return costeTotal;

    }

    /**
     * Suma el coste de todos los nodos hasta el actual, el último nodo
     *
     * @param node
     * @return
     */
    @Override
    public double calculateG(Node node) {
        return 0;
    }

    /**
     * Dado que ya lo tenemos con estado completo, el calculo de H recorrerá todo y devolvera un coste estimado de la distribución actual.
     * @param node
     * @return
     */
    @Override
    public double calculateH(Node node) {
        EnvironmentMap environment = (EnvironmentMap) node.getState();
        double coste = 0;
        for(int i = 0; i<environment.getLista().size()-1; i++)
        {
            coste = coste + calcularCoste(environment.getLista().get(i), environment.getLista().get(i+1));
        }

        return coste;

    }
}
