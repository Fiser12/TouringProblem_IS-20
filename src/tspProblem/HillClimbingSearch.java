package tspProblem;

/**
 * Created by Fiser on 29/4/15.
 */

import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;
import es.deusto.ingenieria.is.search.algorithms.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

public class HillClimbingSearch extends HeuristicSearchMethod {
    private int profundidad;

    public HillClimbingSearch(EvaluationFunction funcion, int profundidad) {
        super(funcion);
        this.profundidad = profundidad;
    }

    public Node search(Problem problem, State state) {
        Node current = new Node(state); //Creamos con el estado inicial
        current.setH(this.getEvaluationFunction().calculateH(current));
        boolean localMejor = false;
        
        //Paramos en cuanto detectamos el que queremos, y además tenemos en cuenta la profundidad
        while(!localMejor&&profundidad!=0)
        {
      	
      		profundidad--;
            Node mejor;
            mejor = this.expand(current, problem);
            if(current.getH()<=mejor.getH())
                localMejor = true;
            else
                current = mejor;
        }
        state.toString();
        return current;
    }

    protected Node expand(Node node, Problem problem) {
        Node nodoSucesor = null;
        State current = null;
        State estadoOperado = null;
		Node mejor = null;

        //Controlamos si los argumentos pasados son nulos
        if (node != null && problem != null) {
            current = node.getState();
            if (current != null) { //Controlamos que no sea null
                for (Operator operator : problem.getOperators()) { //Pasamos por los operadores
                    //Lo aplicamos
                    estadoOperado = operator.apply(current);
                    //Si el sucesor generado no es null creamos el Node y luego asignamos los valores y el H lo calculamos
                    if (estadoOperado != null) {
                        nodoSucesor = new Node(estadoOperado);
                        nodoSucesor.setOperator(operator.getName());
                        nodoSucesor.setParent(node);
                        nodoSucesor.setDepth(node.getDepth() + 1);
                        nodoSucesor.setH(this.getEvaluationFunction().calculateH(nodoSucesor));
                        //Si es el primer nodo, lo cargamos como mejor y si resulta que es mejor el sucesor al mejor hasta ahora lo cambiamos
                        if(mejor==null||mejor.getH() > nodoSucesor.getH()){
                        	mejor=nodoSucesor;
                        }
                    }
                }
            }
        }
        return mejor;
    }
}