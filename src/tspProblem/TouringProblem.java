package tspProblem; /**
 * Created by Fiser on 23/2/15.
 */

import Actions.Move;
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

    public static void main(String[] args) {
        testUnitarioIsFinalState();
        testUnitarioCreateOperators();
    }

    //Comprobamos que el isFinalState funcione, solo tenemos que pasarle un estado que sea valido para hacer dicha comprobación
    public static void testUnitarioIsFinalState() {
        TouringProblem problema = new TouringProblem();
        EnvironmentMap state = (EnvironmentMap) problema.gatherInitialPercepts(); //Carga el estado incial
        state.setRecorrida(state.getLista());
        System.out.println("El estado es final: " + problema.isFinalState(state));
    }

    //Comprobamos que el CreateOperators se ejecute sin lanzar ninguna excepción, además también estamos comprobando el gatherInitialPercepts
    public static void testUnitarioCreateOperators() {
        TouringProblem problema = new TouringProblem();
        EnvironmentMap state = (EnvironmentMap) problema.gatherInitialPercepts(); //Carga el estado incial
        problema.addInitialState(state); //Añade el estado incial al problema
        problema.createOperators();
        System.out.println("Se han creado todos los operadores sin excepciones");
    }

    /**
     * se encarga de incializar el estado inicial en función del XML tourProblem.xml
     *
     * @return
     */
    public State gatherInitialPercepts() {
        StateXMLReader stateXMLReader = new MapReader("data/tourProblem.xml");
        EnvironmentMap estado = (EnvironmentMap) stateXMLReader.getState();
        return estado;
    }

    private void createOperators() {
        ArrayList<Move> temporal = new ArrayList<Move>();
        EnvironmentMap inicial = ((EnvironmentMap) gatherInitialPercepts());
        for (Ciudad ciudad : inicial.getLista()) {
            if(!inicial.getPosicionActual().equals(ciudad))//No necesitamos ir al estado en el que ya nos encontramos
                temporal.add(new Move(ciudad));
            //      this.addOperator(new Move(ciudad));
        }
        java.util.Collections.shuffle(temporal);
        for (Move move : temporal) {
            this.addOperator(move);
        }
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

        String time = "\n* Serach lasts: ";
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

    public boolean isFinalState(State state) {
        if (state != null && state != null && state instanceof EnvironmentMap) {
            EnvironmentMap queenEnv = (EnvironmentMap) state;
            ArrayList<Ciudad> lista = queenEnv.getRecorrida();

            boolean esFinal = lista.containsAll(((EnvironmentMap) state).getLista());

            if ((((EnvironmentMap) state).getPosicionActual().equals(((EnvironmentMap) state).getDestino())))
                esFinal = esFinal && true;
            else
                esFinal = esFinal && false;
            return esFinal;
        } else {
            return false;
        }
    }
}
