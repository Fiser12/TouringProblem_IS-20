package tspProblem;


import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

import java.util.ArrayList;

public class FuncionEvaluacion extends EvaluationFunction {
    public static double calcularCoste(Ciudad origen, Ciudad destino) {

        // Solución euclidea
         double costeTotal = Math.sqrt(Math.pow(origen.getX() - destino.getX(), 2) + Math.pow(origen.getY() - destino.getY(), 2));

        //Solución manhatam
        //double costeTotal = Math.abs(origen.getX()-destino.getX())+Math.abs(origen.getY()-destino.getY());
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
        double costeGTotal = node.getParent().getG()+calcularCoste(((EnvironmentMap)node.getParent().getState()).getActual(), ((EnvironmentMap)node.getState()).getActual());
        return costeGTotal;
    }

    /**
     * Para hacer el cálculo de H y encontrar una ruta óptima vamos a tomar como heurística la distancia que hay desde la posición actual a la destino
     *
     * @param node
     * @return
     */
    @Override
    public double calculateH(Node node) {
       EnvironmentMap environment = (EnvironmentMap) node.getState();
        Ciudad actual = environment.getPosicionActual();
        return calcularCoste(actual, environment.getDestino());

    }
    public static void main(String [] args)
    {
        System.out.println(FuncionEvaluacion.calcularCoste(new Ciudad("A", 3, 2, new ArrayList<Ciudad>()), new Ciudad("B", 3, 7, new ArrayList<Ciudad>())));
    }
}
