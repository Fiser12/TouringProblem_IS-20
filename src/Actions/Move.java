package Actions;

import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;
import tspProblem.EnvironmentMap;
import tspProblem.FuncionEvaluacion;

import java.util.ArrayList;


/**
 * Created by Fiser on 23/2/15.
 */
public class Move extends Operator {
    private Ciudad destino;

    /**
     * Constructor pasandole la ciudad destino
     *
     * @param destino
     */
    public Move(Ciudad destino) {

        super(destino.getPunto());
        this.destino = destino;
    }

    @Override
    /**
     * Comprobamos si es aplicable la acción sobre el estado que le pasamos, aquí comprobamos que no nos estemos moviendo a la misma ciudad, que no hayamos estado ya en la ciudad y que desde la ciudad actual podamos ir al destino
     * vamos a comprobar también para ir a la ciudad final que antes hayamos recorrido todas.
     */
    protected boolean isApplicable(State state) {
        boolean aplicable = true;
        for (Ciudad ciudades : ((EnvironmentMap) state).getLista()) //Comprobamos que desde la posición actual podamos ir a la destino
        {
            if (ciudades.getPunto().equals(((EnvironmentMap) state).getPosicionActual().getPunto()))//Cogemos la ciudad en la que estamos
            {
                ArrayList<Ciudad> destinos = ciudades.getDestinos();//Sacamos sus destinos
                for (Ciudad ciudades2 : destinos)//Procesamos sus destinos
                {
                    if (ciudades2.getPunto().equals(destino.getPunto())) //Si contiene el destino al que queremos ir podemos hacer el metodo
                    {
                        aplicable = true;
                        break;
                    } else
                        aplicable = false;
                }
            }
        }
        if (((EnvironmentMap) state).getRecorrida().contains(destino))
            return false;
        //Si pretendemos ir a la misma ciudad en la que estamos deberá saltar error
        if (destino.getPunto().equals(((EnvironmentMap) state).getPosicionActual()))
            return false;
        //Evita que se pueda poner el destino final antes de haber recorrido el resto de posiciones
        if (((EnvironmentMap) state).getRecorrida().size() != ((EnvironmentMap) state).getLista().size() - 1 && destino.equals(((EnvironmentMap) state).getDestino()))
            return false;
        return aplicable;
    }

    @Override
    /**
     * El efecto de la acción y devolviendo un nuevo estado clonado sobre el que se aplica la modificación
     */
    protected State effect(State state) {
        EnvironmentMap estadoClon = ((EnvironmentMap) state).clone();
        estadoClon.addRecorrido(destino);
        this.setCost(FuncionEvaluacion.calcularCoste(((EnvironmentMap) state).getPosicionActual(), destino));
        return estadoClon;
    }
}
