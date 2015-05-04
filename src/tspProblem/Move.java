package tspProblem;

import Elementos.Ciudad;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

/**
 * Created by Fiser on 23/2/15.
 */
public class Move extends Operator {
    private Ciudad destino;
    private Ciudad origen;

    /**
     * Constructor pasandole la ciudad destino
     *
     * @param destino
     */
    public Move(Ciudad origen, Ciudad destino)
    {
        super(destino.getPunto() + origen.getPunto());
        this.destino = destino;
        this.origen = origen;
    }

    @Override
    /**
     * La situación siempre es true
     */
    protected boolean isApplicable(State state) {
        boolean aplicable = true;
        return aplicable;
    }

    @Override
    /**
     * Cambiamos la posición de las ciudades en función de como las hemos recorrido
     */
    protected State effect(State state) {
        EnvironmentMap estadoClon = ((EnvironmentMap) state).clone();
        for(int i = 0; i<((EnvironmentMap) state).getLista().size(); i++)
        {
            Ciudad procesar = ((EnvironmentMap) state).getLista().get(i);
            if (procesar.equals(origen)) {
                for (int j = 0; j<((EnvironmentMap) state).getLista().size(); j++)
                {
                    Ciudad procesar2 = ((EnvironmentMap) state).getLista().get(j);
                    if (procesar2.equals(destino)) {
                        java.util.Collections.swap(estadoClon.getLista(), i, j);
                    }
                }
            }
        }

        return estadoClon;
    }
}
