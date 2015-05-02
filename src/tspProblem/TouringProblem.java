package tspProblem; /**
 * Created by Fiser on 23/2/15.
 */

import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;
import es.deusto.ingenieria.is.search.xml.StateXMLReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TouringProblem extends Problem {
    /**
     * Inicializa todos los elementos del problema necesarios para su resolución
     */

    public TouringProblem() {
        super();
        EnvironmentMap state = (EnvironmentMap) gatherInitialPercepts(); //Carga el estado incial
        addInitialState(state); //Añade el estado incial al problema
        this.createOperators();
    }
    /**
     * se encarga de incializar el estado inicial en función del XML tourProblem.xml
     *
     * @return
     */
    public State gatherInitialPercepts()
    {
        StateXMLReader stateXMLReader = new MapReader("data/tourProblem.xml");
        EnvironmentMap estado = (EnvironmentMap) stateXMLReader.getState();
        return estado;
    }

    public void createOperators() {
        EnvironmentMap inicial = ((EnvironmentMap) gatherInitialPercepts());
        ArrayList<Ciudad> lista = inicial.getLista();
        ArrayList<Move> operadores = new ArrayList<Move>();
        for(int i = 1; i<lista.size()-1; i++)
            for(int j = i+1; j<lista.size()-1; j++)
            	operadores.add(new Move(lista.get(i), lista.get(j)));
        for(Move move: operadores)
        	this.addOperator(move);
    }
    public void solve(SearchMethod searchMethod) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
        Date beginDate = GregorianCalendar.getInstance().getTime();
        System.out.println("\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");

        Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
        Date endDate = GregorianCalendar.getInstance().getTime();
        System.out.println("* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");

        long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
        long seconds = miliseconds / 1000;
        miliseconds %= 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        long hours = minutes / 60;
        minutes %= 60;

        String time = "\n*  Serach lasts: ";
        time += (hours > 0) ? hours + " h " : " ";
        time += (minutes > 0) ? minutes + " m " : " ";
        time += (seconds > 0) ? seconds + "s " : " ";
        time += (miliseconds > 0) ? miliseconds + "ms " : " ";

        System.out.println(time);

        if (finalNode != null) {
            System.out.println("\n- Solution found!     :)");
            List<String> operators = new ArrayList<String>();
            searchMethod.solutionPath(finalNode, operators);
            searchMethod.createSolutionLog(operators);
            System.out.println("- Final state:\n" + finalNode.getState());
        } else {
            System.out.println("\n- Unable to find the solution!     :(");
        }
    }
}
